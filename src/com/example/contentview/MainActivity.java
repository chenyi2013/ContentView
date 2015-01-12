package com.example.contentview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	private ContentView mContentView;
	private Button mScaleUp;
	private Button mScaleDown;
	private int textSize = 15;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContentView = (ContentView) findViewById(R.id.content_view);
		mScaleDown = (Button) findViewById(R.id.scale_down);
		mScaleDown.setOnClickListener(this);
		mScaleUp = (Button) findViewById(R.id.scale_up);
		mScaleUp.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View view) {

		switch (view.getId()) {
		case R.id.scale_down:
			mContentView.setTextSize(--textSize);

			break;
		case R.id.scale_up:
			mContentView.setTextSize(++textSize);
			break;

		default:
			break;
		}

	}
}
