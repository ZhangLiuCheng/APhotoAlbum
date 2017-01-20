package com.aiyouwai.aphotoalbum.ui.activity.album;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aiyouwai.aphotoalbum.R;
import com.aiyouwai.aphotoalbum.base.AywBaseFragment;
import com.aiyouwai.aphotoalbum.model.entity.Album;
import com.aiyouwai.aphotoalbum.model.entity.Photo;
import com.aiyouwai.aphotoalbum.ui.adapter.PhotoTimeAdapter;
import com.aiyouwai.aphotoalbum.utils.RecyclerItemLisener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoTimeFragment extends AywBaseFragment implements RecyclerItemLisener<Photo> {

    private static final String ARG_PARAM1 = "param1";
    private Album album;

    @BindView(R.id.recylerView) RecyclerView recyclerView;


    public InfoTimeFragment() {
    }

    public static InfoTimeFragment newInstance(Album album) {
        InfoTimeFragment fragment = new InfoTimeFragment();
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
        View view = inflater.inflate(R.layout.fragment_info_time, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setup();
    }

    private void setup() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        PhotoTimeAdapter adapter = new PhotoTimeAdapter(getActivity(), Photo.getTestData());
        adapter.setListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onItemClick(View view, Photo item) {
        Intent intent = new Intent(getActivity(), PhotoDisplayActivity.class);
        startActivity(intent);
    }
}