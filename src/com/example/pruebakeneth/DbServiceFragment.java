package com.example.pruebakeneth;

import java.util.ArrayList;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.pruebakeneth.objects.Record;
import com.example.pruebakeneth.utils.DeviseMemoryUtils;

public class DbServiceFragment extends ListFragment implements OnClickListener {

	public static final String TAG = "DbServiceFragment";
	
	private ArrayList<Record> list;
	private View mView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.fragment_db_use, container, false);
		return mView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		forViews();

		list = new ArrayList<Record>();
		for (int i = 0; i < 6; i++) {
			list.add(new Record());
		}
		
		mAdapter = new ListDbAdapter();
		setListAdapter(mAdapter);
	}
	
	private ListDbAdapter mAdapter;

	private void forViews() {
		mView.findViewById(R.id.btnSave).setOnClickListener(this);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {

	}

	private class ListDbAdapter extends ArrayAdapter<Record> {

		public ListDbAdapter() {
			super(getActivity(), R.layout.listitem_db_use, list);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view;
			if (convertView == null) {
				view = getActivity().getLayoutInflater().inflate(
						R.layout.listitem_db_use, null);
			} else {
				view = convertView;
			}
			return view;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnSave:
//			Toast.makeText(getActivity(), "Save!", Toast.LENGTH_LONG).show();
			Log.d(TAG, "items: " + list.size());
			save();
			break;
		default:
			break;
		}
	}

	private void save() {
		Record mRecord = new Record();
		// internal memory
		Log.d(TAG, "7");
		mRecord.setmInMemTotal(DeviseMemoryUtils.getTotalInternalMemorySize());
		Log.d(TAG, "8");
		mRecord.setmInMemAvailable(DeviseMemoryUtils
				.getAvailableInternalMemorySize());
		Log.d(TAG, "9");
		
		// external memory
		if (DeviseMemoryUtils.externalMemoryAvailable()) { // AVAILABLE
			Log.d(TAG, "1");
			mRecord.setmExMemAvailable(DeviseMemoryUtils.getAvailableExternalMemorySize());
			Log.d(TAG, "2");
			mRecord.setmExMemTotal(DeviseMemoryUtils.getTotalExternalMemorySize());
			Log.d(TAG, "3");
		} else { // NO MEMORY
			Log.d(TAG, "4");
			mRecord.setmExMemAvailable("--");
			Log.d(TAG, "5");
			mRecord.setmExMemTotal("--");
			Log.d(TAG, "6");
		}

		// battery

		// geolocation

		list.add(mRecord);
		
		if (mAdapter == getListAdapter())
			Log.wtf(TAG, "Equal instances!");

	}

}
