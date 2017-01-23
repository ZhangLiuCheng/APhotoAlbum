package com.aiyouwai.aphotoalbum.ui.activity.album;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.widget.TextView;
import android.widget.Toast;

import com.aiyouwai.aphotoalbum.R;
import com.aiyouwai.aphotoalbum.ui.activity.ImagePickerFragment;
import com.aiyouwai.aphotoalbum.base.AywBaseActivity;
import com.aiyouwai.aphotoalbum.model.entity.Album;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlbumInfoActivity extends AywBaseActivity implements ImagePickerFragment.ImagePickerListener {

    public static final int REQUEST_CAMERA = 1000;			// 照相机
    public static final int REQUEST_ALBUM = 1001;			// 相册

    private Album album;
    private InfoTimeFragment infoTimeFragment;
    private InfoGridFragment infoGridFragment;
    private int showType;

    @BindView(R.id.showStateText) TextView showStateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_info);
        ButterKnife.bind(this);

        album = (Album) getIntent().getSerializableExtra("album");
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupInfo();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        outState.putSerializable("album", album);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        album = (Album) savedInstanceState.getSerializable("album");
    }

    @OnClick(R.id.setting)
    public void setting() {
        Intent intent = new Intent(this, AlbumSettingActivity.class);
        intent.putExtra("album", album);
        startActivity(intent);
    }

    @OnClick(R.id.pickPhoto)
    public void pickPhoto() {
        final Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("return-data", false);
        startActivityForResult(intent, REQUEST_ALBUM);
    }

    @OnClick(R.id.camera)
    public void camera() {
        final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        try{
            startActivityForResult(intent, REQUEST_CAMERA);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "无法使用相机拍张功能", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.showStateLayout)
    public void changeShow() {
        showType = showType == 0 ? 1 : 0;
        showStateText.setText(showType == 0 ? "信息流查看" : "时间轴查看");
        setupInfo();
    }

    private void setupInfo() {
//        int showType = UserPreferences.getInstance().getAlbumShowType(this, album);
        Fragment fragment;
        if (showType == 0) {
            if (null == infoGridFragment) infoGridFragment = InfoGridFragment.newInstance(album);
            fragment = infoGridFragment;
        } else {
            if (null == infoTimeFragment) infoTimeFragment = InfoTimeFragment.newInstance(album);
            fragment = infoTimeFragment;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.infoLayout, fragment).commit();
    }

    @Override
    public void onBitmap(String path) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
