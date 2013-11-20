package com.example.remotemouse;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class DisplayKeyboard extends Activity {
	RemoteBluetoothClient kClient;
	//ConnectedThread t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.mousepad_display);
        kClient = new RemoteBluetoothClient();
        kClient.connect();
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
	
	
    //need to still implement these methods to send data to the bluetooth client class!
	
	String cmd;
    public void keyboardToggle(View v){
		byte[] buffer = cmd.getBytes();
    	kClient.send(buffer);
    }
    
	public void KeyboardToggleA(View v) {
        cmd = "KEYBOARD TOGGLE a";
        keyboardToggle(v);
    }

    public void KeyboardToggleB(View v) {
        cmd = "KEYBOARD TOGGLE b";
        keyboardToggle(v);
    }

    public void KeyboardToggleC(View v) {
        cmd = "KEYBOARD TOGGLE c";
        keyboardToggle(v);
    }

    public void KeyboardToggleD(View v) {
        cmd = "KEYBOARD TOGGLE d";
        keyboardToggle(v);
    }

    public void KeyboardToggleE(View v) {
        cmd = "KEYBOARD TOGGLE e";
        keyboardToggle(v);
    }

    public void KeyboardToggleF(View v) {
        cmd = "KEYBOARD TOGGLE f";
        keyboardToggle(v);
    }

    public void KeyboardToggleG(View v) {
        cmd = "KEYBOARD TOGGLE g";
        keyboardToggle(v);
    }

    public void KeyboardToggleH(View v) {
        cmd = "KEYBOARD TOGGLE h";
        keyboardToggle(v);
    }

    public void KeyboardToggleI(View v) {
        cmd = "KEYBOARD TOGGLE i";
        keyboardToggle(v);
    }

    public void KeyboardToggleJ(View v) {
        cmd = "KEYBOARD TOGGLE j";
        keyboardToggle(v);
    }

    public void KeyboardToggleK(View v) {
        cmd = "KEYBOARD TOGGLE k";
        keyboardToggle(v);
    }

    public void KeyboardToggleL(View v) {
        cmd = "KEYBOARD TOGGLE l";
        keyboardToggle(v);
    }

    public void KeyboardToggleM(View v) {
        cmd = "KEYBOARD TOGGLE m";
        keyboardToggle(v);
    }

    public void KeyboardToggleN(View v) {
        cmd = "KEYBOARD TOGGLE n";
        keyboardToggle(v);
    }

    public void KeyboardToggleO(View v) {
        cmd = "KEYBOARD TOGGLE o";
        keyboardToggle(v);
    }

    public void KeyboardToggleP(View v) {
        cmd = "KEYBOARD TOGGLE p";
        keyboardToggle(v);
    }

    public void KeyboardToggleQ(View v) {
        cmd = "KEYBOARD TOGGLE q";
        keyboardToggle(v);
    }

    public void KeyboardToggleR(View v) {
        cmd = "KEYBOARD TOGGLE r";
        keyboardToggle(v);
    }

    public void KeyboardToggleS(View v) {
        cmd = "KEYBOARD TOGGLE s";
        keyboardToggle(v);
    }

    public void KeyboardToggleT(View v) {
        cmd = "KEYBOARD TOGGLE t";
        keyboardToggle(v);
    }

    public void KeyboardToggleU(View v) {
        cmd = "KEYBOARD TOGGLE u";
        keyboardToggle(v);
    }

    public void KeyboardToggleV(View v) {
        cmd = "KEYBOARD TOGGLE v";
        keyboardToggle(v);
    }

    public void KeyboardToggleW(View v) {
        cmd = "KEYBOARD TOGGLE w";
        keyboardToggle(v);
    }

    public void KeyboardToggleX(View v) {
        cmd = "KEYBOARD TOGGLE x";
        keyboardToggle(v);
    }

    public void KeyboardToggleY(View v) {
        cmd = "KEYBOARD TOGGLE y";
        keyboardToggle(v);
    }

    public void KeyboardToggleZ(View v) {
        cmd = "KEYBOARD TOGGLE z";
        keyboardToggle(v);
    }

    public void KeyboardToggleSPACE(View v) {
        cmd = "KEYBOARD TOGGLE SPACE";
        keyboardToggle(v);
    }

    public void KeyboardToggleBACKSPACE(View v) {
        cmd = "KEYBOARD TOGGLE BACKSPACE";
        keyboardToggle(v);
    }

    public void KeyboardToggleF5(View v) {
        cmd = "KEYBOARD TOGGLE F5";
        keyboardToggle(v);
    }

    public void KeyboardToggleF6(View v) {
        cmd = "KEYBOARD TOGGLE F6";
        keyboardToggle(v);
    }

    public void KeyboardToggleRIGHTARROW(View v) {
        cmd = "KEYBOARD TOGGLE RIGHTARROW";
        keyboardToggle(v);
    }

    public void KeyboardToggleLEFTARROW(View v) {
        cmd = "KEYBOARD TOGGLE LEFTARROW";
        keyboardToggle(v);
    }

    public void KeyboardTogglePAGEUP(View v) {
        cmd = "KEYBOARD TOGGLE PAGEUP";
        keyboardToggle(v);
    }

    public void KeyboardTogglePAGEDOWN(View v) {
        cmd = "KEYBOARD TOGGLE PAGEDOWN";
        keyboardToggle(v);
    }

    public void KeyboardToggleESC(View v) {
        cmd = "KEYBOARD TOGGLE ESC";
        keyboardToggle(v);
    }

    public void KeyboardToggleENTER(View v) {
        cmd = "KEYBOARD TOGGLE ENTER";
        keyboardToggle(v);
    }

    public void KeyboardToggleTAB(View v) {
        cmd = "KEYBOARD TOGGLE TAB";
        keyboardToggle(v);
    }

    public void KeyboardHoldALT(View v) {
        cmd = "KEYBOARD HOLD ALT";
        keyboardToggle(v);
    }

    public void KeyboardHoldCTRL(View v) {
        cmd = "KEYBOARD HOLD CTRL";
        keyboardToggle(v);
    }

    public void KeyboardHoldSHIFT(View v) {
        cmd = "KEYBOARD HOLD SHIFT";
        keyboardToggle(v);
    }
}
