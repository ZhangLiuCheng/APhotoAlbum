package com.aiyouwai.aphotoalbum.ui.activity.album;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aiyouwai.aphotoalbum.R;
import com.aiyouwai.aphotoalbum.base.AywBaseActivity;
import com.aiyouwai.aphotoalbum.model.entity.Album;
import com.aiyouwai.aphotoalbum.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PasswordInputActivity extends AywBaseActivity {

    private Album album;
    private StringBuilder sb = new StringBuilder();
    private int index;

    @BindView(R.id.passwordLayout) LinearLayout passwordLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_input);
        ButterKnife.bind(this);

        album = (Album) getIntent().getSerializableExtra("album");
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

    public void inputDigit(View view) {
        if (index >= 6 || !(view instanceof TextView)) return;
        TextView tv = (TextView) view;
        sb.append(tv.getText().toString());

        ImageView pwd = (ImageView) passwordLayout.getChildAt(index);
        pwd.setSelected(true);
        index++;
        if (index == 6) {
            ToastUtil.show(this, "密码是：" + sb.toString());
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    resetPassowrd();
                }
            }, 1000);
        }

    }

    private void resetPassowrd() {
        sb.delete(0, sb.length());
        index = 0;
        for (int i = 0; i < passwordLayout.getChildCount(); i ++) {
            passwordLayout.getChildAt(i).setSelected(false);
        }
    }
}
