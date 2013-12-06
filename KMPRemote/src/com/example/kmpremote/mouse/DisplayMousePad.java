package com.example.kmpremote.mouse;

import com.example.kmpremote.R;
import com.example.kmpremote.bluetoothclient.RemoteBluetoothClient;
import com.example.kmpremote.keyboard.KeyboardActivity;
import com.example.kmpremote.presentation.PresentationActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
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

public class DisplayMousePad extends Activity implements SensorEventListener {
	private SensorManager mSensorManager;
	private float[] mAccelerometerReading;
	private float[] mMagneticFieldReading;
	private float[] mRotationMatrix = new float[16];
	private float[] mRemapedRotationMatrix = new float[16];
	private float[] mOrientation = new float[3];
	//ConnectedThread t;
	public static String cmd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.mousepad_display);
		getActionBar().setDisplayHomeAsUpEnabled(true);   
		mSensorManager = (SensorManager)this.getSystemService(Context.SENSOR_SERVICE);
		mSensorManager.registerListener(this,
				mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
				SensorManager.SENSOR_DELAY_NORMAL);
		mSensorManager.registerListener(this,
				mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	public void onSensorChanged(SensorEvent event)
	{
		switch (event.sensor.getType())
		{
		case Sensor.TYPE_ACCELEROMETER:
		{
			mAccelerometerReading = event.values.clone();
			break;
		}
		case Sensor.TYPE_MAGNETIC_FIELD:
		{
			mMagneticFieldReading = event.values.clone();
			break;
		}
		}
		if(mAccelerometerReading != null && mMagneticFieldReading != null &&
				SensorManager.getRotationMatrix(mRotationMatrix, null, mAccelerometerReading, mMagneticFieldReading))
		{
			SensorManager.remapCoordinateSystem(mRotationMatrix,
					SensorManager.AXIS_Y, SensorManager.AXIS_MINUS_X, mRemapedRotationMatrix);
			SensorManager.getOrientation(mRemapedRotationMatrix, mOrientation);
		}


		//Where you want to read the orientation
		if(mOrientation != null)
		{
			if(mOrientation[1]> 0.1){
				int amount = (int)mOrientation[1]*10;
				moveRight(amount+3);
				mOrientation[1]=0;
			}
			if(mOrientation[1]< -0.1){
				int amount = Math.abs((int)mOrientation[1]*10);
				moveLeft(amount+3);
				mOrientation[1]=0;
			}
			if(mOrientation[2] > 0.1){
				int amount = (int)mOrientation[2]*10;
				moveUp(amount+3);
				mOrientation[2]=0;
			}
			if(mOrientation[2] < -0.1){
				int amount = Math.abs((int)mOrientation[2]*10);
				moveDown(amount+3);
				mOrientation[2]=0;
			}
			//do stuff with mOrientation[0]
			//do stuff with mOrientation[1]
			//do stuff with mOrientation[2]
			//to control a ball i used
			//mOrientation[2] for horizontal movement and
			//- mOrientation[1] for vertical movement
		}
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
	public void moveUp(int x){
		String a = String.valueOf(x);
		cmd = "MOUSE MOVE UP "+a;
		RemoteBluetoothClient.send(cmd);    	
	}

	public void moveDown(int x){
		String a = String.valueOf(x);
		cmd = "MOUSE MOVE DOWN "+a;
		RemoteBluetoothClient.send(cmd);    	
	}

	public void moveLeft(int x){
		String a = String.valueOf(x);
		String cmd = "MOUSE MOVE LEFT "+a;    	
		RemoteBluetoothClient.send(cmd);	
	}

	public void moveRight(int x){
		String a = String.valueOf(x);
		String cmd = "MOUSE MOVE RIGHT "+a;    	
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

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

}
