package com.aiyouwai.aphotoalbum.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aiyouwai.aphotoalbum.R;
import com.aiyouwai.aphotoalbum.base.AywBaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonActivity extends AywBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        ButterKnife.bind(this);
    }
}
