package com.aiyouwai.aphotoalbum.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.aiyouwai.aphotoalbum.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

public class AywBaseActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setTintColor(Color.parseColor("#FFFFFF"));
    }

    /**
     * 设置toolbar和返回监听事件.
     */
    protected void setupToolbar(String title) {
        try {
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
        } catch (Exception e) {
            throw new RuntimeException("activity的xml里面缺少toolbar");
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
        TextView titleView = (TextView) toolbar.findViewById(R.id.titleView);
        titleView.setText(title);
    }

    protected Toolbar getToolBar() {
        return toolbar;
    }

    /**
     * 导航栏和物理按键返回点击调用该方法.
     */
    public void back() {
        finish();
    }

    @Override
    public void onBackPressed() {
        back();
    }

    /**
     * 设置push动画启动.
     * @param intent
     */
//    public void startActivityWithAnim(Intent intent) {
//        super.startActivity(intent);
//        overridePendingTransition(R.anim.activity_right_in, R.anim.activity_left_out);
//    }
//
//    public void startActivityForResultWithAnim(Intent intent, int requestCode) {
//        super.startActivityForResult(intent, requestCode);
//        overridePendingTransition(R.anim.activity_right_in, R.anim.activity_left_out);
//    }

    /**
     * 设置pop动画返回.
     */
//    public void finishActivityWithAnim() {
//        this.finish();
//        overridePendingTransition(R.anim.activity_left_in, R.anim.activity_right_out);
//    }
}
