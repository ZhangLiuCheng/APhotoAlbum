package com.aiyouwai.aphotoalbum.ui.activity.album;

import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.SwitchCompat;
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

    @BindView(R.id.spinnerPrivacy) AppCompatSpinner spinner;
    @BindView(R.id.privacy) SwitchCompat privacy;

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
        data.add("您初恋女友的名字？");
        data.add("对你影响最深的一个人？");
        data.add("你的幸运数字？");
        data.add("你最爱的一个人？");
        data.add("最难忘的地方？");

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
