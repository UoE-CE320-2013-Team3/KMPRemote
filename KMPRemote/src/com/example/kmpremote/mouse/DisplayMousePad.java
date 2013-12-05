package com.example.kmpremote.mouse;

import com.example.kmpremote.R;
import com.example.kmpremote.bluetoothclient.RemoteBluetoothClient;
import com.example.kmpremote.keyboard.KeyboardActivity;
import com.example.kmpremote.presentation.PresentationActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class DisplayMousePad extends Activity {
	
	//ConnectedThread t;
	public static String cmd;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mousepad_display);
        getActionBar().setDisplayHomeAsUpEnabled(true);        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    	MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.keyboard:
                Intent keyAct = new Intent(this, KeyboardActivity.class);
                startActivity(keyAct);
                return true;
            case R.id.presentation:
                Intent presAct = new Intent(this, PresentationActivity.class);
                startActivity(presAct);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    
    //need to still implement these methods to send data to the bluetooth client class!
    public void moveUp(View v){
    	//call the bluetooth class and send MOVE_UP command
    	cmd = "MOUSE MOVE UP 20";
    	RemoteBluetoothClient.send(cmd);    	
    }
    
    public void moveDown(View v){
    	cmd = "MOUSE MOVE DOWN 20";
    	RemoteBluetoothClient.send(cmd);    	
    }

	public void moveLeft(View v){
		String cmd = "MOUSE MOVE LEFT 20";    	
    	RemoteBluetoothClient.send(cmd);	
	}

	public void moveRight(View v){
		String cmd = "MOUSE MOVE RIGHT 20";    	
    	RemoteBluetoothClient.send(cmd);	
	}

	public void leftClick(View v){
		String cmd = "MOUSE TOGGLE leftclick";
    	RemoteBluetoothClient.send(cmd);	
	}

	public void rightClick(View v){
		String cmd = "MOUSE TOGGLE rightclick";  
    	RemoteBluetoothClient.send(cmd);	
	}
    
}
