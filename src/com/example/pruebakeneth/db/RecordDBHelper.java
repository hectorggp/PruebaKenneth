package com.example.pruebakeneth.db;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pruebakeneth.objects.Record;

public class RecordDBHelper extends DBHelperBase<Record, Integer> {

	private static final int VERSION = 2;
	private static final String TABLE_NAME = "DBPrueba";
	private final static String ID = "mRecord_id";

	public RecordDBHelper(Context context, String user) {
		super(context, user, VERSION, TABLE_NAME, ID, Record.class);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		clearTables();
		addTable(TABLE_NAME, TABLE_SQL);
		super.onCreate(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		clearTables();
		addTable(TABLE_NAME, TABLE_SQL);
		super.onUpgrade(db, oldVersion, newVersion);
	}

	@Override
	public Record getObjectFromCursor(Cursor cursor) {
		Record object = new Record();
		return super.getObjectFromCursor(cursor, object);
	}

	@Override
	public boolean insert(Record object) {
		return insert(TABLE_NAME, object);
	}

	@Override
	public boolean insertWS(Record object) {
		return insertWS(TABLE_NAME, object);
	}

	@Override
	public boolean delete(Integer key) {
		return delete(TABLE_NAME, ID, key);
	}

	@Override
	public boolean update(Integer key, Record object) {
		return update(TABLE_NAME, ID, key, object);
	}

	@Override
	public boolean updateWS(Integer key, Record object) {
		return updateWS(TABLE_NAME, ID, key, object);
	}

	@Override
	public Record select(Integer key) {
		return select(TABLE_NAME, ID, key);
	}

	@Override
	public ArrayList<Record> selectAll() {
		return selectAll(TABLE_NAME);
	}

	public ArrayList<Record> selectAllSortByInMemAvailable() {
		return selectAllSort(TABLE_NAME, Record.Tag_mInMemAvailable);
	}

	public ArrayList<Record> selectAllSortByExMemAvailable() {
		return selectAllSort(TABLE_NAME, Record.Tag_mExMemAvailable);
	}

	@Override
	public boolean setModifiedDate(Integer key, String date) {
		return setModifiedDate(TABLE_NAME, ID, key, date);
	}

	@Override
	public String getModifiedDate(Integer key) {
		return getModifiedDate(TABLE_NAME, ID, key);
	}

	@Override
	public boolean exists(Integer key) {
		return exists(TABLE_NAME, ID, key);
	}

}
