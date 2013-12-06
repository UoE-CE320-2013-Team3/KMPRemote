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
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
	public static boolean ActiveSensor;
	
	//ConnectedThread t;
	public static String cmd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.mousepad_display);
		ActiveSensor = true;
		getActionBar().setDisplayHomeAsUpEnabled(true); 
		mSensorManager = (SensorManager)this.getSystemService(Context.SENSOR_SERVICE);
		mSensorManager.registerListener(this,
				mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
				SensorManager.SENSOR_DELAY_NORMAL);
		mSensorManager.registerListener(this,
				mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
		Button hold = (Button)findViewById(R.id.button1);
		hold.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				String cmd;
				switch(event.getAction()){
				
				case MotionEvent.ACTION_DOWN:
					ActiveSensor = true;
					cmd = "MOUSE HOLD leftclick";
					RemoteBluetoothClient.send(cmd);
					break;
				case MotionEvent.ACTION_UP:
					ActiveSensor = false;
					cmd = "MOUSE RELEASE leftclick";
					RemoteBluetoothClient.send(cmd);
					break;
					
				}
				// TODO Auto-generated method stub
				return false;
			}
			
		});
		
	}
	
	
	
	
	

	public void onSensorChanged(SensorEvent event)
	{
		if(ActiveSensor == true){
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
			if(mOrientation != null && ActiveSensor == true)
			{
				if(mOrientation[1]> 0.1){
					//int amount = (int)mOrientation[1]*10;
					moveRight(null);
					mOrientation[1]=0;
				}
				if(mOrientation[1]< -0.1){
					//int amount = Math.abs((int)mOrientation[1]*10);
					moveLeft(null);
					mOrientation[1]=0;
				}
				if(mOrientation[2] > 0.1){
					//int amount = (int)mOrientation[2]*10;
					moveUp(null);
					mOrientation[2]=0;
				}
				if(mOrientation[2] < -0.1){
					//int amount = Math.abs((int)mOrientation[2]*10);
					moveDown(null);
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
			ActiveSensor = false;
			startActivity(keyAct);
			finish();
			return true;
		case R.id.presentation:
			Intent presAct = new Intent(this, PresentationActivity.class);
			ActiveSensor = false;
			startActivity(presAct);
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void activateSensor(View v){
		ActiveSensor = !ActiveSensor;
	}

	//need to still implement these methods to send data to the bluetooth client class!
	public void moveUp(View v){
		String a;
		if(v != null){
			a = "10";
		}
		else{
			a = String.valueOf((int)(mOrientation[2]*10)+3);
			mOrientation[2] = 0;
		}
		cmd = "MOUSE MOVE UP "+a;
		RemoteBluetoothClient.send(cmd);    	
	}

	public void moveDown(View v){
		String a;
		if(v != null){
			a = "10";
		}
		else{
			a = String.valueOf(Math.abs((int)mOrientation[2]*10)+3);
			mOrientation[2] = 0;
		}
		cmd = "MOUSE MOVE DOWN "+a;
		RemoteBluetoothClient.send(cmd);    	
	}

	public void moveLeft(View v){
		String a;
		if(v != null){
			a = "10";
		}
		else{
			a = String.valueOf(Math.abs((int)mOrientation[1]*10)+3);
			mOrientation[1] = 0;
		}
		String cmd = "MOUSE MOVE LEFT "+a;    	
		RemoteBluetoothClient.send(cmd);	
	}

	public void moveRight(View v){
		String a;
		if(v != null){
			a = "10";
		}
		else{
			a = String.valueOf((int)(mOrientation[1]*10)+3);
			mOrientation[1] = 0;
		}
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
