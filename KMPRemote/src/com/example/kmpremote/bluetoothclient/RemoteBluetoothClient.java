package com.example.kmpremote.bluetoothclient;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

import com.example.kmpremote.R;
import com.example.kmpremote.keyboard.KeyboardActivity;
import com.example.kmpremote.mouse.DisplayMousePad;
import com.example.kmpremote.presentation.PresentationActivity;

import android.widget.AdapterView.OnItemClickListener;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class RemoteBluetoothClient extends Activity{
	static UUID uuid = UUID.fromString("04c6093b-0000-1000-8000-00805f9b34fb");
	
	//Variables for the adapter(phone's Bluetooth) and device(server Bluetooth).
	private	BluetoothAdapter deviceBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	private BluetoothDevice btDevice;
	private BluetoothSocket btSocket;
	private static String testcmdBytes= ""; 
	private	Set<BluetoothDevice> pairedDevices = deviceBluetoothAdapter.getBondedDevices();	
	Context context;
	
	public ArrayAdapter<String> newArrayAdapter;
	public ArrayAdapter<String> pairedArrayAdapter;
	
	
	//threads
	private ConnectThread makeCnt; //thread that creates the connection
	private static ConnectedThread maintainCnt; //thread that manages the connection
	
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
		newArrayAdapter = new ArrayAdapter<String>(this, R.layout.device_name);
		
		//Creating ListView for the two arrays
		ListView pairedDevicesListView = (ListView) findViewById(R.id.listView2);
		pairedDevicesListView.setAdapter(pairedArrayAdapter);
		
		ListView newDevicesListView = (ListView)findViewById(R.id.listView1);
		newDevicesListView.setAdapter(newArrayAdapter);
		
		//creating two intents and registers
		IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
		registerReceiver(mReceiver, filter); // Don't forget to unregister during onDestroy		
		
		filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
		registerReceiver(mReceiver, filter);
		
		if(hasBluetoothAdapter()){
			if(!isBluetoothEnabled()){
				Toast.makeText(getApplicationContext(), "No Bluetooth enabled", Toast.LENGTH_SHORT).show();
			}			
			discoverDevices();			
		}else{
			Toast.makeText(getApplicationContext(), "The device has no Bluetooth", Toast.LENGTH_SHORT).show();				
		}			
		getDevice();
		
		
		
		newDevicesListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				//Get the mac address of the selected device.
				String info = ((TextView) arg1).getText().toString();
				//TODO FIX THIS HARDCODE 
				String address = info.substring(info.length() - 17);
				//String address = "00:0A:5D:80:85";
				
				btDevice = deviceBluetoothAdapter.getRemoteDevice(address);
				connect();			
				send(testcmdBytes);
			}			
		});
		
		newDevicesListView.setOnKeyListener(new OnKeyListener(){

			@Override
			public boolean onKey(View arg0, int arg1, KeyEvent arg2) {
				// TODO Auto-generated method stub
				if (arg1 == KeyEvent.KEYCODE_VOLUME_DOWN){
					send(testcmdBytes);
				}
				if (arg1 == KeyEvent.KEYCODE_VOLUME_UP){
					send(testcmdBytes);
				}
				return false;
			}});
	}
	
	private boolean hasBluetoothAdapter(){
		if (deviceBluetoothAdapter == null) {
			return false;
		}
		return true;
	}
	
	public boolean isBluetoothEnabled(){	
		return deviceBluetoothAdapter.isEnabled();
	}	
	
	public void getDevice() {	
		
		// If there are paired devices
		if (pairedDevices.size() > 0) {
			findViewById(R.id.title_paired_devices).setVisibility(View.VISIBLE);
		    // Loop through paired devices
		    for (BluetoothDevice device : pairedDevices) {
		        // Add the name and address to an array adapter to show in a ListView
		        newArrayAdapter.add(device.getName() + "\n" + device.getAddress());
		    }
		}else {
			String noDevices = getResources().getText(R.string.none_paired).toString();
			newArrayAdapter.add(noDevices);
		}
	}
	
	//Method that searches for devices
	private void discoverDevices() {
		// Turn on sub-title for new devices
		findViewById(R.id.title_new_devices).setVisibility(View.VISIBLE);

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
		// if to cancel the connection thread
		if(maintainCnt != null) {
			maintainCnt.cancel();
		}
		maintainCnt = new ConnectedThread(socket);
		maintainCnt.start();
		/*
		Intent i = new Intent(getApplicationContext(), PresentationActivity.class);
		startActivity(i);*/
	}
	
	//Public send method for other classes to use when sending over to the server
	public static void send(String cmd) {
		//If there is a connection : send
		byte[] commandBuffer = cmd.getBytes();
		byte[] commandBufferWithEnding= new byte[commandBuffer.length+1];
		System.arraycopy(commandBuffer, 0, commandBufferWithEnding, 0, commandBuffer.length);
		if(maintainCnt != null) {
		    commandBufferWithEnding[commandBufferWithEnding.length-1] = -2;
			maintainCnt.write(commandBufferWithEnding);
		}else{
//			Toast.makeText(getApplicationContext(), "No active connection", Toast.LENGTH_SHORT).show();
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
		
		ConnectedThread(BluetoothSocket socket){
			btSocket = socket;
			try{
				os = socket.getOutputStream();
			}catch(IOException e){
				Toast.makeText(getApplicationContext(), "Error at outputing", Toast.LENGTH_SHORT).show();
			}
		}
		
		public void run(){
			if (deviceBluetoothAdapter.isDiscovering()) {
			deviceBluetoothAdapter.cancelDiscovery();
		}
			byte[] buffer = new byte[1024]; 
			int bytes;
		}
		
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