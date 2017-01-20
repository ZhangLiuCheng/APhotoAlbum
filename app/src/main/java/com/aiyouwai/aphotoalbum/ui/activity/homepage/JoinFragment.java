package com.aiyouwai.aphotoalbum.ui.activity.homepage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aiyouwai.aphotoalbum.R;
import com.aiyouwai.aphotoalbum.base.AywBaseDialogFragment;
import com.aiyouwai.aphotoalbum.utils.ToastUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 输入好友邀请码加入.
 */
public class JoinFragment extends AywBaseDialogFragment {

    public JoinFragment() {
    }

    public static JoinFragment newInstance() {
        JoinFragment fragment = new JoinFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_join, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.join)
    public void join() {
        ToastUtil.show(getActivity(), "申请成功,等待对方同意。");
        dismiss();
    }
}
