package com.example.pruebakeneth.db;

import com.example.pruebakeneth.TheApplication;

public class DataBaseManager {

	private static DataBaseManager sDBManagerInstance;
	private RecordDBHelper mRecordDBHelper;
	private TheApplication mContext;

	public static DataBaseManager getInstance(TheApplication context) {
		if (sDBManagerInstance == null)
			sDBManagerInstance = new DataBaseManager(context);
		return sDBManagerInstance;

	}

	public RecordDBHelper OpenOrCreateRecordInstance(String user) {
		if (mRecordDBHelper == null
				|| (mRecordDBHelper != null && !mRecordDBHelper.getUser()
						.equals(user))) {
			mRecordDBHelper = new RecordDBHelper(mContext, user);
		}
		return mRecordDBHelper;
	}

	private DataBaseManager(TheApplication context) {
		mContext = context;
	}

}
