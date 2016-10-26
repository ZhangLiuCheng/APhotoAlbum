package com.aiyouwai.aphotoalbum.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.aiyouwai.aphotoalbum.R;
import com.aiyouwai.aphotoalbum.adapter.PhotoStaggerdAdapter;
import com.aiyouwai.aphotoalbum.base.AywBaseActivity;
import com.aiyouwai.aphotoalbum.entity.Photo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlbumInfoActivity extends AywBaseActivity implements ImagePickerFragment.ImagePickerListener {

    @BindView(R.id.recylerView) RecyclerView recyclerView;
    @BindView(R.id.pickPhoto) ImageView pickPhoto;

    private PhotoStaggerdAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_info);
        ButterKnife.bind(this);

        setupRecylerView();
    }

    @OnClick(R.id.setting)
    public void setting() {
        Intent intent = new Intent(this, AlbumSettingActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.pickPhoto)
    public void pickPhoto() {
        pickPhoto.setVisibility(View.GONE);
//        PhotoPickFragment fragment = new PhotoPickFragment();
//        fragment.show(getSupportFragmentManager(), null);
        ImagePickerFragment fragment = new ImagePickerFragment();
        fragment.showWithAnim(this);
    }

    private void setupRecylerView() {
        adapter = new PhotoStaggerdAdapter(this, Photo.getTestData());

        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBitmap(String path) {
        pickPhoto.setVisibility(View.VISIBLE);
    }
}
