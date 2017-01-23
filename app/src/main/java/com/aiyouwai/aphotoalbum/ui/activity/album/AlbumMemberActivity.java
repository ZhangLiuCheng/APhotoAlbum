package com.aiyouwai.aphotoalbum.ui.activity.album;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.aiyouwai.aphotoalbum.R;
import com.aiyouwai.aphotoalbum.base.AywBaseActivity;
import com.aiyouwai.aphotoalbum.model.entity.Member;
import com.aiyouwai.aphotoalbum.model.entity.Photo;
import com.aiyouwai.aphotoalbum.ui.adapter.AlbumMembersAdapter;
import com.aiyouwai.aphotoalbum.ui.adapter.PhotoStaggerdAdapter;
import com.aiyouwai.aphotoalbum.utils.RecyclerItemLisener;
import com.aspsine.irecyclerview.IRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlbumMemberActivity extends AywBaseActivity implements RecyclerItemLisener<Member> {

    @BindView(R.id.recylerView) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_member);
        ButterKnife.bind(this);
        setupMembers();
    }

    private void setupMembers() {
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(manager);
        AlbumMembersAdapter adapter = new AlbumMembersAdapter(this, Member.getTestData());
        adapter.setListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @OnClick(R.id.invite)
    public void invite() {
        Intent intent = new Intent(this, AlbumInviteActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(View view, Member item) {
    }
}
