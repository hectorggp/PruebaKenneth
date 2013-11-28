package com.example.pruebakeneth;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
	private InfoFragment mInfoFragment;
	private DbServiceFragment mDbServiceFragment;
	private RecordsServiceFragment mRecordsServiceFragment;
	private InternetServiceFragment mInternetServiceFragment;
	private RelativeLayout rtlLayout1, rtlLayout2, rtlLayout3,
			rtlLayout4;
	private LinearLayout lnlSelected2, lnlSelected1, lnlSelected3,
			lnlSelected4;
	private int itemSelected = R.layout.fragment_db_use;
	private boolean isOpen;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_sliding_main_menu);

		// get views references
		bindViews();

		// set fragments
		addFragments();

		// inflate correct layout with selection
		selectMenu(arg0 == null ? itemSelected : arg0.getInt(TAG_FRAGMENT));

		// sliding menu is open
		isOpen = (arg0 != null && arg0.getBoolean(TAG_SLIDING_OPEN));
		Log.d(TAG, "onCreate: " + isOpen);

	}

	private void addFragments() {
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		mDbServiceFragment = new DbServiceFragment();
		fragmentTransaction.add(R.id.fragment_db, mDbServiceFragment);

		mInternetServiceFragment = new InternetServiceFragment();
		fragmentTransaction.add(R.id.fragment_internet, mInternetServiceFragment);
		
		mInfoFragment = new InfoFragment();
		fragmentTransaction.add(R.id.fragment_info, mInfoFragment);
		
//		mRecordsServiceFragment = new RecordsServiceFragment();
//		fragmentTransaction.add(R.id.fragment_records, mRecordsServiceFragment);
		fragmentTransaction.commit();
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
//		rlvContent = (RelativeLayout) findViewById(R.id.rlvContent);
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
		// LayoutInflater inflater = (LayoutInflater) getApplicationContext()
		// .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// LayoutParams params = rlvContent.getLayoutParams();
		// View view = inflater.inflate(id, null);
		//
		// view.setLayoutParams(params);
		// rlvContent.removeAllViews();
		// rlvContent.addView(view);

		itemSelected = id;
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.rtlLayout1:
			lnlSelected1.setVisibility(View.VISIBLE);
			lnlSelected2.setVisibility(View.INVISIBLE);
			lnlSelected3.setVisibility(View.INVISIBLE);
			lnlSelected4.setVisibility(View.INVISIBLE);
//			mRecordsServiceFragment.setVisibility(View.INVISIBLE);
			mInternetServiceFragment.setVisibility(View.INVISIBLE);
			mInfoFragment.setVisibility(View.INVISIBLE);
			mDbServiceFragment.setVisibility(View.VISIBLE);
			changeView(R.layout.fragment_db_use);
			mSlidingLayer.closeLayer(true);
			break;
		case R.id.rtlLayout2:
			lnlSelected2.setVisibility(View.VISIBLE);
			lnlSelected1.setVisibility(View.INVISIBLE);
			lnlSelected3.setVisibility(View.INVISIBLE);
			lnlSelected4.setVisibility(View.INVISIBLE);
//			mRecordsServiceFragment.setVisibility(View.GONE);
			mInternetServiceFragment.setVisibility(View.VISIBLE);
			mInfoFragment.setVisibility(View.GONE);
			mDbServiceFragment.setVisibility(View.GONE);
			changeView(R.layout.fragment_internet);
			mSlidingLayer.closeLayer(true);
			break;
		case R.id.rtlLayout3:
			lnlSelected4.setVisibility(View.INVISIBLE);
			lnlSelected3.setVisibility(View.VISIBLE);
			lnlSelected2.setVisibility(View.INVISIBLE);
			lnlSelected1.setVisibility(View.INVISIBLE);
//			mRecordsServiceFragment.setVisibility(View.VISIBLE);
			mInternetServiceFragment.setVisibility(View.GONE);
			mInfoFragment.setVisibility(View.GONE);
			mDbServiceFragment.setVisibility(View.GONE);
			changeView(R.layout.fragment_records);
			mSlidingLayer.closeLayer(true);
			break;
		case R.id.rtlLayout4:
			lnlSelected3.setVisibility(View.INVISIBLE);
			lnlSelected4.setVisibility(View.VISIBLE);
			lnlSelected2.setVisibility(View.INVISIBLE);
			lnlSelected1.setVisibility(View.INVISIBLE);
//			mRecordsServiceFragment.setVisibility(View.GONE);
			mInternetServiceFragment.setVisibility(View.GONE);
			mInfoFragment.setVisibility(View.VISIBLE);
			mDbServiceFragment.setVisibility(View.GONE);
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
