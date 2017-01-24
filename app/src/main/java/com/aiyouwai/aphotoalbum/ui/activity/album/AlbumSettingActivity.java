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

public class AlbumSettingActivity extends AywBaseActivity implements ImagePickerFragment.ImagePickerListener  {

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
        startActivityWithAnim(intent);
    }

    @OnClick(R.id.cover)
    public void cover() {
        ImagePickerFragment pickerFragment = new ImagePickerFragment();
        pickerFragment.showWithAnim(this);
    }

    @OnClick(R.id.modifyName)
    public void modifyName() {
        Intent intent = new Intent(this, AlbumNameActivity.class);
        startActivityWithAnim(intent);
    }

    @OnClick(R.id.members)
    public void members() {
        Intent intent = new Intent(this, AlbumMemberActivity.class);
        startActivityWithAnim(intent);
    }

    @OnClick(R.id.link)
    public void link() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("已复制到剪贴板，快去粘贴发送吧！").setPositiveButton("确认", null);
        builder.show();
    }

    @OnClick(R.id.delete)
    public void delete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示").setMessage("是否确认删除该空间，一旦删除无法恢复图片！").setNegativeButton("取消", null);
        builder.setPositiveButton("确认",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        builder.show();
    }

    @Override
    public void onBitmap(String path) {

    }
}
