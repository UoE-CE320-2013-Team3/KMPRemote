package com.bignerdranch.android.presentation;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

public class RemoteBluetoothClient{
	UUID uuid = new UUID(0, 80087355);
	
	
	private	BluetoothAdapter deviceBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	private	Set<BluetoothDevice> pairedDevices = deviceBluetoothAdapter.getBondedDevices();
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
	
//	public void turnBluetoothOn(){
//		if (!isBluetoothEnabled()) {
//			Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//			startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
//		}
//	}	

	public void connect(BluetoothDevice device){
		// check if there is a connection : terminate
		if(ct != null) {
			ct.cancel();
		}
		ct = new ConnectThread(device);
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
//			btDevice = device;
//			try{
//				btSocket = btDevice.createInsecureRfcommSocketToServiceRecord(uuid);
//				
//			}catch(IOException e){
//		
//			}
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
