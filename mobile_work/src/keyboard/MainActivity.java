package com.example.keyboard;

import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	//RemoteBluetoothClient kClient;
	//ConnectedThread t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
	
	String cmd;
    public void keyboardSend(View v){
		byte[] buffer = cmd.getBytes();
    	//kClient.send(buffer);
    }
    
	public void KeyboardToggleA(View v) {
        cmd = "KEYBOARD TOGGLE a";
        keyboardSend(v);
        Toast.makeText(MainActivity.this, R.string.A_Toast, Toast.LENGTH_SHORT).show();
    }
    public void KeyboardToggleB(View v) {
        cmd = "KEYBOARD TOGGLE b";
        keyboardSend(v);
        Toast.makeText(MainActivity.this, R.string.B_Toast, Toast.LENGTH_SHORT).show();
    }
    public void KeyboardToggleC(View v) {
        cmd = "KEYBOARD TOGGLE c";
        keyboardSend(v);
        Toast.makeText(MainActivity.this, R.string.C_Toast, Toast.LENGTH_SHORT).show();
    }
    public void KeyboardToggleD(View v) {
        cmd = "KEYBOARD TOGGLE d";
        keyboardSend(v);
        Toast.makeText(MainActivity.this, R.string.D_Toast, Toast.LENGTH_SHORT).show();
    }
    public void KeyboardToggleE(View v) {
        cmd = "KEYBOARD TOGGLE e";
        keyboardSend(v);
        Toast.makeText(MainActivity.this, R.string.E_Toast, Toast.LENGTH_SHORT).show();
    }
    public void KeyboardToggleF(View v) {
        cmd = "KEYBOARD TOGGLE f";
        keyboardSend(v);
    }
    public void KeyboardToggleG(View v) {
        cmd = "KEYBOARD TOGGLE g";
        keyboardSend(v);
    }
    public void KeyboardToggleH(View v) {
        cmd = "KEYBOARD TOGGLE h";
        keyboardSend(v);
    }
    public void KeyboardToggleI(View v) {
        cmd = "KEYBOARD TOGGLE i";
        keyboardSend(v);
    }
    public void KeyboardToggleJ(View v) {
        cmd = "KEYBOARD TOGGLE j";
        keyboardSend(v);
    }
    public void KeyboardToggleK(View v) {
        cmd = "KEYBOARD TOGGLE k";
        keyboardSend(v);
    }
    public void KeyboardToggleL(View v) {
        cmd = "KEYBOARD TOGGLE l";
        keyboardSend(v);
    }
    public void KeyboardToggleM(View v) {
        cmd = "KEYBOARD TOGGLE m";
        keyboardSend(v);
    }
    public void KeyboardToggleN(View v) {
        cmd = "KEYBOARD TOGGLE n";
        keyboardSend(v);
    }
    public void KeyboardToggleO(View v) {
        cmd = "KEYBOARD TOGGLE o";
        keyboardSend(v);
    }
    public void KeyboardToggleP(View v) {
        cmd = "KEYBOARD TOGGLE p";
        keyboardSend(v);
    }
    public void KeyboardToggleQ(View v) {
        cmd = "KEYBOARD TOGGLE q";
        keyboardSend(v);
    }
    public void KeyboardToggleR(View v) {
        cmd = "KEYBOARD TOGGLE r";
        keyboardSend(v);
    }
    public void KeyboardToggleS(View v) {
        cmd = "KEYBOARD TOGGLE s";
        keyboardSend(v);
    }
    public void KeyboardToggleT(View v) {
        cmd = "KEYBOARD TOGGLE t";
        keyboardSend(v);
    }
    public void KeyboardToggleU(View v) {
        cmd = "KEYBOARD TOGGLE u";
        keyboardSend(v);
    }
    public void KeyboardToggleV(View v) {
        cmd = "KEYBOARD TOGGLE v";
        keyboardSend(v);
    }
    public void KeyboardToggleW(View v) {
        cmd = "KEYBOARD TOGGLE w";
        keyboardSend(v);
    }
    public void KeyboardToggleX(View v) {
        cmd = "KEYBOARD TOGGLE x";
        keyboardSend(v);
    }
    public void KeyboardToggleY(View v) {
        cmd = "KEYBOARD TOGGLE y";
        keyboardSend(v);
    }
    public void KeyboardToggleZ(View v) {
        cmd = "KEYBOARD TOGGLE z";
        keyboardSend(v);
    }
    public void KeyboardToggleSPACE(View v) {
        cmd = "KEYBOARD TOGGLE SPACE";
        keyboardSend(v);
    }
    public void KeyboardToggleCAPS(View v) {
        cmd = "KEYBOARD TOGGLE CAPS";
        keyboardSend(v);
    }
    
    public void KeyboardToggleBACKSPACE(View v) {
        cmd = "KEYBOARD TOGGLE BACKSPACE";
        keyboardSend(v);
    }
    public void KeyboardToggleF5(View v) {
        cmd = "KEYBOARD TOGGLE F5";
        keyboardSend(v);
    }
    public void KeyboardToggleF6(View v) {
        cmd = "KEYBOARD TOGGLE F6";
        keyboardSend(v);
    }
    public void KeyboardToggleRIGHTARROW(View v) {
        cmd = "KEYBOARD TOGGLE RIGHTARROW";
        keyboardSend(v);
    }
    public void KeyboardToggleLEFTARROW(View v) {
        cmd = "KEYBOARD TOGGLE LEFTARROW";
        keyboardSend(v);
    }
    public void KeyboardTogglePAGEUP(View v) {
        cmd = "KEYBOARD TOGGLE PAGEUP";
        keyboardSend(v);
    }
    public void KeyboardTogglePAGEDOWN(View v) {
        cmd = "KEYBOARD TOGGLE PAGEDOWN";
        keyboardSend(v);
    }
    public void KeyboardToggleESC(View v) {
        cmd = "KEYBOARD TOGGLE ESC";
        keyboardSend(v);
    }
    public void KeyboardToggleENTER(View v) {
        cmd = "KEYBOARD TOGGLE ENTER";
        keyboardSend(v);
    }
    public void KeyboardToggleTAB(View v) {
        cmd = "KEYBOARD TOGGLE TAB";
        keyboardSend(v);
    }
    public void KeyboardToggle1(View v) {
        cmd = "KEYBOARD TOGGLE 1";
        keyboardSend(v);
        Toast.makeText(MainActivity.this, R.string.ONE_Toast, Toast.LENGTH_SHORT).show();
    }
    
    public void KeyboardToggle2(View v) {
        cmd = "KEYBOARD TOGGLE 2";
        keyboardSend(v);
    }
    
    public void KeyboardToggle3(View v) {
        cmd = "KEYBOARD TOGGLE 3";
        keyboardSend(v);
    }
    
    public void KeyboardToggle4(View v) {
        cmd = "KEYBOARD TOGGLE 4";
        keyboardSend(v);
    }
    
    public void KeyboardToggle5(View v) {
        cmd = "KEYBOARD TOGGLE 5";
        keyboardSend(v);
    }
    
    public void KeyboardToggle6(View v) {
        cmd = "KEYBOARD TOGGLE 6";
        keyboardSend(v);
    }
    
    public void KeyboardToggle7(View v) {
        cmd = "KEYBOARD TOGGLE 7";
        keyboardSend(v);
    }
    
    public void KeyboardToggle8(View v) {
        cmd = "KEYBOARD TOGGLE 8";
        keyboardSend(v);
        
    }
    
    public void KeyboardToggle9(View v) {
        cmd = "KEYBOARD TOGGLE 9";
        keyboardSend(v);
    }
    
    public void KeyboardToggle0(View v) {
        cmd = "KEYBOARD TOGGLE 0";
        keyboardSend(v);
    }
}