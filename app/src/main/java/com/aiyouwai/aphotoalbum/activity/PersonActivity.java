package com.aiyouwai.aphotoalbum.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.aiyouwai.aphotoalbum.R;
import com.aiyouwai.aphotoalbum.base.AywBaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonActivity extends AywBaseActivity implements ImagePickerFragment.ImagePickerListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.pickPhoto)
    public void pickPhoto() {
//        PhotoPickFragment fragment = new PhotoPickFragment();
//        fragment.show(getSupportFragmentManager(), null);
        ImagePickerFragment fragment = new ImagePickerFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("crop", true);
        bundle.putInt("ratio", 1);
        fragment.setArguments(bundle);
        fragment.showWithAnim(this);
    }

    @Override
    public void onBitmap(String path) {
        ImageView image = (ImageView) findViewById(R.id.image);
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        image.setImageBitmap(bitmap);
    }
}
