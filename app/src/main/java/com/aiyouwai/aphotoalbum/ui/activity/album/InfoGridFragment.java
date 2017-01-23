package com.aiyouwai.aphotoalbum.ui.activity.album;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.aiyouwai.aphotoalbum.R;
import com.aiyouwai.aphotoalbum.base.AywBaseFragment;
import com.aiyouwai.aphotoalbum.model.entity.Album;
import com.aiyouwai.aphotoalbum.model.entity.Photo;
import com.aiyouwai.aphotoalbum.ui.adapter.PhotoStaggerdAdapter;
import com.aiyouwai.aphotoalbum.utils.RecyclerItemLisener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InfoGridFragment extends AywBaseFragment implements RecyclerItemLisener<Photo> {

    private static final String ARG_PARAM1 = "param1";
    private Album album;

    @BindView(R.id.recylerView) RecyclerView recyclerView;
    @BindView(R.id.members) LinearLayout membersLayout;

    public InfoGridFragment() {
    }

    public static InfoGridFragment newInstance(Album album) {
        InfoGridFragment fragment = new InfoGridFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, album);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            album = (Album) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_grid, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupMembers();
        setupRecycleView();
    }

    private void setupMembers() {
        for (int i = 0; i < 5; i ++){
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(R.drawable.album_member);
            imageView.setClickable(false);
            membersLayout.addView(imageView);
        }
    }

    private void setupRecycleView() {
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        PhotoStaggerdAdapter adapter = new PhotoStaggerdAdapter(getActivity(), Photo.getTestData());
        adapter.setListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @OnClick({R.id.members, R.id.memberLayout, R.id.scrollView})
    public void membersLayout() {
        Intent intent = new Intent(getActivity(), AlbumMemberActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(View view, Photo item) {
        Intent intent = new Intent(getActivity(), PhotoDisplayActivity.class);
        startActivity(intent);
    }
}