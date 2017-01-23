package com.aiyouwai.aphotoalbum.ui.activity.album;

import android.content.Intent;
import android.os.Bundle;

import com.aiyouwai.aphotoalbum.R;
import com.aiyouwai.aphotoalbum.base.AywBaseActivity;
import com.aiyouwai.aphotoalbum.model.entity.Album;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlbumSettingActivity extends AywBaseActivity {

    private Album album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_setting);
        ButterKnife.bind(this);

        album = (Album) getIntent().getSerializableExtra("album");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        outState.putSerializable("album", album);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        album = (Album) savedInstanceState.getSerializable("album");
    }

    @OnClick(R.id.invite)
    public void invite() {
        Intent intent = new Intent(this, AlbumInviteActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.members)
    public void members() {
        Intent intent = new Intent(this, AlbumMemberActivity.class);
        startActivity(intent);
    }
}
