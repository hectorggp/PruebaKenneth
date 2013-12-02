package com.example.pruebakeneth;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.Dialog;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pruebakeneth.objects.ParseRecord;
import com.example.pruebakeneth.utils.NetworkStateUtils;
import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

public class InternetServiceFragment extends ListFragment implements
		OnClickListener {

	public static final String TAG = "InternetServiceFragment";

	private boolean connect;
	private View rootView;
	private ArrayList<ParseRecord> mList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		connect = NetworkStateUtils.isNetworkAvailable(getActivity());
		if (connect)
			rootView = inflater.inflate(R.layout.fragment_internet, container,
					false);
		else
			rootView = inflater.inflate(R.layout.fragment_internet_no_connect,
					container, false);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (connect) {
			forViews();
			whenIsConnected();
		} else {
			rootView.findViewById(R.id.btnPri).setOnClickListener(this);
		}
	}

	private void forViews() {
		// actions:
		rootView.findViewById(R.id.btnSave).setOnClickListener(this);
	}

	private void whenIsConnected() {
		// instance list
		ParseQuery<ParseObject> query = ParseQuery
				.getQuery(ParseRecord.NAME_OBJECT);
		query.findInBackground(new FindCallback<ParseObject>() {
			public void done(List<ParseObject> scoreList, ParseException e) {
				if (e == null) {
					Log.d(TAG, "Retrieved " + scoreList.size() + " scores");
					if (mList == null)
						mList = new ArrayList<ParseRecord>();
					for (ParseObject mObject : scoreList) {
						Log.d(TAG, "contains: " + mObject.containsKey(ParseRecord.KEY_TAG));
						ParseRecord mRecord = new ParseRecord();
						mRecord.setDate(mObject.getString(ParseRecord.DATE_TAG));
						mRecord.setKey(mObject.getLong(ParseRecord.KEY_TAG));
						mRecord.setNumber(mObject.getString(ParseRecord.NUMBER_TAG));
						mRecord.setText(mObject.getString(ParseRecord.TEXT_TAG));
						mRecord.setParse_objectId(mObject.getObjectId());
						mList.add(mRecord);
					}
					postListInstace();
				} else {
					Log.d(TAG, "Error: " + e.getMessage());
				}
			}
		});

	}

	private void postListInstace() {
		if (mList == null) {
			Log.d(TAG, "mList is null");
			setListAdapter(new ListAdapter());
		} else {
			Log.d(TAG, "mList not is null");
			ListAdapter mAdapter = new ListAdapter(false);
			synchronized (mAdapter) {
				setListAdapter(mAdapter);
			}
		}

		// for examples:
		// for (int i = 0; i < 6; i++) {
		// list.add(new Record());
		// }
	}

	private void save(String text) {
		final ParseRecord mParseRecord = new ParseRecord(Calendar.getInstance()
				.get(Calendar.MILLISECOND));
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		mParseRecord.setDate(dateFormat.format(date));
		mParseRecord.setNumber(mList.size() + "");
		mParseRecord.setText(text);

		ParseObject mObject = new ParseObject(ParseRecord.NAME_OBJECT);
		mObject.put(ParseRecord.DATE_TAG, mParseRecord.getDate());
		mObject.put(ParseRecord.KEY_TAG, mParseRecord.getKey());
		mObject.put(ParseRecord.NUMBER_TAG, mParseRecord.getNumber());
		mObject.put(ParseRecord.TEXT_TAG, mParseRecord.getText());
		mObject.saveInBackground(new SaveCallback() {

			@Override
			public void done(ParseException e) {
				if (e == null) { // all right
					Toast.makeText(getActivity(),
							"Registro guardado en internet!", Toast.LENGTH_LONG)
							.show();
					mList.add(mParseRecord);
					notifyDataSetChanged();
				} else { // error!
					Log.d(TAG, e.getMessage());
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnPri:
			break;
		case R.id.btnSave:
			final Dialog mDialog = new Dialog(getActivity(),
					R.style.CustomDialogTheme);

			mDialog.setCancelable(false);
			mDialog.setContentView(R.layout.dialog_input_text);
			mDialog.findViewById(R.id.txtAceptar).setOnClickListener(
					new OnClickListener() {

						@Override
						public void onClick(View v) {
							mDialog.dismiss();
							save(((EditText) mDialog
									.findViewById(R.id.edtInput)).getText()
									.toString());
						}
					});
			mDialog.show();
			break;
		default:
			break;
		}
	}

	private class PlaceHolder {
		TextView txtId;
		TextView txtDate;
		TextView txtNumber;
		TextView txtText;
	}

	private class ListAdapter extends ArrayAdapter<ParseRecord> {

		public ListAdapter() {
			super(getActivity(), R.layout.listitem_internet);
		}

		public ListAdapter(boolean f) {
			super(getActivity(), R.layout.listitem_internet, mList);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View mView;
			if (convertView == null) {
				mView = getActivity().getLayoutInflater().inflate(
						R.layout.listitem_internet, null);
				PlaceHolder mPlaceHolder = new PlaceHolder();
				mPlaceHolder.txtDate = (TextView) mView
						.findViewById(R.id.txtDate);
				mPlaceHolder.txtId = (TextView) mView.findViewById(R.id.txtId);
				mPlaceHolder.txtNumber = (TextView) mView
						.findViewById(R.id.txtNumber);
				mPlaceHolder.txtText = (TextView) mView
						.findViewById(R.id.txtText);
				mView.setTag(mPlaceHolder);
			} else {
				mView = convertView;
			}
			PlaceHolder mHolder = (PlaceHolder) mView.getTag();
			ParseRecord mParseRecord = mList.get(position);
			mHolder.txtDate.setText(mParseRecord.getDate());
			mHolder.txtId.setText(mParseRecord.getKey() + "");
			mHolder.txtNumber.setText(mParseRecord.getNumber());
			mHolder.txtText.setText(mParseRecord.getText());
			Log.e(TAG, String.format(
					"date: %s - key: %s - number: %s - text: %s",
					mParseRecord.getDate(), mParseRecord.getKey(),
					mParseRecord.getNumber(), mParseRecord.getText()));
			return mView;
		}
	}

	private void notifyDataSetChanged() {
		((ListAdapter) getListAdapter()).notifyDataSetChanged();
	}

	@Override
	public void onListItemClick(ListView l, View v, final int position, long id) {

		final Dialog mDialog = new Dialog(getActivity(),
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
	
	private void delete(final int position) {
		ParseRecord mRecord = mList.get(position);
		ParseObject mObject = new ParseObject(ParseRecord.NAME_OBJECT);
		mObject.setObjectId(mRecord.getParse_objectId());
		mObject.deleteInBackground(new DeleteCallback() {
			
			@Override
			public void done(ParseException e) {
				if (e == null) { // all right
					Toast.makeText(getActivity(), "fue borrado!", Toast.LENGTH_LONG).show();
					mList.remove(position);
					notifyDataSetChanged();
				} else { // error
					Log.d(TAG, "error on delete: " + e.getMessage());
				}
			}
		});
	}

}
