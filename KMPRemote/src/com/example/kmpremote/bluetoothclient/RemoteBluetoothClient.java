package com.example.kmpremote.bluetoothclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.json.JSONObject;


import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.kmpremote.R;
import com.example.kmpremote.keyboard.KeyboardActivity;
import com.example.kmpremote.mouse.DisplayMousePad;
import com.example.kmpremote.presentation.PresentationActivity;

public class RemoteBluetoothClient extends Activity{
	static UUID uuid = UUID.fromString("04c6093b-0000-1000-8000-00805f9b34fb");

	//Variables for the adapter(phone's Bluetooth) and device(server Bluetooth).
	private	BluetoothAdapter deviceBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	private BluetoothDevice btDevice;
	private	Set<BluetoothDevice> pairedDevices = deviceBluetoothAdapter.getBondedDevices();	
	Context context;

	// Arrays containing new and paired devices
	public ArrayAdapter<String> pairedArrayAdapter;
	public ArrayAdapter<String> newArrayAdapter;

	//threads
	private ConnectThread makeCnt; //thread that creates the connection
	private static ConnectedThread maintainCnt; //thread that manages the connection

	// Buttons to choose between activities
	public void mouseActivity(View v){
		Intent mouse = new Intent(this, DisplayMousePad.class);
		startActivity(mouse);
	}

	public void keyboardActivity(View v){
		Intent keyboard = new Intent(this, KeyboardActivity.class);
		startActivity(keyboard);
	}

	public void presentationActivity(View v){
		Intent pres = new Intent(this, PresentationActivity.class);
		startActivity(pres);
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bluetooth);
		
		//Initialise array containing newly found devices devices
		pairedArrayAdapter = new ArrayAdapter<String>(this, R.layout.device_name);
		newArrayAdapter = new ArrayAdapter<String>(this, R.layout.device_name);

		//Creating ListView for the two arrays
		ListView pairedDevicesListView = (ListView) findViewById(R.id.listView1);
		pairedDevicesListView.setAdapter(pairedArrayAdapter);

		ListView newDevicesListView = (ListView)findViewById(R.id.listView2);
		newDevicesListView.setAdapter(newArrayAdapter);

		//creating two intents and registers
		IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
		registerReceiver(mReceiver, filter); // Don't forget to unregister during onDestroy		

		filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
		registerReceiver(mReceiver, filter);

		// Initial checkers
		if(hasBluetoothAdapter()){
			if(!isBluetoothEnabled()){
				Toast.makeText(getApplicationContext(), "No Bluetooth enabled", Toast.LENGTH_SHORT).show();
			}			

			// If checkers pass, scan for devices
			discoverDevices();			
		}else{
			Toast.makeText(getApplicationContext(), "The device has no Bluetooth", Toast.LENGTH_SHORT).show();				
		}		


		getDevice();

		newDevicesListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				//Get the mac address of the selected device.
				String info = ((TextView) arg1).getText().toString();
				String address = info.substring(info.length() - 17);

				btDevice = deviceBluetoothAdapter.getRemoteDevice(address);
				connect();			
			}			
		});

		pairedDevicesListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				//Get the mac address of the selected device.
				String info = ((TextView) arg1).getText().toString();
				String address = info.substring(info.length() - 17);

				btDevice = deviceBluetoothAdapter.getRemoteDevice(address);
				connect();			
			}			
		});
	}

	// Check for Bluetooth Adapter
	private boolean hasBluetoothAdapter(){
		if (deviceBluetoothAdapter == null) {
			return false;
		}
		return true;
	}

	// Check if Bluetooth is enabled
	public boolean isBluetoothEnabled(){	
		return deviceBluetoothAdapter.isEnabled();
	}	

	// Add devices to the two lists
	public void getDevice() {	

		// If there are paired devices
		if (pairedDevices.size() > 0) {
			findViewById(R.id.title_paired_devices).setVisibility(View.VISIBLE);

			// Loop through paired devices
			for (BluetoothDevice device : pairedDevices) {

				// Add the name and address to an array adapter to show in a ListView
				pairedArrayAdapter.add(device.getName() + "\n" + device.getAddress());
			}
		}else {
			String noDevices = getResources().getText(R.string.none_paired).toString();
			if(pairedArrayAdapter == null){
				pairedArrayAdapter.add(noDevices);
			}
			if(newArrayAdapter == null){
				newArrayAdapter.add(noDevices);
			}
		}
	}

	//Method that searches for devices
	private void discoverDevices() {
	
		// Turn on sub-title for new devices
		findViewById(R.id.title_new_devices).setVisibility(View.VISIBLE);
		findViewById(R.id.title_paired_devices).setVisibility(View.VISIBLE);

		// If we're already discovering, stop it
		if (deviceBluetoothAdapter.isDiscovering()) {
			deviceBluetoothAdapter.cancelDiscovery();
		}
		// Request discover from BluetoothAdapter
		deviceBluetoothAdapter.startDiscovery();
	}

	//Method that starts the connection thread
	public void connect(){
	
		// check if there is a connection : terminate
		if(makeCnt != null) {
			makeCnt.cancel();
		}
		makeCnt = new ConnectThread(btDevice);
		makeCnt.start();				
	}

	//Method that maintains the connection
	private void connected(BluetoothDevice device, BluetoothSocket socket){
	
		// check if there is a maintain connection thread : terminate
		if(maintainCnt != null) {
			maintainCnt.cancel();
		}
		maintainCnt = new ConnectedThread(socket);
		maintainCnt.start();
	}

	//Adding end command to each byte array 
	private static byte[] stringToDelimitedBytes(String cmd) {
		byte[] commandBuffer = cmd.getBytes();
		byte[] commandBufferWithEnding= new byte[commandBuffer.length+1];
		System.arraycopy(commandBuffer, 0, commandBufferWithEnding, 0, commandBuffer.length);
		commandBufferWithEnding[commandBufferWithEnding.length-1] = -2;
		return commandBufferWithEnding;
	}

	//Public send method for other classes to use when sending over to the server
	public static void send(String cmd) {
	
		//If there is a connection : send
		byte[] commandBufferWithEnding= stringToDelimitedBytes(cmd);
		if(maintainCnt != null) {
			maintainCnt.write(commandBufferWithEnding);
		}					
	}	

	//Get the JSONObject containing the notes, be aware that the Object might not be populated if the data has not been read.
	public static JSONObject getJSONObject() {
		if (maintainCnt.jObj == null) {
			return null;
		} else {
			return maintainCnt.jObj;
		}
	}

	//BroadcastReceiver for ACTION_Found
	private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			
			// When discovery finds a device
			if (BluetoothDevice.ACTION_FOUND.equals(action)) {
			
				// Get the BluetoothDevice object from the Intent
				BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
				
				// Add the name and address to an array adapter to show in a ListView
				newArrayAdapter.add(device.getName() + "\n" + device.getAddress());
			}
		}
	};

	//Thread that creates a socket and connects to the server
	private class ConnectThread extends Thread{
		private BluetoothDevice btDevice;
		private BluetoothSocket btSocket;

		public ConnectThread(BluetoothDevice device){
			btDevice = device;
			try{
				btSocket = btDevice.createInsecureRfcommSocketToServiceRecord(uuid);				
			}catch(IOException e){
				Toast.makeText(getApplicationContext(), "Error at socketing", Toast.LENGTH_SHORT).show();
			}
		}

		public void run(){
			if (deviceBluetoothAdapter.isDiscovering()) {
				deviceBluetoothAdapter.cancelDiscovery();
			}
			try {
				btSocket.connect();
			} catch (IOException e) {
				try{
					btSocket.close();
				}catch(IOException ex){
					Toast.makeText(getApplicationContext(), "Error at socketing", Toast.LENGTH_SHORT).show();
				}
			}
			connected(btDevice,btSocket);
		}

		public void cancel(){
			try {
				btSocket.close();
			} catch (IOException e) {
				Toast.makeText(getApplicationContext(), "Error at canceling ConnectThread", Toast.LENGTH_SHORT).show();
			}
		}

	}

	//Thread to maintain the connection and send data to the server
	private class ConnectedThread extends Thread{
		BluetoothSocket btSocket;
		OutputStream os;
		InputStream is;
		JSONObject jObj;

		ConnectedThread(BluetoothSocket socket){
			btSocket = socket;
			try{
				os = socket.getOutputStream();
				is = socket.getInputStream();
			}catch(IOException e){
				Toast.makeText(getApplicationContext(), "Error at outputing", Toast.LENGTH_SHORT).show();
			}
		}

		public void run(){
			if (deviceBluetoothAdapter.isDiscovering()) {
				deviceBluetoothAdapter.cancelDiscovery();
			}
			byte[] buffer = new byte[1024]; 
			int bytes = 0;			
			
			// Keep listening to the InputStream while connected
			while(true) {
				listen(buffer, bytes);
			}
		}		

		//listen method that stores the received bytes to an array and then sends it on.
		public void listen(byte[] buffer, int bytes) {
			int end = -2;
			int bye = -1;

			List<Byte> isBytes = new ArrayList<Byte>();
			while(true){
				try{
					byte aByte = (byte) is.read();
					if(aByte == (end)){
						try{
							byte[] arrBytes = new byte[isBytes.size()];
							int i = 0;
							for(Byte b :isBytes){
								arrBytes[i++] = b;
							}
							String serverInput = new String(arrBytes);
							jObj = new JSONObject(serverInput);
						}catch(Exception e){
							e.printStackTrace();
						}
					}else if(aByte == bye){
						break;
					}else{
						isBytes.add(aByte);
					}
				}catch(Exception e){
					e.printStackTrace();	
				}
			}
		}

		// Send byte array to the server
		public void write(byte[] buffer) {						
			try {
				os.write(buffer);
				os.flush();
			} catch (IOException e) { 
				Toast.makeText(getApplicationContext(), "Error at writing", Toast.LENGTH_SHORT).show();
			}
		}

		public void cancel(){
			try {
				btSocket.close();
			} catch (IOException e) {
				Toast.makeText(getApplicationContext(), "Error at canceling ConnectedThread", Toast.LENGTH_SHORT).show();
			}
		}
	}
}