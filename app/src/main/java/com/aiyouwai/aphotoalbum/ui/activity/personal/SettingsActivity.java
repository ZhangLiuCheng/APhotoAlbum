package com.aiyouwai.aphotoalbum.ui.activity.personal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.aiyouwai.aphotoalbum.R;
import com.aiyouwai.aphotoalbum.base.AywBaseActivity;
import com.aiyouwai.aphotoalbum.ui.activity.album.AlbumInviteActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends AywBaseActivity implements CompoundButton.OnCheckedChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
    }

    @OnClick(R.id.invite)
    public void invite() {
        Intent intent = new Intent(this, AlbumInviteActivity.class);
        startActivity(intent);
    }
}
