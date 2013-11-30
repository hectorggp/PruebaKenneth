package com.example.pruebakeneth;

import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

import com.example.pruebakeneth.global.SharedValues;
import com.example.pruebakeneth.utils.LocationUtils;

import android.app.Application;
import android.util.Log;

@ReportsCrashes(formKey = "", 
		mailTo = "hector.geovani@gmail.com", 
		mode = ReportingInteractionMode.TOAST, 
		resToastText = R.string.crash_toast_text)
public class TheApplication extends Application {

	public static final String TAG = "TheApplication";
	
	private LocationUtils mLocationUtils;
	@Override
	public void onCreate() {
		super.onCreate();
		
		Log.wtf(TAG,  "onCreate Application!");
		
		new SharedValues(this.getApplicationContext()).resetPreferences();
	}
	
	public LocationUtils getmLocationUtils() {
		return this.mLocationUtils;
	}
	
	public void setmLocationUtils(LocationUtils mLocationUtils) {
		this.mLocationUtils = mLocationUtils;
	}
	
}
