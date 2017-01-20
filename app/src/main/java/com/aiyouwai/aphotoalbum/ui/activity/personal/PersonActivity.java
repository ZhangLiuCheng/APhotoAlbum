package com.aiyouwai.aphotoalbum.ui.activity.personal;

import android.content.Intent;
import android.os.Bundle;

import com.aiyouwai.aphotoalbum.R;
import com.aiyouwai.aphotoalbum.base.AywBaseActivity;
import com.aiyouwai.aphotoalbum.ui.activity.personal.LoginActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonActivity extends AywBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.userLayout)
    public void userLayout() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
