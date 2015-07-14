package com.blue.doctor;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blue.doctor.base.BaseActivity;
import com.blue.doctor.http.HttpApi;
import com.blue.doctor.util.DiagUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author yuanbing
 * @ClassName: BlueDoctor
 * @Description: (这里用一句话描述这个类的作用)
 * @email 1260380285@qq.com
 * @date 2015/7/13
 */
public class ModifyPwdActivity extends BaseActivity implements HttpApi.IInterface{
    @Bind(R.id.title_image)
    ImageView titleImage;
    @Bind(R.id.title)
    TextView titleTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        ButterKnife.bind(this);
        init();
    }
    private void init() {
        titleImage.setVisibility(View.VISIBLE);
        titleTextView.setText(getString(R.string.modify));
    }

    @Override
    public void getVerifyCode() {
        setType("2");
        super.getVerifyCode();
    }
    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();

    }
    @Override
    public void callBack(String result) {
        DiagUtil.dismissWaitDialog();
    }
}
