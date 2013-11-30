package com.example.pruebakeneth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.example.pruebakeneth.global.Constants;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ImageView imgview = (ImageView) findViewById(R.id.imvSplash);
		imgview.setScaleType(ScaleType.FIT_XY);
		
		if (savedInstanceState == null) {
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() {
					Intent loginActivity = new Intent(MainActivity.this,
							ServicesActivity.class);
					startActivity(loginActivity);
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
