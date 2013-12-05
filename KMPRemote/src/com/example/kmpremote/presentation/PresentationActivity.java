package com.example.kmpremote.presentation;

import com.example.kmpremote.R;
import com.example.kmpremote.bluetoothclient.RemoteBluetoothClient;
import com.example.kmpremote.keyboard.KeyboardActivity;
import com.example.kmpremote.mouse.DisplayMousePad;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;


public class PresentationActivity extends Activity {
	OnSwipeTouchListener fswipe;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_presentation);
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
            case R.id.mouse:
                Intent presAct = new Intent(this, DisplayMousePad.class);
                startActivity(presAct);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

	public void previousSlide(View v) {
		String theKey = "KEYBOARD TOGGLE LEFTARROW ";
		RemoteBluetoothClient.send(theKey);
	}

	public void startPresentation(View v) {
		String theKey = "KEYBOARD TOGGLE F5 ";
		RemoteBluetoothClient.send(theKey);
	}

	public void exitPresentation(View v) {
		String theKey = "KEYBOARD TOGGLE ESC ";
		RemoteBluetoothClient.send(theKey);
	}

	public void nextSlide(View v) {
		String theKey = "KEYBOARD TOGGLE RIGHTARROW ";
		RemoteBluetoothClient.send(theKey);
	}

	public void swipe(View v) {
		v.setOnTouchListener(new OnSwipeTouchListener() {
			public void onSwipeRight() {
			}

			public void onSwipeLeft() {
			}
		});
	}
}
