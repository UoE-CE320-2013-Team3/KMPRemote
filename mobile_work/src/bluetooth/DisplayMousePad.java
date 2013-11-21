package com.example.remotemouse;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class DisplayMousePad extends Activity {
	RemoteBluetoothClient mclient;
	//ConnectedThread t;
	public static String cmd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mousepad_display);
        //mclient = new RemoteBluetoothClient();
        //mclient.mArrayAdapter = new ArrayAdapter<String>(this,R.layout.mousepad_display);
        //ListView newDevicesListView = (ListView)()
        //mclient.connect();
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    //need to still implement these methods to send data to the bluetooth client class!
    public void moveUp(View v){
    	//call the bluetooth class and send MOVE_UP command
    	cmd = "MOUSE MOVE UP 20";
    	byte[] buffer = cmd.getBytes();
    	//mclient.send(buffer);
    	Toast.makeText(this,  "Message sent!"+cmd, Toast.LENGTH_LONG).show();
    	
    }
    
    public void moveDown(View v){
    	cmd = "MOUSE MOVE DOWN 20";
    	byte[] buffer = cmd.getBytes();
    	//mclient.send(buffer);
    	Toast.makeText(this,  "Message sent!"+cmd, Toast.LENGTH_LONG).show();
    	
    	
    }

	public void moveLeft(View v){
		String cmd = "MOUSE MOVE LEFT 20";
    	byte[] buffer = cmd.getBytes();
    	//mclient.send(buffer);
    	Toast.makeText(this,  "Message sent!"+cmd, Toast.LENGTH_LONG).show();
	
	}

	public void moveRight(View v){
		String cmd = "MOUSE MOVE RIGHT 20";
    	byte[] buffer = cmd.getBytes();
    	//mclient.send(buffer);
    	Toast.makeText(this,  "Message sent!"+cmd, Toast.LENGTH_LONG).show();
	
	}

	public void leftClick(View v){
		String cmd = "MOUSE LEFT CLICK";
    	byte[] buffer = cmd.getBytes();
    	//mclient.send(buffer);
    	Toast.makeText(this,  "Message sent!"+ cmd, Toast.LENGTH_LONG).show();
	
	}

	public void rightClick(View v){
		String cmd = "MOUSE RIGHT CLICK";
    	byte[] buffer = cmd.getBytes();
    	//mclient.send(buffer);
    	Toast.makeText(this,  "Message sent!"+cmd, Toast.LENGTH_LONG).show();
	
	}
    
}
