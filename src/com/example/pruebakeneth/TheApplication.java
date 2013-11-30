package com.example.pruebakeneth;

import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

import android.app.Application;
import android.util.Log;

import com.example.pruebakeneth.db.DataBaseManager;
import com.example.pruebakeneth.db.RecordDBHelper;
import com.example.pruebakeneth.global.SharedValues;
import com.example.pruebakeneth.utils.LocationUtils;

@ReportsCrashes(formKey = "", mailTo = "hector.geovani@gmail.com", mode = ReportingInteractionMode.TOAST, resToastText = R.string.crash_toast_text)
public class TheApplication extends Application {

	public static final String TAG = "TheApplication";

	private LocationUtils mLocationUtils;
	private DataBaseManager mDbManager;

	@Override
	public void onCreate() {
		super.onCreate();

		Log.wtf(TAG, "onCreate Application!");

		new SharedValues(this.getApplicationContext()).resetPreferences();
	}

	public LocationUtils getmLocationUtils() {
		return this.mLocationUtils;
	}

	public void setmLocationUtils(LocationUtils mLocationUtils) {
		this.mLocationUtils = mLocationUtils;
	}

	public DataBaseManager getDbManager() {
		if (mDbManager == null)
			mDbManager = DataBaseManager.getInstance(this);
		return mDbManager;
	}

	public RecordDBHelper getCurrentClientDataBase() {
		if (getUserId() != null)
			return getDbManager().OpenOrCreateRecordInstance(
					String.valueOf(getUserId()));
		return null;
	}

	public Integer getUserId() {
		return 69;
	}
}
