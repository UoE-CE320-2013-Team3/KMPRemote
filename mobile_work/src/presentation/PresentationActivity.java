import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PresentationActivity extends Activity {

	private Button mPreviousButton;
	private Button mStartButton;
	private Button mEndButton;
	private Button mNextButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_presentation);	
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		mPreviousButton = (Button)findViewById(R.id.previous_button);
		mPreviousButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(PresentationActivity.this, R.string.Left_Toast, Toast.LENGTH_SHORT).show();
			}
		});
		mStartButton = (Button)findViewById(R.id.START_button);
		mStartButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(PresentationActivity.this, R.string.F5_Toast, Toast.LENGTH_SHORT).show();				
			}
		});
		
		mEndButton = (Button)findViewById(R.id.END_button);
		mEndButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(PresentationActivity.this, R.string.F6_Toast, Toast.LENGTH_SHORT).show();	
			}
		});
		
		mNextButton = (Button)findViewById(R.id.next_button);	
		mNextButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(PresentationActivity.this, R.string.Right_Toast, Toast.LENGTH_SHORT).show();	
			}
		});	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.presentation, menu);
		return true;
	}
}
