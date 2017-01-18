package com.aiyouwai.aphotoalbum.ui.activity;

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

public class AlbumCreateActivity extends AywBaseActivity implements CompoundButton.OnCheckedChangeListener,
        AdapterView.OnItemSelectedListener {

    @BindView(R.id.spinnerPrivacy) Spinner spinner;
    @BindView(R.id.privacy) Switch privacy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_create);
        ButterKnife.bind(this);

        setupPrivacy();
        setupSpinner();
    }

    // 是否为私密相册
    private void setupPrivacy() {
        privacy.setOnCheckedChangeListener(this);
    }

    private void setupSpinner() {
        List<String> data = new ArrayList<>();
        data.add("进入空相册");
        data.add("提示密码错误");

        ArrayAdapter adapter= new ArrayAdapter<>(this, R.layout.item_spinner_album, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
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
