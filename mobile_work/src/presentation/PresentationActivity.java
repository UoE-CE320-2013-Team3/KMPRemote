package com.bignerdranch.android.presentation;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class PresentationActivity extends Activity {
	//RemoteBluetoothClient mclient;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_presentation);
		// mclient = new RemoteBluetoothClient();
		// mclient.connect();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.presentation, menu);
		return true;
	}
	public void previousSlide(View v) {
		String theKey = "LEFTARROW";
		byte[] buffer = theKey.getBytes();
		//mclient.send(buffer);
		Toast.makeText(PresentationActivity.this, R.string.Left_Toast,
				Toast.LENGTH_SHORT).show();
	}
	public void startPresentation(View v) {
		String theKey = "F5";
		byte[] buffer = theKey.getBytes();
		//mclient.send(buffer);
		Toast.makeText(PresentationActivity.this, R.string.F5_Toast,
				Toast.LENGTH_SHORT).show();
	}
	public void exitPresentation(View v) {
		String theKey = "ESC";
		byte[] buffer = theKey.getBytes();
		//mclient.send(buffer);
		Toast.makeText(PresentationActivity.this, R.string.ESC_Toast,
				Toast.LENGTH_SHORT).show();
	}
	public void nextSlide(View v) {
		String theKey = "RIGHTARROW";
		byte[] buffer = theKey.getBytes();
		//mclient.send(buffer);
		Toast.makeText(PresentationActivity.this, R.string.Right_Toast,
				Toast.LENGTH_SHORT).show();
	}
}
