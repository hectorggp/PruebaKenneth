package com.example.pruebakeneth;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.slidinglayer.SlidingLayer;

public class ServicesActivity extends FragmentActivity implements
		OnClickListener {
	private static final String TAG = "ServicesActivity";
	private static final String TAG_FRAGMENT = "fragment";
	private static final String TAG_SLIDING_OPEN = "sliding";

	private SlidingLayer mSlidingLayer;
	private RelativeLayout rtlLayout1, rtlLayout2, rtlLayout3, rtlLayout4;
	private LinearLayout lnlSelected2, lnlSelected1, lnlSelected3,
			lnlSelected4;
	private int itemSelected;
	private boolean isOpen;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_sliding_main_menu);

		// get views references
		bindViews();

		// inflate correct layout with selection
		if (arg0 == null) {
			selectMenu(R.layout.fragment_db_use);
		} else {
			itemSelected = arg0.getInt(TAG_FRAGMENT);
			selectMenu(itemSelected);
		}

		// sliding menu is open
		isOpen = (arg0 != null && arg0.getBoolean(TAG_SLIDING_OPEN));
		Log.d(TAG, "onCreate: " + isOpen);

	}

	private void selectMenu(int item) {
		View view = new View(getApplicationContext());
		switch (item) {
		case R.layout.fragment_db_use:
			view.setId(R.id.rtlLayout1);
			break;
		case R.layout.fragment_internet:
			view.setId(R.id.rtlLayout2);
			break;
		case R.layout.fragment_records:
			view.setId(R.id.rtlLayout3);
			break;
		case R.layout.fragment_info:
			view.setId(R.id.rtlLayout4);
			break;
		}
		onClick(view);
	}

	@Override
	protected void onResume() {
		super.onResume();

		if (isOpen) {
			if (!mSlidingLayer.isOpened()) {
				mSlidingLayer.openLayer(true);
				Log.d(TAG, "onResume: 1");
			} else
				Log.d(TAG, "onResume: 2");
		} else {
			if (mSlidingLayer.isOpened()) {
				mSlidingLayer.closeLayer(true);
				Log.d(TAG, "onResume: 3");
			} else
				Log.d(TAG, "onResume: 4");
		}

	}

	private void bindViews() {
		mSlidingLayer = (SlidingLayer) findViewById(R.id.slidingLayer1);
		// rlvContent = (RelativeLayout) findViewById(R.id.rlvContent);
		rtlLayout1 = (RelativeLayout) findViewById(R.id.rtlLayout1);
		rtlLayout2 = (RelativeLayout) findViewById(R.id.rtlLayout2);
		rtlLayout3 = (RelativeLayout) findViewById(R.id.rtlLayout3);
		rtlLayout4 = (RelativeLayout) findViewById(R.id.rtlLayout4);
		lnlSelected1 = (LinearLayout) findViewById(R.id.lnlSelected1);
		lnlSelected2 = (LinearLayout) findViewById(R.id.lnlSelected2);
		lnlSelected3 = (LinearLayout) findViewById(R.id.lnlSelected3);
		lnlSelected4 = (LinearLayout) findViewById(R.id.lnlSelected4);

		// functionality
		rtlLayout1.setOnClickListener(this);
		rtlLayout2.setOnClickListener(this);
		rtlLayout3.setOnClickListener(this);
		rtlLayout4.setOnClickListener(this);
		findViewById(R.id.btn).setOnClickListener(this);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		Log.d(TAG, String.format("itemSelected: %s - isOpened: %s",
				itemSelected, mSlidingLayer.isOpened()));
		outState.putInt(TAG_FRAGMENT, itemSelected);
		outState.putBoolean(TAG_SLIDING_OPEN, mSlidingLayer.isOpened());
		super.onSaveInstanceState(outState);
		Log.d(TAG, "onSaveInstanceState");
	}

	private void changeView(int id) {
		if (itemSelected != id) {
			Fragment fragment;
			if (id == R.layout.fragment_db_use) {
				fragment = new DbServiceFragment();
				Log.d(TAG, "db use");
			} else if (id == R.layout.fragment_info) {
				fragment = new InfoFragment();
				Log.d(TAG, "info");
			} else if (id == R.layout.fragment_internet) {
				fragment = new InternetServiceFragment();
				Log.d(TAG, "internet");
			} else {
				fragment = new RecordsServiceFragment();
				Log.d(TAG, "records");
			}
			getFragmentManager().beginTransaction()
					.replace(R.id.rlvContent, fragment).commit();
		}
		Log.i(TAG, itemSelected + " - " + id);
		itemSelected = id;
		Log.i(TAG, itemSelected + " - " + id);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.rtlLayout1:
			lnlSelected1.setVisibility(View.VISIBLE);
			lnlSelected2.setVisibility(View.INVISIBLE);
			lnlSelected3.setVisibility(View.INVISIBLE);
			lnlSelected4.setVisibility(View.INVISIBLE);
			changeView(R.layout.fragment_db_use);
			mSlidingLayer.closeLayer(true);
			break;
		case R.id.rtlLayout2:
			lnlSelected2.setVisibility(View.VISIBLE);
			lnlSelected1.setVisibility(View.INVISIBLE);
			lnlSelected3.setVisibility(View.INVISIBLE);
			lnlSelected4.setVisibility(View.INVISIBLE);
			changeView(R.layout.fragment_internet);
			mSlidingLayer.closeLayer(true);
			break;
		case R.id.rtlLayout3:
			lnlSelected4.setVisibility(View.INVISIBLE);
			lnlSelected3.setVisibility(View.VISIBLE);
			lnlSelected2.setVisibility(View.INVISIBLE);
			lnlSelected1.setVisibility(View.INVISIBLE);
			changeView(R.layout.fragment_records);
			mSlidingLayer.closeLayer(true);
			break;
		case R.id.rtlLayout4:
			lnlSelected3.setVisibility(View.INVISIBLE);
			lnlSelected4.setVisibility(View.VISIBLE);
			lnlSelected2.setVisibility(View.INVISIBLE);
			lnlSelected1.setVisibility(View.INVISIBLE);
			changeView(R.layout.fragment_info);
			mSlidingLayer.closeLayer(true);
			break;
		case R.id.btn:
			if (!mSlidingLayer.isOpened())
				mSlidingLayer.openLayer(true);
			else
				mSlidingLayer.closeLayer(true);
			break;
		default:
			Log.d(TAG, "default");
			break;
		}
	}

}
