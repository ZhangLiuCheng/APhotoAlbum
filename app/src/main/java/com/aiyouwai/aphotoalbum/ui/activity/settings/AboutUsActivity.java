package com.aiyouwai.aphotoalbum.ui.activity.settings;

import android.os.Bundle;

import com.aiyouwai.aphotoalbum.R;
import com.aiyouwai.aphotoalbum.base.AywBaseActivity;

import butterknife.ButterKnife;

/**
 * 关于我们.
 */
public class AboutUsActivity extends AywBaseActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ButterKnife.bind(this);
    }
}
