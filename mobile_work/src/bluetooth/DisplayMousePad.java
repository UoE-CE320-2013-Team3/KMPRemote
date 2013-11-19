package com.example.remotemouse;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class DisplayMousePad extends Activity {
	//RemoteBluetoothClient mclient;
	//ConnectedThread t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mousepad_display);
        //mclient = new RemoteBluetoothClient();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void moveUp(View v){
    	//call the bluetooth class and send MOVE_UP command
    	
    }
    
    public void moveDown(View v){
    	
    }

	public void moveLeft(View v){
	
	}

	public void moveRight(View v){
	
	}

	public void leftClick(View v){
	
	}

	public void rightClick(View v){
	
	}
    
}
