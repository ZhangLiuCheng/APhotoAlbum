package com.aiyouwai.aphotoalbum.ui.activity.homepage;

import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;

import com.aiyouwai.aphotoalbum.R;
import com.aiyouwai.aphotoalbum.ui.activity.album.AlbumInfoActivity;
import com.aiyouwai.aphotoalbum.ui.adapter.AlbumAdapter;
import com.aiyouwai.aphotoalbum.base.AywBaseActivity;
import com.aiyouwai.aphotoalbum.model.entity.Album;
import com.aiyouwai.aphotoalbum.utils.RecyclerItemLisener;
import com.aiyouwai.aphotoalbum.ui.widget.LoadMoreFooterView;
import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AywBaseActivity implements RecyclerItemLisener<Album>,
        OnRefreshListener, OnLoadMoreListener {

    private Handler handler = new Handler();

    private LoadMoreFooterView loadMoreFooterView;
    private AlbumAdapter adapter;

    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.recylerView) IRecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupRecyclerView(Album.testData());
    }

    @OnClick(R.id.personal)
    public void personal() {
//        Intent intent = new Intent(this, PersonActivity.class);
//        startActivity(intent);

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    @OnClick(R.id.albumFunc)
    public void albumFunc(View view) {
        FuncPopView popView = new FuncPopView(this);
        popView.showAsDropDown(view, 0, 30);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void setupRecyclerView(List<Album> links) {
        adapter = new AlbumAdapter(this, links);
        loadMoreFooterView = (LoadMoreFooterView) recyclerView.getLoadMoreFooterView();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setIAdapter(adapter);
        recyclerView.setOnRefreshListener(this);
        recyclerView.setOnLoadMoreListener(this);
        adapter.setListener(this);

        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                recyclerView.setRefreshing(true);
            }
        });
    }

    private void inputTitleDialog() {
        final EditText inputServer = new EditText(this);
        inputServer.setFocusable(true);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请输入密码").setView(inputServer).setNegativeButton("取消", null);
        builder.setPositiveButton("确认",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String inputName = inputServer.getText().toString();
                        Intent intent = new Intent(MainActivity.this, AlbumInfoActivity.class);
                        startActivity(intent);
                    }
                });
        builder.show();
    }

    @Override
    public void onItemClick(View view, Album item) {
        if (item.isPrivacy()) {
            inputTitleDialog();
        } else {
            Intent intent = new Intent(this, AlbumInfoActivity.class);
            intent.putExtra("album", item);
//            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }
    }

    @Override
    public void onLoadMore() {
        if (loadMoreFooterView.canLoadMore() && adapter.getItemCount() > 0) {
            loadMoreFooterView.setStatus(LoadMoreFooterView.Status.LOADING);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    adapter.append(Album.testData());
                    loadMoreFooterView.setStatus(LoadMoreFooterView.Status.GONE);
                }
            }, 1000);
        }
    }

    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.setData(Album.testData());
                recyclerView.setRefreshing(false);
            }
        }, 2000);
    }
}
