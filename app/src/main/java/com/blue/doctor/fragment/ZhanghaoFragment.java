package com.blue.doctor.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.blue.doctor.LogonActivity;
import com.blue.doctor.R;
import com.blue.doctor.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author yuanbing
 * @ClassName: BlueDoctor
 * @Description: (这里用一句话描述这个类的作用)
 * @email 1260380285@qq.com
 * @date 2015/7/10
 */
public class ZhanghaoFragment extends BaseFragment {
    @Bind(R.id.logon_button)
    Button logonButton;

    private static final  String TAG=ZhanghaoFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.zhanghao, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.logon_button)
    public void goToLogonPage() {
        Log.i(TAG,"goToLogonPage");
        Intent intent = new Intent(getActivity(), LogonActivity.class);
        startActivity(intent);
    }
}
