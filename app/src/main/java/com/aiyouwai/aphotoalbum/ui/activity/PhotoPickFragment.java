package com.aiyouwai.aphotoalbum.ui.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.aiyouwai.aphotoalbum.R;
import com.aiyouwai.aphotoalbum.base.AywBaseDialogFragment;
import com.aiyouwai.aphotoalbum.utils.LogUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class PhotoPickFragment extends AywBaseDialogFragment {

    public PhotoPickFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_pick, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LogUtil.v("onCreateDialog");

        Dialog dialog =  super.onCreateDialog(savedInstanceState);
        LogUtil.v("---------->     " + dialog);

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                LogUtil.v("onDismiss");
            }
        });
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        LinearLayout animLayout = (LinearLayout) getView().findViewById(R.id.animLayout);
        animLayout.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fragment_pop_enter));
    }

    @Override
    public void dismiss() {
        LogUtil.v("============>     " + getDialog());

//        getDialog().setOnDismissListener(new DialogInterface.OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialog) {
//                LogUtil.v("onDismiss1111");
//
//            }
//        });
        Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.fragment_pop_exit);
        LinearLayout animLayout = (LinearLayout) getView().findViewById(R.id.animLayout);
        animLayout.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                PhotoPickFragment.super.dismiss();
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }

    @OnClick(R.id.connect)
    public void connect() {
        dismiss();
    }

    @OnClick(R.id.speed)
    public void speed() {
        dismiss();
    }

    @OnClick(R.id.cancel)
    public void cancel() {
        dismiss();
    }
}
