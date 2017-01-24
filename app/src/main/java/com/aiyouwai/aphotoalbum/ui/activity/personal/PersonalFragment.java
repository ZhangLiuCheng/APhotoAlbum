package com.aiyouwai.aphotoalbum.ui.activity.personal;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aiyouwai.aphotoalbum.R;
import com.aiyouwai.aphotoalbum.base.AywBaseFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonalFragment extends AywBaseFragment {

    public PersonalFragment() {
    }

    public static PersonalFragment newInstance() {
        PersonalFragment fragment = new PersonalFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.userLayout)
    public void headerClick() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.setting)
    public void setting() {
        Intent intent = new Intent(getActivity(), SettingsActivity.class);
        startActivity(intent);
    }
}
