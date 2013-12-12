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
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Symbolkey extends Activity {
	//RemoteBluetoothClient kClient;
	//ConnectedThread t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        DisplayMousePad.ActiveSensor = false;
        setContentView(R.layout.symkey);
        getActionBar().setDisplayHomeAsUpEnabled(true);      
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    	MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }	
    //need to still implement these methods to send data to the bluetooth client class!
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.mouse:
                Intent keyAct = new Intent(this, DisplayMousePad.class);
                startActivity(keyAct);
                finish();
                return true;
            case R.id.presentation:
                Intent presAct = new Intent(this, PresentationActivity.class);
                startActivity(presAct);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
	String cmd;
    
    //F1-F12
    
    public void KeyboardToggleF1(View v) {
        cmd = "KEYBOARD TOGGLE F1";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleF2(View v) {
        cmd = "KEYBOARD TOGGLE F2";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleF3(View v) {
        cmd = "KEYBOARD TOGGLE F3";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleF4(View v) {
        cmd = "KEYBOARD TOGGLE F4";
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
    public void KeyboardToggleF7(View v) {
        cmd = "KEYBOARD TOGGLE F7";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleF8(View v) {
        cmd = "KEYBOARD TOGGLE F8";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleF9(View v) {
        cmd = "KEYBOARD TOGGLE F9";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleF10(View v) {
        cmd = "KEYBOARD TOGGLE F10";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleF11(View v) {
        cmd = "KEYBOARD TOGGLE F11";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleF12(View v) {
        cmd = "KEYBOARD TOGGLE F12";
        RemoteBluetoothClient.send(cmd);
    }
    
    //Arrows
    
    public void KeyboardToggleRIGHTARROW(View v) {
        cmd = "KEYBOARD TOGGLE RIGHTARROW";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleLEFTARROW(View v) {
        cmd = "KEYBOARD TOGGLE LEFTARROW";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleUPARROW(View v) {
        cmd = "KEYBOARD TOGGLE UPARROW";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleDOWNARROW(View v) {
        cmd = "KEYBOARD TOGGLE DOWNARROW";
        RemoteBluetoothClient.send(cmd);
    }
    
    //Special
    
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
    
    public void KeyboardToggleCOPY(View v) {
        cmd = "KEYBOARD TOGGLE COPY";
        RemoteBluetoothClient.send(cmd);
    } 
    
    //0-9
    
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
    
    //Punctuation
      
    public void KeyboardToggleCOMMA(View v) {
        cmd = "KEYBOARD TOGGLE ,";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleSLASH(View v) {
        cmd = "KEYBOARD TOGGLE /";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleBSLASH(View v) {
        cmd = "KEYBOARD TOGGLE \\";
        RemoteBluetoothClient.send(cmd);
    }
    public void KeyboardToggleCOLON(View v) {
        cmd = "KEYBOARD TOGGLE :";
        RemoteBluetoothClient.send(cmd);
    }    
    public void KeyboardToggleQUOTE(View v) {
        cmd = "KEYBOARD TOGGLE \"";
        RemoteBluetoothClient.send(cmd);
    }    
    public void KeyboardToggleLOWERTHAN(View v) {
        cmd = "KEYBOARD TOGGLE <";
        RemoteBluetoothClient.send(cmd);
    } 
    public void KeyboardToggleGREATERTHAN(View v) {
        cmd = "KEYBOARD TOGGLE >";
        RemoteBluetoothClient.send(cmd);
    } 
    public void KeyboardToggleAMPERSANT(View v) {
        cmd = "KEYBOARD TOGGLE &";
        RemoteBluetoothClient.send(cmd);
    } 
    public void KeyboardToggleEXCLA(View v) {
        cmd = "KEYBOARD TOGGLE !";
        RemoteBluetoothClient.send(cmd);
    } 
    public void KeyboardToggleHASH(View v) {
        cmd = "KEYBOARD TOGGLE #";
        RemoteBluetoothClient.send(cmd);
    } 
    public void KeyboardToggleDOLLAR(View v) {
        cmd = "KEYBOARD TOGGLE $";
        RemoteBluetoothClient.send(cmd);
    } 
    public void KeyboardTogglePOUND(View v) {
    	cmd = "KEYBOARD HOLD SHIFT";
    	RemoteBluetoothClient.send(cmd);
    	cmd = "KEYBOARD TOGGLE 3";
    	RemoteBluetoothClient.send(cmd);
    	cmd = "KEYBOARD RELEASE SHIFT";
        RemoteBluetoothClient.send(cmd);
    } 
    public void KeyboardToggleEQUALS(View v) {
        cmd = "KEYBOARD TOGGLE =";
        RemoteBluetoothClient.send(cmd);
    } 
    public void KeyboardTogglePERCENT(View v) {
    	cmd = "KEYBOARD HOLD SHIFT";
    	RemoteBluetoothClient.send(cmd);
    	cmd = "KEYBOARD TOGGLE 5";
    	RemoteBluetoothClient.send(cmd);
    	cmd = "KEYBOARD RELEASE SHIFT";
        RemoteBluetoothClient.send(cmd);
    } 
    public void KeyboardToggleSINGLEQUOTE(View v) {
        cmd = "KEYBOARD TOGGLE '";
        RemoteBluetoothClient.send(cmd);
    } 
    public void KeyboardToggleBRACKETOPEN(View v) {
        cmd = "KEYBOARD TOGGLE (";
        RemoteBluetoothClient.send(cmd);
    } 
    public void KeyboardToggleBRACKETCLOSE(View v) {
        cmd = "KEYBOARD TOGGLE )";
        RemoteBluetoothClient.send(cmd);
    } 
    public void KeyboardToggleTIMES(View v) {
        cmd = "KEYBOARD TOGGLE *";
        RemoteBluetoothClient.send(cmd);
    } 
    public void KeyboardTogglePLUS(View v) {
        cmd = "KEYBOARD TOGGLE +";
        RemoteBluetoothClient.send(cmd);
    } 
    public void KeyboardToggleDASH(View v) {
        cmd = "KEYBOARD TOGGLE -";
        RemoteBluetoothClient.send(cmd);
    } 
    public void KeyboardToggleFULLSTOP(View v) {
        cmd = "KEYBOARD TOGGLE .";
        RemoteBluetoothClient.send(cmd);
    } 
    public void KeyboardToggleSQBRACKETOPEN(View v) {
        cmd = "KEYBOARD TOGGLE [";
        RemoteBluetoothClient.send(cmd);
    } 
    public void KeyboardToggleSQBRACKETCLOSE(View v) {
        cmd = "KEYBOARD TOGGLE ]";
        RemoteBluetoothClient.send(cmd);
    } 
    public void KeyboardToggleUNDERSCORE(View v) {
        cmd = "KEYBOARD TOGGLE _";
        RemoteBluetoothClient.send(cmd);
    } 
    public void KeyboardToggleCRBRACKETOPEN(View v) {
    	cmd = "KEYBOARD HOLD SHIFT";
        RemoteBluetoothClient.send(cmd);
        cmd = "KEYBOARD TOGGLE [";
        RemoteBluetoothClient.send(cmd);
        cmd = "KEYBOARD RELEASE SHIFT";
        RemoteBluetoothClient.send(cmd);
    } 
    public void KeyboardToggleCRBRACKETCLOSE(View v) {
    	cmd = "KEYBOARD HOLD SHIFT";
        RemoteBluetoothClient.send(cmd);
        cmd = "KEYBOARD TOGGLE ]";
        RemoteBluetoothClient.send(cmd);
        cmd = "KEYBOARD RELEASE SHIFT";
        RemoteBluetoothClient.send(cmd);
    } 
    public void KeyboardToggleEQV(View v) {
    	cmd = "KEYBOARD HOLD SHIFT";
        RemoteBluetoothClient.send(cmd);
        cmd = "KEYBOARD TOGGLE #";
        RemoteBluetoothClient.send(cmd);
        cmd = "KEYBOARD RELEASE SHIFT";
        RemoteBluetoothClient.send(cmd);
    } 
    public void KeyboardToggleQM(View v){
    	cmd = "KEYBOARD HOLD SHIFT";
        RemoteBluetoothClient.send(cmd);
        cmd = "KEYBOARD TOGGLE /";
        RemoteBluetoothClient.send(cmd);
        cmd = "KEYBOARD RELEASE SHIFT";
        RemoteBluetoothClient.send(cmd);
    }
    
    
    
    
    //Hold keys
    
    public void KeyboardHoldCtrl(View v) {
        cmd = "KEYBOARD HOLD CTRL";
        RemoteBluetoothClient.send(cmd);
    }
    
    public void KeyboardHoldAlt(View v) {
        cmd = "KEYBOARD HOLD ALT";
        RemoteBluetoothClient.send(cmd);
    }
    
    public void KeyboardHoldShift(View v) {
        cmd = "KEYBOARD HOLD SHIFT";
        RemoteBluetoothClient.send(cmd);
    }
    
    public void KeyboardReleaseCtrl(View v) {
        cmd = "KEYBOARD RELEASE CTRL";
        RemoteBluetoothClient.send(cmd);
    }
    
    public void KeyboardReleaseAlt(View v) {
        cmd = "KEYBOARD RELEASE ALT";
        RemoteBluetoothClient.send(cmd);
    }
    
    public void KeyboardReleaseShift(View v) {
        cmd = "KEYBOARD RELEASE SHIFT";
        RemoteBluetoothClient.send(cmd);
    }
    
    public void KeyboardSWITCH(View v) {
        Intent i = new Intent(this, KeyboardActivity.class);
        startActivity(i);
        finish();
    }
    
}