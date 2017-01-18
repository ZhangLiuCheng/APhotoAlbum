package com.aiyouwai.aphotoalbum.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aiyouwai.aphotoalbum.R;
import com.aiyouwai.aphotoalbum.base.AywBaseDialogFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class AlbumFuncFragment extends AywBaseDialogFragment {

    public AlbumFuncFragment() {
    }

    public static AlbumFuncFragment newInstance() {
        AlbumFuncFragment fragment = new AlbumFuncFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album_func, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.createAlbum)
    public void createAlbum() {
        dismiss();
        Intent intent = new Intent(getActivity(), AlbumCreateActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.apply)
    public void apply() {
        dismiss();
        Intent intent = new Intent(getActivity(), AlbumApplyActivity.class);
        startActivity(intent);
    }
}
