package com.aiyouwai.aphotoalbum.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;

import com.aiyouwai.aphotoalbum.R;
import com.aiyouwai.aphotoalbum.base.AywBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumSettingActivity extends AywBaseActivity implements CompoundButton.OnCheckedChangeListener,
        AdapterView.OnItemSelectedListener {

    @BindView(R.id.switchPrivacy) Switch switchPrivacy;
    @BindView(R.id.spinnerPrivacy) Spinner spinnerPrivacy;
    @BindView(R.id.spinnerShow) Switch spinnerShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_setting);
        ButterKnife.bind(this);

        setupPrivacy();
    }

    // 是否为私密相册
    private void setupPrivacy() {
        switchPrivacy.setOnCheckedChangeListener(this);
        List<String> data = new ArrayList<>();
        data.add("进入空相册");
        data.add("提示密码错误");

        ArrayAdapter adapter= new ArrayAdapter<>(this, R.layout.item_spinner_album, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPrivacy.setAdapter(adapter);
        spinnerPrivacy.setOnItemSelectedListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        LinearLayout privacyLayout = (LinearLayout) findViewById(R.id.privacyLayout);
        privacyLayout.setVisibility(isChecked ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
