package com.example.kmpremote.presentation;

import org.json.JSONException;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
		// Holding the Start button will open a Context Menu. Here are the
		// options found within that menu
		super.onCreateContextMenu(startMenu, v, startMenuChoice);
		startMenu.setHeaderTitle("Features");
		startMenu.add(0, v.getId(), 0, "Start from first slide");
		startMenu.add(0, v.getId(), 0, "Start from current slide");
		startMenu.add(0, v.getId(), 0, "Request Notes via File Directory");
		startMenu.add(0, v.getId(), 0, "Display Notes");
		startMenu.add(0, v.getId(), 0, "Clear Notes");
	}

	public boolean onContextItemSelected(MenuItem menuChoice) {
		// Handles presses/choices on the context menu
		if (menuChoice.getTitle() == "Start from first slide") {
			startBeginning(menuChoice.getItemId());
		} else if (menuChoice.getTitle() == "Start from current slide") {
			startCurrent(menuChoice.getItemId());
		} else if (menuChoice.getTitle() == "Request Notes via File Directory") {
			requestNotes(menuChoice.getItemId());
		} else if (menuChoice.getTitle() == "Display Notes") {
			try {
				displayNotes(menuChoice.getItemId());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (menuChoice.getTitle() == "Clear Notes") {
			clearNotes(menuChoice.getItemId());
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
		// Action if the previous slide button is pressed
		String theKey = "KEYBOARD TOGGLE LEFTARROW ";
		RemoteBluetoothClient.send(theKey);
	}

	public void startPresentation(View v) {
		// Action if the start presentation button is pressed
		String theKey = "KEYBOARD TOGGLE F5 ";
		RemoteBluetoothClient.send(theKey);
	}

	public void exitPresentation(View v) {
		// Action if the exit presentation button is pressed
		String theKey = "KEYBOARD TOGGLE ESC ";
		RemoteBluetoothClient.send(theKey);
	}

	public void nextSlide(View v) {
		// Action if the next slide button is pressed
		String theKey = "KEYBOARD TOGGLE RIGHTARROW ";
		RemoteBluetoothClient.send(theKey);
	}

	public void startBeginning(int id) {
		// Action if the start from beginning option is pressed within the
		// context menu
		String theKey = "KEYBOARD TOGGLE F5 ";
		RemoteBluetoothClient.send(theKey);
		Toast.makeText(this, "Started Presentation from the beginning slide",
				Toast.LENGTH_SHORT).show();
	}

	public void startCurrent(int id) {
		// Action if the start from current option is pressed within the context
		// menu
		String theKey = "KEYBOARD HOLD SHIFT ";
		RemoteBluetoothClient.send(theKey);
		String theKey1 = "KEYBOARD TOGGLE F5 ";
		RemoteBluetoothClient.send(theKey1);
		String theKey2 = "KEYBOARD RELEASE SHIFT ";
		RemoteBluetoothClient.send(theKey2);
		Toast.makeText(this, "Started Presentation from the current slide",
				Toast.LENGTH_SHORT).show();
	}

	public void requestNotes(int id) {
		// Action if the request notes option is pressed within the context menu
		AlertDialog.Builder promptForFile = new AlertDialog.Builder(this);
		promptForFile.setTitle("Requesting the notes");
		promptForFile
				.setTitle("Please enter the file directory of your PowerPoint Presentation");
		final EditText userInput = new EditText(this);
		promptForFile.setView(userInput);
		promptForFile.setPositiveButton("Enter",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface userInputDialog,
							int aButton) {
						String firstBit = "PRESENTATION LINK ";
						String secondBit = " END_LINK";
						Editable value = userInput.getText();
						String strValue = value.toString();
						String strFinal = firstBit + strValue + secondBit;
						RemoteBluetoothClient.send(strFinal);
						String theKey = "PRESENTATION NOTES";
						RemoteBluetoothClient.send(theKey);
					}
				});
		promptForFile.setNegativeButton("Return",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface UserInputDialog,
							int aButton) {
					}
				});
		promptForFile.show();
	}

	public void displayNotes(int id) throws JSONException {
		// Action if the display notes option is pressed within the context menu
		RemoteBluetoothClient.getJSONObject();
		int abc = RemoteBluetoothClient.getJSONObject().length();
		TextView textView = (TextView) findViewById(R.id.notes_text);
		String theText = "";
		textView.setText("");
		for (int i = 1; i <= abc; i++) {
			theText = RemoteBluetoothClient.getJSONObject().getString(
					String.valueOf(i));
			textView.append("Slide " + i + ":" + " " + theText + "\n\n");
		}
	}

	public void clearNotes(int id) {
		// Action if the clear notes option is pressed within the context menu
		TextView textView = (TextView) findViewById(R.id.notes_text);
		textView.setText("To get the notes, hold the START button, a menu will appear, select the request notes option and type in the address of the file, hit enter, then open the menu again and press display notes.");
	}

	public void swipeGestures(View v) {
		// Uses OnSwipeTouchListener & handles left and right swipe gestures
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
