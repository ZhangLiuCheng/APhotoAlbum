package com.aiyouwai.aphotoalbum.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.aiyouwai.aphotoalbum.R;
import com.aiyouwai.aphotoalbum.ui.adapter.PhotoStaggerdAdapter;
import com.aiyouwai.aphotoalbum.ui.adapter.PhotoTimeAdapter;
import com.aiyouwai.aphotoalbum.base.AywBaseActivity;
import com.aiyouwai.aphotoalbum.model.db.UserPreferences;
import com.aiyouwai.aphotoalbum.model.entity.Album;
import com.aiyouwai.aphotoalbum.model.entity.Photo;
import com.aiyouwai.aphotoalbum.utils.RecyclerItemLisener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlbumInfoActivity extends AywBaseActivity implements ImagePickerFragment.ImagePickerListener,
        RecyclerItemLisener<Photo> {

    private Album album;

    @BindView(R.id.recylerView) RecyclerView recyclerView;
    @BindView(R.id.pickPhoto) ImageView pickPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_info);
        ButterKnife.bind(this);

        album = (Album) getIntent().getSerializableExtra("album");
//        setupRecylerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupRecylerView();
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

    @OnClick(R.id.setting)
    public void setting() {
        Intent intent = new Intent(this, AlbumSettingActivity.class);
        intent.putExtra("album", album);
        startActivity(intent);
    }

    @OnClick(R.id.pickPhoto)
    public void pickPhoto() {
        pickPhoto.setVisibility(View.GONE);
        ImagePickerFragment fragment = new ImagePickerFragment();
        fragment.showWithAnim(this);
    }

    private void setupRecylerView() {
        int showType = UserPreferences.getInstance().getAlbumShowType(this, album);
        if (showType == 0) {
            StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(manager);
            PhotoStaggerdAdapter adapter = new PhotoStaggerdAdapter(this, Photo.getTestData());
            adapter.setListener(this);
            recyclerView.setAdapter(adapter);
        } else {
            LinearLayoutManager manager = new LinearLayoutManager(this);
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(manager);
            PhotoTimeAdapter adapter = new PhotoTimeAdapter(this, Photo.getTestData());
            adapter.setListener(this);
            recyclerView.setAdapter(adapter);
        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onBitmap(String path) {
        pickPhoto.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemClick(View view, Photo item) {
        Intent intent = new Intent(this, PhotoDisplayActivity.class);
        startActivity(intent);
    }
}
