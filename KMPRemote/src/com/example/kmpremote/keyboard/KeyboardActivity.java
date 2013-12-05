package com.example.kmpremote.keyboard;

import com.example.kmpremote.R;
import com.example.kmpremote.bluetoothclient.RemoteBluetoothClient;
import com.example.kmpremote.mouse.DisplayMousePad;
import com.example.kmpremote.presentation.PresentationActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class KeyboardActivity extends Activity {
	private Spinner spinner1;
	//RemoteBluetoothClient kClient;
	//ConnectedThread t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.keyboard_display);
        //kClient = new RemoteBluetoothClient();
        //kClient.connect();      
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }	
    //need to still implement these methods to send data to the bluetooth client class!
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
				
				if(selected.equals("Mouse")){
					Intent m = new Intent(getApplicationContext(),DisplayMousePad.class);
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
    		
    	});
      }
    
	String cmd;
	/*
    public void keyboardSend(View v){
		byte[] buffer = cmd.getBytes();
    	//kClient.send(buffer);
    }*/
    
	public void KeyboardToggleA(View v) {
        cmd = "KEYBOARD TOGGLE a";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleB(View v) {
        cmd = "KEYBOARD TOGGLE b";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleC(View v) {
        cmd = "KEYBOARD TOGGLE c";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleD(View v) {
        cmd = "KEYBOARD TOGGLE d";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleE(View v) {
        cmd = "KEYBOARD TOGGLE e";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleF(View v) {
        cmd = "KEYBOARD TOGGLE f";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleG(View v) {
        cmd = "KEYBOARD TOGGLE g";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleH(View v) {
        cmd = "KEYBOARD TOGGLE h";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleI(View v) {
        cmd = "KEYBOARD TOGGLE i";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleJ(View v) {
        cmd = "KEYBOARD TOGGLE j";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleK(View v) {
        cmd = "KEYBOARD TOGGLE k";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleL(View v) {
        cmd = "KEYBOARD TOGGLE l";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleM(View v) {
        cmd = "KEYBOARD TOGGLE m";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleN(View v) {
        cmd = "KEYBOARD TOGGLE n";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleO(View v) {
        cmd = "KEYBOARD TOGGLE o";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleP(View v) {
        cmd = "KEYBOARD TOGGLE p";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleQ(View v) {
        cmd = "KEYBOARD TOGGLE q";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleR(View v) {
        cmd = "KEYBOARD TOGGLE r";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleS(View v) {
        cmd = "KEYBOARD TOGGLE s";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleT(View v) {
        cmd = "KEYBOARD TOGGLE t";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleU(View v) {
        cmd = "KEYBOARD TOGGLE u";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleV(View v) {
        cmd = "KEYBOARD TOGGLE v";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleW(View v) {
        cmd = "KEYBOARD TOGGLE w";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleX(View v) {
        cmd = "KEYBOARD TOGGLE x";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleY(View v) {
        cmd = "KEYBOARD TOGGLE y";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleZ(View v) {
        cmd = "KEYBOARD TOGGLE z";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleSPACE(View v) {
        cmd = "KEYBOARD TOGGLE SPACE";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleCAPS(View v) {
        cmd = "KEYBOARD TOGGLE CAPS";
        RemoteBluetoothClient.send(cmd);
    }
    
    public void KeyboardToggleBACKSPACE(View v) {
        cmd = "KEYBOARD TOGGLE BACKSPACE";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleF5(View v) {
        cmd = "KEYBOARD TOGGLE F5";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleF6(View v) {
        cmd = "KEYBOARD TOGGLE F6";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleRIGHTARROW(View v) {
        cmd = "KEYBOARD TOGGLE RIGHTARROW";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleLEFTARROW(View v) {
        cmd = "KEYBOARD TOGGLE LEFTARROW";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardTogglePAGEUP(View v) {
        cmd = "KEYBOARD TOGGLE PAGEUP";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardTogglePAGEDOWN(View v) {
        cmd = "KEYBOARD TOGGLE PAGEDOWN";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleESC(View v) {
        cmd = "KEYBOARD TOGGLE ESC";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleENTER(View v) {
        cmd = "KEYBOARD TOGGLE ENTER";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleTAB(View v) {
        cmd = "KEYBOARD TOGGLE TAB";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggle1(View v) {
        cmd = "KEYBOARD TOGGLE 1";
        RemoteBluetoothClient.send(cmd);
    }
    
    public void KeyboardToggle2(View v) {
        cmd = "KEYBOARD TOGGLE 2";
        RemoteBluetoothClient.send(cmd);
    }
    
    public void KeyboardToggle3(View v) {
        cmd = "KEYBOARD TOGGLE 3";
        RemoteBluetoothClient.send(cmd);
    }
    
    public void KeyboardToggle4(View v) {
        cmd = "KEYBOARD TOGGLE 4";
        RemoteBluetoothClient.send(cmd);
    }
    
    public void KeyboardToggle5(View v) {
        cmd = "KEYBOARD TOGGLE 5";
        RemoteBluetoothClient.send(cmd);
    }
    
    public void KeyboardToggle6(View v) {
        cmd = "KEYBOARD TOGGLE 6";
        RemoteBluetoothClient.send(cmd);
    }
    
    public void KeyboardToggle7(View v) {
        cmd = "KEYBOARD TOGGLE 7";
        RemoteBluetoothClient.send(cmd);
    }
    
    public void KeyboardToggle8(View v) {
        cmd = "KEYBOARD TOGGLE 8";
        RemoteBluetoothClient.send(cmd);
        
    }
    
    public void KeyboardToggle9(View v) {
        cmd = "KEYBOARD TOGGLE 9";
        RemoteBluetoothClient.send(cmd);
    }
    
    public void KeyboardToggle0(View v) {
        cmd = "KEYBOARD TOGGLE 0";
        RemoteBluetoothClient.send(cmd);
    }
}