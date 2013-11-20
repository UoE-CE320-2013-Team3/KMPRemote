package com.example.bluetoothconnection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.widget.Toast;

public class RemoteBluetoothClient{
	UUID uuid = new UUID(0, 80087355);
	
	
	private	BluetoothAdapter deviceBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	private	Set<BluetoothDevice> pairedDevices = deviceBluetoothAdapter.getBondedDevices();
	private BluetoothDevice btDevice;
	//threads
	private ConnectThread ct;
	private ConnectedThread cnt;
	
	public RemoteBluetoothClient(){			
			if(hasBluetoothAdapter()){
				if(!isBluetoothEnabled()){
					//warning mes
					//Toast.makeText(this, "Turn Bluetooth on", Toast.LENGTH_SHORT).show();
				}
			}else{
				//display message
			}			
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
		boolean discovered = deviceBluetoothAdapter.startDiscovery();
		
		final BroadcastReceiver mReceiver = new BroadcastReceiver() {
		    public void onReceive(Context context, Intent intent) {
		        String action = intent.getAction();
		        // When discovery finds a device
		        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
		            // Get the BluetoothDevice object from the Intent
		            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
		            Log.d("DeviceList" , device.getName() + "\n" + device.getAddress());
		            btDevice = device;
		            // Add the name and address to an array adapter to show in a ListView
		            //mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
		        }
		    }
		};
		// Register the BroadcastReceiver
		//IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
		//registerReceiver(mReceiver, filter); // Don't forget to unregister during onDestroy
		
	}
	
//	public void turnBluetoothOn(){
//		if (!isBluetoothEnabled()) {
//			Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//			startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
//		}
//	}	

	public void connect(){
		// check if there is a connection : terminate
		getDevice();
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
	
	
	//creates a socket and connects to the server
	private class ConnectThread extends Thread{
		private BluetoothDevice btDevice;
		private BluetoothSocket btSocket;
		
		public ConnectThread(BluetoothDevice device){
			btDevice = device;
			try{
				btSocket = btDevice.createInsecureRfcommSocketToServiceRecord(uuid);
				
			}catch(IOException e){
		
			}
		}
		
		public void run(){
			deviceBluetoothAdapter.cancelDiscovery();
			try {
				btSocket.connect();
			} catch (IOException e) {
				try{
					btSocket.close();
				}catch(IOException ex){
					
				}
				e.printStackTrace();
			}
			connected(btDevice,btSocket);
		}
		
		public void cancel(){
			try {
				btSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	//uses the created 
	private class ConnectedThread extends Thread{
		BluetoothSocket btSocket;
		OutputStream os;
		
		ConnectedThread(BluetoothSocket socket){
			btSocket = socket;
			os = null;
			try{
				os = socket.getOutputStream();
			}catch(IOException e){
				//fill me out!	
			}
		}
		public void run(){
			byte[] buffer = new byte[1024];
			int bytes;
		}
		public void write(byte[] buffer){
			try {
				os.write(buffer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void cancel(){
			try {
				btSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
