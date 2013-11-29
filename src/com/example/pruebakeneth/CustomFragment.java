package com.example.pruebakeneth;

import android.app.Fragment;
import android.util.Log;
import android.view.View;

public class CustomFragment extends Fragment {

	private static final String TAG = "CustomFragment";
	protected View rootView;

	public void setVisibility(int visible) {
		if (rootView != null)
			rootView.setVisibility(visible);
		else
			Log.d(TAG, "is invisible");
	}
}
