package com.example.pruebakeneth.objects;

public class Record {

	public Record() {
		mInMemAvailable = mInMemTotal = mExMemAvailable = mExMemTotal = mBatPercent = "";
	}

	private String mInMemAvailable, mInMemTotal, mExMemAvailable, mExMemTotal,
			mBatPercent;
	private int mLat, mLon, mAlt;

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
		return mBatPercent;
	}

	public void setmBatPercent(String mBatPercent) {
		this.mBatPercent = mBatPercent;
	}

	public int getmLat() {
		return mLat;
	}

	public void setmLat(int mLat) {
		this.mLat = mLat;
	}

	public int getmLon() {
		return mLon;
	}

	public void setmLon(int mLon) {
		this.mLon = mLon;
	}

	public int getmAlt() {
		return mAlt;
	}

	public void setmAlt(int mAlt) {
		this.mAlt = mAlt;
	}

}
