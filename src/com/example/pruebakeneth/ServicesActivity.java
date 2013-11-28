package com.example.pruebakeneth;

import android.content.Context;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.slidinglayer.SlidingLayer;

public class ServicesActivity extends FragmentActivity implements OnClickListener{
	private static final String TAG = "ServicesActivity";
	private static final String TAG_FRAGMENT = "fragment";
	private static final String TAG_SLIDING_OPEN = "sliding";

	private SlidingLayer mSlidingLayer;
	private RelativeLayout rlvContent;
	private LinearLayout lnlSelected2, lnlSelected1, lnlSelected3;
	private int itemSelected = R.layout.fragment_db_use;
	private boolean isOpen;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_sliding_main_menu);

		bindViews();
		
		// inflate correct layout
		changeView(arg0 == null ? itemSelected : arg0.getInt(TAG_FRAGMENT));		

		// sliding menu is open
		isOpen = (arg0 != null && arg0.getBoolean(TAG_SLIDING_OPEN)); 
		Log.d(TAG, "onCreate: " + isOpen);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		if (isOpen){
			if (!mSlidingLayer.isOpened())
				mSlidingLayer.openLayer(true);
		} else {
			if(mSlidingLayer.isOpened())
				mSlidingLayer.closeLayer(true);
		}
		
		Log.d(TAG, "onResume");
	}

	private void bindViews() {
		mSlidingLayer = (SlidingLayer) findViewById(R.id.slidingLayer1);
		rlvContent = (RelativeLayout) findViewById(R.id.rlvContent);
		lnlSelected1 = (LinearLayout) findViewById(R.id.lnlSelected1);
		lnlSelected2 = (LinearLayout) findViewById(R.id.lnlSelected2);
		lnlSelected3 = (LinearLayout) findViewById(R.id.lnlSelected3);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		Log.d(TAG, String.format("itemSelected: %s - isOpened: %s", itemSelected, mSlidingLayer.isOpened()));
		outState.putInt(TAG_FRAGMENT, itemSelected);
		outState.putBoolean(TAG_SLIDING_OPEN, mSlidingLayer.isOpened());
		super.onSaveInstanceState(outState);
		Log.d(TAG, "onSaveInstanceState");
	}

	public void click(View view) {
		switch (view.getId()) {
		case R.id.rtlLayout1:
			lnlSelected1.setVisibility(View.VISIBLE);
			lnlSelected2.setVisibility(View.INVISIBLE);
			lnlSelected3.setVisibility(View.INVISIBLE);
			changeView(R.layout.fragment_db_use);
			mSlidingLayer.closeLayer(true);
			break;
		case R.id.rtlLayout2:
			lnlSelected2.setVisibility(View.VISIBLE);
			lnlSelected1.setVisibility(View.INVISIBLE);
			lnlSelected3.setVisibility(View.INVISIBLE);
			changeView(R.layout.fragment_internet);
			mSlidingLayer.closeLayer(true);
			break;
		case R.id.rtlLayout3:
			lnlSelected3.setVisibility(View.VISIBLE);
			lnlSelected2.setVisibility(View.INVISIBLE);
			lnlSelected1.setVisibility(View.INVISIBLE);
			changeView(R.layout.fragment_records);
			mSlidingLayer.closeLayer(true);
			break;
		default:
			Log.d(TAG, "default");
			break;
		}
	}

	private void changeView(int id) {
		LayoutInflater inflater = (LayoutInflater) getApplicationContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LayoutParams params = rlvContent.getLayoutParams();
		View view = inflater.inflate(id, null);
		view.setLayoutParams(params);
		rlvContent.removeAllViews();
		rlvContent.addView(view);
		view.findViewById(R.id.btn).setOnClickListener(this);
		itemSelected = id;
	}

	@Override
	public void onClick(View v) {
		if(!mSlidingLayer.isOpened()){
			mSlidingLayer.openLayer(true);
		}
	}

}
