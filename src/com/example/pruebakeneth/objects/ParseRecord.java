package com.example.pruebakeneth.objects;

import com.example.pruebakeneth.helpers.DateTimeHelper;

public class ParseRecord {

	public static final String NAME_OBJECT = "ParseRecord";
	public static final String KEY_TAG = "key";
	public static final String DATE_TAG = "date";
	public static final String NUMBER_TAG = "number";
	public static final String TEXT_TAG = "text";

	private long key;
	private String date;
	private String number;
	private String text;
	private String mModified_date;
	private String parse_objectId;

	public String getParse_objectId() {
		return parse_objectId;
	}

	public void setParse_objectId(String parse_objectId) {
		this.parse_objectId = parse_objectId;
	}

	public ParseRecord() {

	}

	public ParseRecord(long datetime) {
		this.key = datetime;
		this.mModified_date = (DateTimeHelper.toString(datetime));
	}

	public String getmModified_date() {
		return mModified_date;
	}

	public void setmModified_date(String mModified_date) {
		this.mModified_date = mModified_date;
	}

	public long getKey() {
		return key;
	}

	public void setKey(long key) {
		this.key = key;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
