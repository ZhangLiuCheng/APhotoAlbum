package com.aiyouwai.aphotoalbum.ui.activity.settings;

import android.os.Bundle;

import com.aiyouwai.aphotoalbum.R;
import com.aiyouwai.aphotoalbum.base.AywBaseActivity;
import com.aiyouwai.aphotoalbum.model.entity.Album;

import butterknife.ButterKnife;

/**
 * 上传进度.
 */
public class UploadActivity extends AywBaseActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        ButterKnife.bind(this);
    }
}
