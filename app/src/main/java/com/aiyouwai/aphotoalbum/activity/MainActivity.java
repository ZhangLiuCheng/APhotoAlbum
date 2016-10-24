package com.aiyouwai.aphotoalbum.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.aiyouwai.aphotoalbum.R;
import com.aiyouwai.aphotoalbum.adapter.AlbumAdapter;
import com.aiyouwai.aphotoalbum.base.AywBaseActivity;
import com.aiyouwai.aphotoalbum.entity.Album;
import com.aiyouwai.aphotoalbum.utils.RecyclerItemLisener;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AywBaseActivity implements RecyclerItemLisener<Album> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupRecyclerView(Album.testData());
    }

    @OnClick(R.id.personal)
    public void personal() {
        Intent intent = new Intent(this, PersonActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.albumFunc)
    public void albumFunc() {
        AlbumFuncFragment fragment = AlbumFuncFragment.newInstance();
        fragment.show(getSupportFragmentManager(), null);
    }

    private void setupRecyclerView(List<Album> links) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recylerView);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        AlbumAdapter adapter = new AlbumAdapter(this, links);
        recyclerView.setAdapter(adapter);
        adapter.setListener(this);
    }

    @Override
    public void onItemClick(View view, Album item) {

    }
}
