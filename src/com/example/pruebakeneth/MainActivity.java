package com.example.pruebakeneth;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.example.pruebakeneth.global.Constants;
import com.parse.ParseAnalytics;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ParseAnalytics.trackAppOpened(getIntent());

		ImageView imgview = (ImageView) findViewById(R.id.imvSplash);
		imgview.setScaleType(ScaleType.FIT_XY);

		if (savedInstanceState == null) {
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() {
					Intent servicesActivity = new Intent(MainActivity.this,
							ServicesActivity.class);
					if (getIntent().getStringExtra(
							TheApplication.BOOT_UP_DEVICE) != null) {
						DateFormat dateFormat = new SimpleDateFormat(
								"yyyy/MM/dd HH:mm:ss");
						Date date = new Date();
						servicesActivity.putExtra(
								TheApplication.BOOT_UP_DEVICE,
								dateFormat.format(date));
						Log.d(TAG,
								"Send signal with date: "
										+ dateFormat.format(date));
					}
					startActivity(servicesActivity);
					finish();
				}
			}, Constants.Splash.TIME_SPLASH);
		}

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putBoolean(Constants.Splash.STARTED, true);
	}

}
