package com.aiyouwai.aphotoalbum.ui.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.aiyouwai.aphotoalbum.R;
import com.aspsine.irecyclerview.RefreshTrigger;

/**
 * Created by aspsine on 16/4/7.
 */
public class RefreshHeaderView extends FrameLayout implements RefreshTrigger {

    private ImageView ivBatMan;

    private ImageView ivSuperMan;

    private ImageView ivVs;

    private int mHeight;

    private ValueAnimator animator;

    public RefreshHeaderView(Context context) {
        this(context, null);
    }

    public RefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.layout_irecyclerview_refresh_header_view, this);
        ivBatMan = (ImageView) findViewById(R.id.ivBatMan);
        ivSuperMan = (ImageView) findViewById(R.id.ivSuperMan);
        ivVs = (ImageView) findViewById(R.id.imageView);


        animator = ValueAnimator.ofInt(0, 360);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                ivVs.setRotation(value);
            }
        });
        animator.setRepeatCount(-1);
        animator.setDuration(1000);
    }

    @Override
    public void onStart(boolean automatic, int headerHeight, int finalHeight) {
        mHeight = headerHeight;
    }

    @Override
    public void onMove(boolean finished, boolean automatic, int moved) {
        if (!finished) {
            ivVs.setRotationY(moved / (float) mHeight * 360);
        } else {
            ivVs.setRotationY(moved / (float) mHeight * 360);
        }
    }

    @Override
    public void onRefresh() {
        ivBatMan.setVisibility(View.GONE);
        ivSuperMan.setVisibility(View.GONE);
        animator.start();
    }

    @Override
    public void onRelease() {
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onReset() {
        ivVs.setRotationY(0);
        ivBatMan.setVisibility(View.VISIBLE);
        ivSuperMan.setVisibility(View.VISIBLE);
        ivVs.setRotation(0);
        animator.cancel();
    }
}
