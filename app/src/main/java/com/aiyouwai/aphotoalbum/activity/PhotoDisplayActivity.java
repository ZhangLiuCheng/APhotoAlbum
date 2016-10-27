package com.aiyouwai.aphotoalbum.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.WindowManager;

import com.aiyouwai.aphotoalbum.R;
import com.aiyouwai.aphotoalbum.adapter.PhotoAdapter;
import com.aiyouwai.aphotoalbum.base.AywBaseActivity;
import com.aiyouwai.aphotoalbum.entity.Photo;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoDisplayActivity extends AywBaseActivity {

    @BindView(R.id.viewPage) ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_display);
        ButterKnife.bind(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setupViewPage();
    }

    private void setupViewPage() {
        viewPager.setAdapter(new PhotoAdapter(this, Photo.getTestData()));
    }
}
