package com.example.pruebakeneth.objects;

import com.example.pruebakeneth.helpers.DateTimeHelper;

public class Record {

	public final static String Tag_mId = "mId";
	public final static String Tag_mInMemAvailable = "mInMemAvailable";
	public final static String Tag_mInMemTotal = "mInMemTotal";
	public final static String Tag_mExMemAvailable = "mExMemAvailable";
	public final static String Tag_mExMemTotal = "mExMemTotal";
	public final static String Tag_mBatChargeStatus = "mBatChargeStatus";
	public final static String Tag_mBatteryChargeType = "mBatteryChargeType";
	public final static String Tag_mLat = "mLat";
	public final static String Tag_mLon = "mLon";
	public final static String Tag_mAlt = "mAlt";
	public final static String Tag_mAccuracy = "mAccuracy";
	public final static String Tag_mBatteryCharging = "mBatteryCharging";

	private String mInMemAvailable, mInMemTotal, mExMemAvailable, mExMemTotal,
			mBatChargeStatus, mBatteryChargeType, mModified_date;
	private double mLat, mLon, mAlt;
	private float mAccuracy;
	private boolean mBatteryCharging;
	private long mClient_id;
	private int isNew=0;

	public Record() {
		mInMemAvailable = mInMemTotal = mExMemAvailable = mExMemTotal = mBatChargeStatus = mBatteryChargeType = "";
	}

	public Record(long datetime) {
		mInMemAvailable = mInMemTotal = mExMemAvailable = mExMemTotal = mBatChargeStatus = mBatteryChargeType = "";
		this.mClient_id = datetime;
		this.mModified_date = (DateTimeHelper.toString(datetime));
		this.setNew(1);
	}
	
	private void setNew (int i) {
		isNew = i;
	}
	
	public int isNew() {
		return this.isNew;
	}

	public String getmModified_date() {
		return mModified_date;
	}

	public void setmModified_date(String mModified_date) {
		this.mModified_date = mModified_date;
	}

	public long getmClient_id() {
		return mClient_id;
	}

	public void setmClient_id(long mClient_id) {
		this.mClient_id = mClient_id;
	}

	public String getmBatteryChargeType() {
		return mBatteryChargeType;
	}

	public void setmBatteryChargeType(String mBatteryChargeType) {
		this.mBatteryChargeType = mBatteryChargeType;
	}

	public boolean ismBatteryCharging() {
		return mBatteryCharging;
	}

	public void setmBatteryCharging(boolean mBatteryCharging) {
		this.mBatteryCharging = mBatteryCharging;
	}

	public String getmInMemAvailable() {
		return mInMemAvailable;
	}

	public void setmInMemAvailable(String mInMemAvailable) {
		this.mInMemAvailable = mInMemAvailable;
	}

	public String getmInMemTotal() {
		return mInMemTotal;
	}

	public void setmInMemTotal(String mInMemTotal) {
		this.mInMemTotal = mInMemTotal;
	}

	public String getmExMemAvailable() {
		return mExMemAvailable;
	}

	public void setmExMemAvailable(String mExMemAvailable) {
		this.mExMemAvailable = mExMemAvailable;
	}

	public String getmExMemTotal() {
		return mExMemTotal;
	}

	public void setmExMemTotal(String mExMemTotal) {
		this.mExMemTotal = mExMemTotal;
	}

	public String getmBatPercent() {
		return mBatChargeStatus;
	}

	public void setmBatPercent(String mBatChargeStatus) {
		this.mBatChargeStatus = mBatChargeStatus;
	}

	public double getmLat() {
		return mLat;
	}

	public double getmLat(int num) {
		double ret = ((double) Math.round(mLat * num)) / num;
		return ret;
	}

	public void setmLat(double d) {
		this.mLat = d;
	}

	public double getmLon() {
		return mLon;
	}

	public double getmLon(int num) {
		double ret = ((double) Math.round(mLon * num)) / num;
		return ret;
	}

	public void setmLon(double d) {
		this.mLon = d;
	}

	public double getmAlt() {
		return mAlt;
	}

	public double getmAlt(int num) {
		double ret = ((double) Math.round(mAlt * num)) / num;
		return ret;
	}

	public void setmAlt(double d) {
		this.mAlt = d;
	}

	public void setmAccuracy(float f) {
		this.mAccuracy = f;
	}

	public float getmAccuracy() {
		return mAccuracy;
	}
}
