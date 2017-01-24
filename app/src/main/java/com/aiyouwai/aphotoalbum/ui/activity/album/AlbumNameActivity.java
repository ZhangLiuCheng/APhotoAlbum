package com.aiyouwai.aphotoalbum.ui.activity.album;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.aiyouwai.aphotoalbum.R;
import com.aiyouwai.aphotoalbum.base.AywBaseActivity;
import com.aiyouwai.aphotoalbum.model.entity.Album;
import com.aiyouwai.aphotoalbum.ui.activity.ImagePickerFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 修改相册名称.
 */
public class AlbumNameActivity extends AywBaseActivity  {

    private Album album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_name);
        ButterKnife.bind(this);

        album = (Album) getIntent().getSerializableExtra("album");
    }
}
