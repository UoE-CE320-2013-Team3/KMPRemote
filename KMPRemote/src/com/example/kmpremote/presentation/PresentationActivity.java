package com.example.kmpremote.presentation;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.kmpremote.R;
import com.example.kmpremote.bluetoothclient.RemoteBluetoothClient;
import com.example.kmpremote.keyboard.KeyboardActivity;
import com.example.kmpremote.mouse.DisplayMousePad;

public class PresentationActivity extends Activity {
	OnSwipeTouchListener fswipe;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		super.onCreate(savedInstanceState);
		DisplayMousePad.ActiveSensor = false;
		setContentView(R.layout.activity_presentation);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		Button holdButton = (Button) findViewById(R.id.START_button);
		registerForContextMenu(holdButton);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onCreateContextMenu(ContextMenu startMenu, View v,
			ContextMenuInfo startMenuChoice) {
		super.onCreateContextMenu(startMenu, v, startMenuChoice);
		startMenu.setHeaderTitle("Context Menu");
		startMenu.add(0, v.getId(), 0, "Start from first slide");
		startMenu.add(0, v.getId(), 0, "Start from current slide");
	}

	public boolean onContextItemSelected(MenuItem menuChoice) {
		if (menuChoice.getTitle() == "Start from first slide") {
			startBeginning(menuChoice.getItemId());
		} else if (menuChoice.getTitle() == "Start from current slide") {
			startCurrent(menuChoice.getItemId());
		} else {
			return false;
		}
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.keyboard:
			Intent keyAct = new Intent(this, KeyboardActivity.class);
			startActivity(keyAct);
			finish();
			return true;
		case R.id.mouse:
			Intent presAct = new Intent(this, DisplayMousePad.class);
			startActivity(presAct);
			finish();
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

	public void startBeginning(int id) {
		String theKey = "KEYBOARD TOGGLE F5 ";
		RemoteBluetoothClient.send(theKey);
		Toast.makeText(this, "Started Presentation from the beginning slide",
				Toast.LENGTH_SHORT).show();
	}

	public void startCurrent(int id) {
		String theKey = "KEYBOARD HOLD SHIFT ";
		RemoteBluetoothClient.send(theKey);
		String theKey1 = "KEYBOARD TOGGLE F5 ";
		RemoteBluetoothClient.send(theKey1);
		String theKey2 = "KEYBOARD RELEASE SHIFT ";
		RemoteBluetoothClient.send(theKey2);
		Toast.makeText(this, "Started Presentation from the current slide",
				Toast.LENGTH_SHORT).show();
	}

	public void swipeGestures(View v) {
		v.setOnTouchListener(new OnSwipeTouchListener() {
			public void onSwipeRight() {
				String theKey = "KEYBOARD TOGGLE LEFTARROW ";
				RemoteBluetoothClient.send(theKey);
			}

			public void onSwipeLeft() {
				String theKey = "KEYBOARD TOGGLE RIGHTARROW ";
				RemoteBluetoothClient.send(theKey);
			}
		});
	}
}
