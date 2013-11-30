package com.example.pruebakeneth.objects;

public class Record {

	public Record() {
		mInMemAvailable = mInMemTotal = mExMemAvailable = mExMemTotal = mBatChargeStatus = "";
	}

	private String mInMemAvailable, mInMemTotal, mExMemAvailable, mExMemTotal,
			mBatChargeStatus, mBatteryChargeType;
	
	private double mLat, mLon, mAlt;
	
	private float mAccuracy;
	
	private boolean mBatteryCharging;

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
		double ret = ((double)Math.round(mLat * num)) / num;
		return ret;
	}

	public void setmLat(double d) {
		this.mLat = d;
	}

	public double getmLon() {
		return mLon;
	}
	
	public double getmLon(int num) {
		double ret = ((double)Math.round(mLon * num)) / num;
		return ret;
	}

	public void setmLon(double d) {
		this.mLon = d;
	}

	public double getmAlt() {
		return mAlt;
	}
	
	public double getmAlt(int num) {
		double ret = ((double)Math.round(mAlt * num)) / num;
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
