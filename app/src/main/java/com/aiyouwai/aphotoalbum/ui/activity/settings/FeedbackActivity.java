package com.aiyouwai.aphotoalbum.ui.activity.settings;

import android.os.Bundle;

import com.aiyouwai.aphotoalbum.R;
import com.aiyouwai.aphotoalbum.base.AywBaseActivity;

import butterknife.ButterKnife;

/**
 * 意见反馈.
 */
public class FeedbackActivity extends AywBaseActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);
    }
}
