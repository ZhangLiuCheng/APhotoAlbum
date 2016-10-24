package com.aiyouwai.aphotoalbum.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public abstract class AywBaseFragment extends Fragment {
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	/*
	public void startActivityWithAnim(Intent intent) {
		super.startActivity(intent);
		getActivity().overridePendingTransition(R.anim.activity_right_in, R.anim.activity_left_out);
	}
	
	public void startActivityForResultWithAnim(Intent intent, int requestCode) {
		super.startActivityForResult(intent, requestCode);
		getActivity().overridePendingTransition(R.anim.activity_right_in, R.anim.activity_left_out);
	}
	*/
}
