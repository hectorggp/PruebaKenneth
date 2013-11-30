package com.example.pruebakeneth.global;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

//info:
//	http://developer.android.com/reference/android/content/SharedPreferences.html

public class SharedValues {

	public static final String GPS_CHECKED = "gps_checked";
	public static final String PREFS_NAME = "pruebakeneth";
	
	private Context mContext;
	private boolean GpsChecked;

	public SharedValues(Context mContext) {
		this.mContext = mContext;
		
		refresh();
	}
	
	public void resetPreferences() {
		// set default values
		GpsChecked = false;
		
		commit();
	}
	
	public void commit() {
		SharedPreferences settings = mContext.getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		
		// set values
		editor.putBoolean(GPS_CHECKED, GpsChecked);
		
		editor.commit();
	}

	public void refresh() {
		SharedPreferences settings = mContext.getSharedPreferences(PREFS_NAME, 0);
		
		// get values
		GpsChecked = settings.getBoolean(GPS_CHECKED, false);
	}
	
	public boolean isGpsChecked() {
		return GpsChecked;
	}
	
	public void setGpsChecked(boolean GpsChecked) {
		this.GpsChecked = GpsChecked;
	}
	
}
