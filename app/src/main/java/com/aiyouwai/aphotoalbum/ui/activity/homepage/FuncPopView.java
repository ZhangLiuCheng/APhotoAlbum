package com.aiyouwai.aphotoalbum.ui.activity.homepage;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.aiyouwai.aphotoalbum.R;
import com.aiyouwai.aphotoalbum.base.AywBaseActivity;
import com.aiyouwai.aphotoalbum.ui.activity.album.AlbumCreateActivity;

public class FuncPopView {

    private AywBaseActivity activity;

    private PopupWindow mPopupWindow;
    private View mView;

    public FuncPopView(AywBaseActivity activity) {
        this.activity = activity;
        initPopView();
        setupListener();
    }

    public void showAsDropDown(View view, int xoff, int yoff) {
        mPopupWindow.showAsDropDown(view, xoff, yoff);
    }

    private void initPopView() {
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 180, activity.getResources().getDisplayMetrics());
        mView = LayoutInflater.from(activity).inflate(R.layout.popview_func, null);
        mPopupWindow = new PopupWindow(mView, width, WindowManager.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setAnimationStyle(0);
        mPopupWindow.getContentView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mPopupWindow.dismiss();
                return false;
            }
        });

        // 响应返回按钮
        ColorDrawable dw = new ColorDrawable(0x00000000);
        mPopupWindow.setBackgroundDrawable(dw);
    }

    private void setupListener() {
        mView.findViewById(R.id.create).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
                Intent intent = new Intent(activity, AlbumCreateActivity.class);
                activity.startActivity(intent);
            }
        });

        mView.findViewById(R.id.apply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
                JoinFragment fragment = JoinFragment.newInstance();
                fragment.show(activity.getSupportFragmentManager(), null);
            }
        });

        mView.findViewById(R.id.barCode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mPopupWindow.dismiss();
//                Intent intent = new Intent(activity, AlbumApplyActivity.class);
//                activity.startActivity(intent);
            }
        });
    }
}
