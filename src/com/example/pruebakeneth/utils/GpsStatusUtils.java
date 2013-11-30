package com.example.pruebakeneth.utils;

//info:
//	http://stackoverflow.com/questions/4721449/enable-gps-programatically-like-tasker

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.provider.Settings;

public class GpsStatusUtils {

	private Activity mActivity;

	public GpsStatusUtils(Activity mActivity) {
		this.mActivity = mActivity;
	}

	public boolean isGpsEnabled() {
		String provider = Settings.Secure.getString(
				mActivity.getContentResolver(),
				Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
		return provider.contains("gps");
	}

	public boolean isGpsDisabled() {
		String provider = Settings.Secure.getString(
				mActivity.getContentResolver(),
				Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
		return !provider.contains("gps");
	}

	public void turnGPSOn() {

		if (isGpsDisabled()) { // if gps is disabled
			final Intent poke = new Intent();
			poke.setClassName("com.android.settings",
					"com.android.settings.widget.SettingsAppWidgetProvider");
			poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
			poke.setData(Uri.parse("3"));
			mActivity.sendBroadcast(poke);
		}
	}

	public void turnGPSOff() {

		if (isGpsEnabled()) { // if gps is enabled
			final Intent poke = new Intent();
			poke.setClassName("com.android.settings",
					"com.android.settings.widget.SettingsAppWidgetProvider");
			poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
			poke.setData(Uri.parse("3"));
			mActivity.sendBroadcast(poke);
		}
	}

	public boolean canToggleGPS() {
	    PackageManager pacman = mActivity.getPackageManager();
	    PackageInfo pacInfo = null;

	    try {
	        pacInfo = pacman.getPackageInfo("com.android.settings", PackageManager.GET_RECEIVERS);
	    } catch (NameNotFoundException e) {
	        return false; //package not found
	    }

	    if(pacInfo != null){
	        for(ActivityInfo actInfo : pacInfo.receivers){
	            //test if recevier is exported. if so, we can toggle GPS.
	            if(actInfo.name.equals("com.android.settings.widget.SettingsAppWidgetProvider") && actInfo.exported){
	                return true;
	            }
	        }
	    }

	    return false; //default
	}
}
