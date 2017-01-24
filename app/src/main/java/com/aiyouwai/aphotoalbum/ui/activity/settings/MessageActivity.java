package com.aiyouwai.aphotoalbum.ui.activity.settings;

import android.os.Bundle;

import com.aiyouwai.aphotoalbum.R;
import com.aiyouwai.aphotoalbum.base.AywBaseActivity;

import butterknife.ButterKnife;

/**
 * 消息中心.
 */
public class MessageActivity extends AywBaseActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
    }
}
