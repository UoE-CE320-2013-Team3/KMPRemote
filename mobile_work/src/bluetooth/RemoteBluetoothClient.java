package com.example.bluetoothconnection;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

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
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RemoteBluetoothClient extends Activity{
	static UUID uuid = UUID.fromString("04c6093b-0000-1000-8000-00805f9b34fb");
	
	//Variables for the adapter(phone's Bluetooth) and device(server Bluetooth).
	private	BluetoothAdapter deviceBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	private BluetoothDevice btDevice;
	private	Set<BluetoothDevice> pairedDevices = deviceBluetoothAdapter.getBondedDevices();	
	public ArrayAdapter<String> mArrayAdapter;
//	private BroadcastReceiver mReceiver;
	
	//threads
	private ConnectThread ct; //thread that creates the connection
	private ConnectedThread cnt; //thread that manages the connection
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mArrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_main);
		ListView newDevicesListView = (ListView)findViewById(R.id.listView1);
		newDevicesListView.setAdapter(mArrayAdapter);
		
		IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
		registerReceiver(mReceiver, filter); // Don't forget to unregister during onDestroy		
		
		filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
		registerReceiver(mReceiver, filter);
		
		if(hasBluetoothAdapter()){
			if(!isBluetoothEnabled()){
				//display message
			}
		}else{
			//display message
			getDevice();				
		}	
		newDevicesListView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(btDevice == null) {
					//error
				} else {
					connect(btDevice);
				}
				
			}
			
			
		});
	}
	
//	public RemoteBluetoothClient(){		
//				
//	}
	
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
		    // Loop through paired devices
		    for (BluetoothDevice device : pairedDevices) {
		        // Add the name and address to an array adapter to show in a ListView
		        mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
		    }
		}
	}

	public void connect(BluetoothDevice device){
		btDevice = device;
		// check if there is a connection : terminate
		if(ct != null) {
			ct.cancel();
		}
		ct = new ConnectThread(btDevice);
		ct.start();
	}
	
	private void connected(BluetoothDevice device, BluetoothSocket socket){
		// if to cancel the connection thread
		if(cnt != null) {
			cnt.cancel();
		}
		// if to cancel any thread running a connection
		cnt = new ConnectedThread(socket);
		cnt.start();
	}
	
	public void send(byte[] buffer) {
		if(cnt != null) {
			cnt.write(buffer);
		}else{
			//TODO message: no connectedThread. (connect() to start a new connection)
		}		
	}	
	
	//FIX THE RECEIVER!
	// Create a BroadcastReceiver for ACTION_FOUND
	private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
	    public void onReceive(Context context, Intent intent) {
	        String action = intent.getAction();
	        // When discovery finds a device
	        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
	            // Get the BluetoothDevice object from the Intent
	            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
	            // Add the name and address to an array adapter to show in a ListView
	            mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
	        }
	    }
	};
	
	//creates a socket and connects to the server
	private class ConnectThread extends Thread{
		private BluetoothDevice btDevice;
		private BluetoothSocket btSocket;
		
		public ConnectThread(BluetoothDevice device){
			btDevice = device;
			try{
				btSocket = btDevice.createInsecureRfcommSocketToServiceRecord(uuid);				
			}catch(IOException e){}
		}
		
		public void run(){
			deviceBluetoothAdapter.cancelDiscovery();
			try {
				btSocket.connect();
			} catch (IOException e) {
				try{
					btSocket.close();
				}catch(IOException ex){}
			}
			connected(btDevice,btSocket);
		}
		
		public void cancel(){
			try {
				btSocket.close();
			} catch (IOException e) {}
		}

	}

	//uses the created connection
	private class ConnectedThread extends Thread{
		BluetoothSocket btSocket;
		OutputStream os;
		
		ConnectedThread(BluetoothSocket socket){
			btSocket = socket;
			os = null;
			try{
				os = socket.getOutputStream();
			}catch(IOException e){}
		}
		
		public void run(){
			byte[] buffer = new byte[1024];
			int bytes;
		}
		
		public void write(byte[] buffer){
			try {
				os.write(buffer);
			} catch (IOException e) {}
		}
		
		public void cancel(){
			try {
				btSocket.close();
			} catch (IOException e) {}
		}
	}
}
