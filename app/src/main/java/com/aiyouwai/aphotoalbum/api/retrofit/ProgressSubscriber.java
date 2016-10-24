package com.aiyouwai.aphotoalbum.api.retrofit;

import android.app.Dialog;
import android.content.Context;

import com.aiyouwai.aphotoalbum.utils.DialogUtil;
import com.aiyouwai.aphotoalbum.utils.ToastUtil;

import rx.Subscriber;

public abstract class ProgressSubscriber<T> extends Subscriber<T> {

    private Context context;
    private Dialog dialog;

    public ProgressSubscriber(Context context, String messsage) {
        this.context = context;
        dialog = DialogUtil.getProgressDialog(context, messsage);
    }

    public ProgressSubscriber(Context context) {
        this(context, "加载中");
    }

    @Override
    public void onStart() {
        super.onStart();
        DialogUtil.showDialog(dialog);
    }

    @Override
    public void onCompleted() {
        DialogUtil.dismissDialog(dialog);
    }

    @Override
    public void onError(Throwable e) {
        DialogUtil.dismissDialog(dialog);
        ToastUtil.show(context, e.getMessage());
    }
}
