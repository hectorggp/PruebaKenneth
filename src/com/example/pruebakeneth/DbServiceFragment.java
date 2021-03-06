package com.example.pruebakeneth;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Dialog;
import android.app.ListFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pruebakeneth.db.RecordDBHelper;
import com.example.pruebakeneth.global.SharedValues;
import com.example.pruebakeneth.objects.Record;
import com.example.pruebakeneth.utils.BatteryChargeStateUtils;
import com.example.pruebakeneth.utils.DeviseMemoryUtils;
import com.example.pruebakeneth.utils.GpsStatusUtils;
import com.example.pruebakeneth.utils.LocationUtils;

public class DbServiceFragment extends ListFragment implements OnClickListener {

	public static final String TAG = "DbServiceFragment";
	private static final String INSTANCE_LIST = "LIST";
	private static final String DELETE_RECORD = "delete";

	private ArrayList<Record> mList;
	private View mView;
	private RecordDBHelper mRecordDBHelper;

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

		// db
		mRecordDBHelper = ((TheApplication) getActivity().getApplication())
				.getCurrentClientDataBase();

		new onBack().execute(INSTANCE_LIST);

		if (savedInstanceState == null)
			initLocationService();

	}

	private void onListInstance() {
		setListAdapter(new ListDbAdapter());

		// for examples:
		// for (int i = 0; i < 6; i++) {
		// list.add(new Record());
		// }
	}

	private void initLocationService() {
		SharedValues values = new SharedValues(getActivity());
		if (!values.isGpsChecked()) {

			GpsStatusUtils gpsstatus = new GpsStatusUtils(getActivity());

			if (gpsstatus.isGpsDisabled()) {
				Toast.makeText(getActivity(), "GPS off", Toast.LENGTH_SHORT)
						.show();
				if (gpsstatus.canToggleGPS()) {
					gpsstatus.turnGPSOn();
				} else {
					// Toast.makeText(getActivity(), "cant toggle gps",
					// Toast.LENGTH_SHORT).show();
				}
			}

			LocationUtils mLocationUtils = new LocationUtils(getActivity());
			int opc;
			if ((opc = mLocationUtils.validateAndRunThread()) == 0) {
				// all right
			} else {
				Log.d(TAG, opc == 1 ? "ERR_NO_LOCATION_MANAGER"
						: "ERR_NO_PROVIDERS_ENABLED");
			}
			((TheApplication) getActivity().getApplication())
					.setmLocationUtils(mLocationUtils);

			values.setGpsChecked(true);
			values.commit();
		}
	}

	private void forViews() {
		mView.findViewById(R.id.btnSave).setOnClickListener(this);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {

	}

	private class PlaceHolder {
		TextView txvInternalMemoryAvailable, txvInternalMemoryTotal,
				txvInternalMemoryPercent;
		TextView txvExternalMemoryAvailable, txvExternalMemoryTotal,
				txvExternalMemoryPercent;
		TextView txtBatteryChargeStatus, txtBatteryCharge, txtBatteryMode;
		TextView txtLatitude, txtAltitude, txtLongitude, txtAccuracy;
		ImageButton btnMore;
	}

	private class ListDbAdapter extends ArrayAdapter<Record> {

		public ListDbAdapter() {
			super(getActivity(), R.layout.listitem_db_use, mList);
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			View view;
			if (convertView == null) {
				view = getActivity().getLayoutInflater().inflate(
						R.layout.listitem_db_use, null);
				PlaceHolder mPlaceHolder = new PlaceHolder();
				mPlaceHolder.txvInternalMemoryAvailable = (TextView) view
						.findViewById(R.id.txvInternalMemoryAvailable);
				mPlaceHolder.txvInternalMemoryTotal = (TextView) view
						.findViewById(R.id.txvInternalMemoryTotal);
				mPlaceHolder.txvInternalMemoryPercent = (TextView) view
						.findViewById(R.id.txvInternalMemoryPercent);
				mPlaceHolder.txvExternalMemoryAvailable = (TextView) view
						.findViewById(R.id.txvExternalMemoryAvailable);
				mPlaceHolder.txvExternalMemoryTotal = (TextView) view
						.findViewById(R.id.txvExternalMemoryTotal);
				mPlaceHolder.txvExternalMemoryPercent = (TextView) view
						.findViewById(R.id.txvExternalMemoryPercent);
				mPlaceHolder.txtBatteryChargeStatus = (TextView) view
						.findViewById(R.id.txtBatteryChargeStatus);
				mPlaceHolder.txtBatteryCharge = (TextView) view
						.findViewById(R.id.txtBatteryCharge);
				mPlaceHolder.txtBatteryMode = (TextView) view
						.findViewById(R.id.txtBatteryMode);
				mPlaceHolder.txtLatitude = (TextView) view
						.findViewById(R.id.txtLatitude);
				mPlaceHolder.txtAltitude = (TextView) view
						.findViewById(R.id.txtAltitude);
				mPlaceHolder.txtLongitude = (TextView) view
						.findViewById(R.id.txtLongitude);
				mPlaceHolder.txtAccuracy = (TextView) view
						.findViewById(R.id.txtAccuracy);
				mPlaceHolder.btnMore = (ImageButton) view
						.findViewById(R.id.btnMore);
				view.setTag(mPlaceHolder);
			} else {
				view = convertView;
			}
			PlaceHolder mHolder = (PlaceHolder) view.getTag();
			Record mRecord = mList.get(position);
			mHolder.txvInternalMemoryPercent.setText(percent(
					mRecord.getmInMemAvailable(), mRecord.getmInMemTotal()));
			mHolder.txvInternalMemoryAvailable.setText(mRecord
					.getmInMemAvailable());
			mHolder.txvInternalMemoryTotal.setText(mRecord.getmInMemTotal());
			mHolder.txvExternalMemoryPercent.setText(percent(
					mRecord.getmExMemAvailable(), mRecord.getmExMemTotal()));
			mHolder.txvExternalMemoryAvailable.setText(mRecord
					.getmExMemAvailable());
			mHolder.txvExternalMemoryTotal.setText(mRecord.getmExMemTotal());
			mHolder.txtBatteryChargeStatus.setText(mRecord.getmBatPercent());
			mHolder.txtBatteryCharge
					.setText(mRecord.ismBatteryCharging() ? "Cargado"
							: "No cargado");
			mHolder.txtBatteryMode.setText(mRecord.getmBatteryChargeType());
			mHolder.txtLatitude.setText(mRecord.getmLat(1000) + "");
			mHolder.txtLongitude.setText(mRecord.getmLon(1000) + "");
			mHolder.txtAccuracy.setText(""
					+ ((double) Math.round(mRecord.getmAccuracy() * 1000))
					/ 1000);
			mHolder.txtAltitude.setText(mRecord.getmAlt(1000) + "");

			mHolder.btnMore.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					final Dialog mDialog = new Dialog(getContext(),
							R.style.CustomDialogTheme);

					mDialog.setCancelable(false);
					mDialog.setContentView(R.layout.dialog_multiple_buttons);
					mDialog.findViewById(R.id.txtYes).setOnClickListener(
							new OnClickListener() {

								@Override
								public void onClick(View v) {
									mDialog.dismiss();
									delete(position);
								}
							});
					mDialog.findViewById(R.id.txtNo).setOnClickListener(
							new OnClickListener() {

								@Override
								public void onClick(View v) {
									mDialog.dismiss();
								}
							});
					mDialog.show();
				}
			});

			return view;
		}
	}

	private void delete(int position) {
		String[] params = { DELETE_RECORD, position + "" };
		new onBack().execute(params);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnSave:
			// Toast.makeText(getActivity(), "Save!", Toast.LENGTH_LONG).show();
			Log.d(TAG, "items: " + mList.size());
			save();
			break;
		default:
			break;
		}
	}

	private void save() {
		Record mRecord = new Record(Calendar.getInstance().get(
				Calendar.MILLISECOND));
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
			mRecord.setmExMemAvailable(DeviseMemoryUtils
					.getAvailableExternalMemorySize());
			Log.d(TAG, "2");
			mRecord.setmExMemTotal(DeviseMemoryUtils
					.getTotalExternalMemorySize());
			Log.d(TAG, "3");
		} else { // NO MEMORY
			Log.d(TAG, "4");
			mRecord.setmExMemAvailable("--");
			Log.d(TAG, "5");
			mRecord.setmExMemTotal("--");
			Log.d(TAG, "6");
		}

		// battery
		BatteryChargeStateUtils battery = new BatteryChargeStateUtils(
				getActivity());
		mRecord.setmBatPercent(battery.getStatus() + "");
		mRecord.setmBatteryCharging(battery.isCharging());
		mRecord.setmBatteryChargeType(battery.isAcCharge() ? "AC" : (battery
				.isUsbCharge() ? "USB" : "--"));

		// geolocation
		LocationUtils mLocationUtils = ((TheApplication) getActivity()
				.getApplication()).getmLocationUtils();
		if (mLocationUtils != null && mLocationUtils.isSuccess()) {
			mRecord.setmLat(mLocationUtils.getLatitude());
			mRecord.setmLon(mLocationUtils.getLongitude());
			mRecord.setmAlt(mLocationUtils.getAltitude());
			mRecord.setmAccuracy(mLocationUtils.getAccuracy());
		} else {
			Toast.makeText(getActivity(), "Can not get for location",
					Toast.LENGTH_LONG).show();
		}

		mList.add(mRecord);

		// if (mAdapter == getListAdapter())
		// Log.wtf(TAG, "Equal instances!");

		if (mRecordDBHelper.insert(mRecord))
			Log.d(TAG, "can be inserted");
		else
			Log.d(TAG, "can not be inserted");
		notifyDataSetChanged();
	}

	private String percent(String ava, String tot) {
		String ret = null;
		try {
			double available = Double.valueOf(ava.replace(",", "")
					.replace("MB", "").replace("KB", "KB"));
			double total = Double.valueOf(tot.replace(",", "")
					.replace("MB", "").replace("KB", "KB"));
			if (ava.contains("MB") && tot.contains("MB") || ava.contains("KB")
					&& tot.contains("KB")) {
				// ok!
			} else { // to KB
				if (ava.contains("MB"))
					available *= 1024;
				// else :P
				if (tot.contains("MB"))
					total *= 1024;
			}
			Log.d(TAG, "available: " + available + " - total: " + total);
			Double perc = Double.valueOf(available) / Double.valueOf(total);
			perc = ((double) Math.round(perc * 1000)) / 10;
			ret = perc + "%";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	private void notifyDataSetChanged() {
		((ListDbAdapter) getListAdapter()).notifyDataSetChanged();
	}

	private class onBack extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			if (params[0].equals(INSTANCE_LIST)) {
				Log.d(TAG, "empezó");
				mList = mRecordDBHelper.selectAll();
				Log.d(TAG, "terminó");
				return INSTANCE_LIST;
			} else if (params[0].equals(DELETE_RECORD)) {
				int position = Integer.valueOf(params[1]);
				Record toDelete = mList.get(position);
				if (mRecordDBHelper.delete(toDelete.getmRecord_id()))
					mList.remove(position);
				return DELETE_RECORD;
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			if (result == INSTANCE_LIST) {
				Log.d(TAG, "is INSTANCE_LIST");
				onListInstance();
			} else if (result == DELETE_RECORD) {
				Log.d(TAG, "is DELETE_RECORD");
				notifyDataSetChanged();
			}

		}
	}

}
