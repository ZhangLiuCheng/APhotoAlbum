package com.aiyouwai.aphotoalbum.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.aiyouwai.aphotoalbum.R;
import com.aiyouwai.aphotoalbum.adapter.PhotoStaggerdAdapter;
import com.aiyouwai.aphotoalbum.base.AywBaseActivity;
import com.aiyouwai.aphotoalbum.entity.Photo;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumInfoActivity extends AywBaseActivity {

    @BindView(R.id.recylerView) RecyclerView recyclerView;

    private PhotoStaggerdAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_info);
        ButterKnife.bind(this);

        setupRecylerView();
    }

    private void setupRecylerView() {
        adapter = new PhotoStaggerdAdapter(this, Photo.getTestData());

        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}
