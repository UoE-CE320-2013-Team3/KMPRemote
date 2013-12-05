package com.example.kmpremote.mouse;

import com.example.kmpremote.R;
import com.example.kmpremote.bluetoothclient.RemoteBluetoothClient;
import com.example.kmpremote.keyboard.KeyboardActivity;
import com.example.kmpremote.presentation.PresentationActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
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
	private Spinner spinner1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mousepad_display);
        //mclient = new RemoteBluetoothClient();
        //mclient.mArrayAdapter = new ArrayAdapter<String>(this,R.layout.mousepad_display);
        //ListView newDevicesListView = (ListView)()
        //mclient.connect();
        addListenerOnSpinnerItemSelection();
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void addListenerOnSpinnerItemSelection() {
    	spinner1 = (Spinner) findViewById(R.id.spinner1);
    	spinner1.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				String selected = arg0.getItemAtPosition(arg2).toString();
				if(selected.equals("Presentation")){
					Intent m = new Intent(getApplicationContext(),PresentationActivity.class);
					startActivity(m);
				}
				
				if(selected.equals("Keyboard")){
					Intent m = new Intent(getApplicationContext(),KeyboardActivity.class);
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
    		
    	});
      }
    //need to still implement these methods to send data to the bluetooth client class!
    public void moveUp(View v){
    	//call the bluetooth class and send MOVE_UP command
    	cmd = "MOUSE MOVE UP 20";
    	RemoteBluetoothClient.send(cmd);
    	Toast.makeText(this,  "Message sent!"+cmd, Toast.LENGTH_LONG).show();
    	
    }
    
    public void moveDown(View v){
    	cmd = "MOUSE MOVE DOWN 20";
    	RemoteBluetoothClient.send(cmd);
    	Toast.makeText(this,  "Message sent!"+cmd, Toast.LENGTH_LONG).show();
    	
    	
    }

	public void moveLeft(View v){
		String cmd = "MOUSE MOVE LEFT 20";
    	
    	RemoteBluetoothClient.send(cmd);
    	Toast.makeText(this,  "Message sent!"+cmd, Toast.LENGTH_LONG).show();
	
	}

	public void moveRight(View v){
		String cmd = "MOUSE MOVE RIGHT 20";
    	
    	RemoteBluetoothClient.send(cmd);
    	Toast.makeText(this,  "Message sent!"+cmd, Toast.LENGTH_LONG).show();
	
	}

	public void leftClick(View v){
		String cmd = "MOUSE TOGGLE leftclick";
    	RemoteBluetoothClient.send(cmd);
    	Toast.makeText(this,  "Message sent!"+ cmd, Toast.LENGTH_LONG).show();
	
	}

	public void rightClick(View v){
		String cmd = "MOUSE TOGGLE rightclick";
  
    	RemoteBluetoothClient.send(cmd);
    	Toast.makeText(this,  "Message sent!"+cmd, Toast.LENGTH_LONG).show();
	
	}
    
}
