package com.example.kmpremote.presentation;

import com.example.kmpremote.R;
import com.example.kmpremote.bluetoothclient.RemoteBluetoothClient;
import com.example.kmpremote.mouse.DisplayMousePad;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;


public class PresentationActivity extends Activity {
	OnSwipeTouchListener fswipe;
	private Spinner spinner1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_presentation);
		addListenerOnSpinnerItemSelection();
	}

	public void addListenerOnSpinnerItemSelection() {
    	spinner1 = (Spinner) findViewById(R.id.spinner1);
    	spinner1.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				String selected = arg0.getItemAtPosition(arg2).toString();
				if(selected.equals("Mouse")){
					Intent m = new Intent(getApplicationContext(),DisplayMousePad.class);
					startActivity(m);
				}
				/* need to add keyboard stuff
				if(selected.equals("Keyboard")){
					Intent m = new Intent(getApplicationContext(),PresentationActivity.class);
				}
				*/
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
    		
    	});
      }

	public void previousSlide(View v) {
		String theKey = "KEYBOARD TOGGLE LEFTARROW ";
		RemoteBluetoothClient.send(theKey);
		Toast.makeText(PresentationActivity.this, R.string.Left_Toast,
				Toast.LENGTH_SHORT).show();
	}

	public void startPresentation(View v) {
		String theKey = "KEYBOARD TOGGLE F5 ";
		RemoteBluetoothClient.send(theKey);
		Toast.makeText(PresentationActivity.this, R.string.F5_Toast,
				Toast.LENGTH_SHORT).show();
	}

	public void exitPresentation(View v) {
		String theKey = "KEYBOARD TOGGLE ESC ";
		RemoteBluetoothClient.send(theKey);
		Toast.makeText(PresentationActivity.this, R.string.ESC_Toast,
				Toast.LENGTH_SHORT).show();
	}

	public void nextSlide(View v) {
		String theKey = "KEYBOARD TOGGLE RIGHTARROW ";
		RemoteBluetoothClient.send(theKey);
		Toast.makeText(PresentationActivity.this, R.string.Right_Toast,
				Toast.LENGTH_SHORT).show();
	}

	public void swipe(View v) {
		v.setOnTouchListener(new OnSwipeTouchListener() {
			public void onSwipeRight() {
				Toast.makeText(PresentationActivity.this, R.string.Right_Toast,
						Toast.LENGTH_SHORT).show();
			}

			public void onSwipeLeft() {
				Toast.makeText(PresentationActivity.this, R.string.Left_Toast,
						Toast.LENGTH_SHORT).show();
			}
		});
	}
}
