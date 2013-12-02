package com.example.pruebakeneth.objects;

public class BootupRecord {

	private String date;
	private int isNew = 0;
	private long mRecord_id;

	public int getIsNew() {
		return isNew;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setIsNew(int isNew) {
		this.isNew = isNew;
	}

	public long getmRecord_id() {
		return mRecord_id;
	}

	public void setmRecord_id(long mRecord_id) {
		this.mRecord_id = mRecord_id;
	}

	public String getDate() {
		return date;
	}

}
