package com.blue.doctor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blue.doctor.base.BaseActivity;
import com.blue.doctor.http.HttpApi;
import com.blue.doctor.util.DiagUtil;
import com.blue.doctor.util.StringUtils;
import com.blue.doctor.util.TDevice;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author yuanbing
 * @ClassName: BlueDoctor
 * @Description: (这里用一句话描述这个类的作用)
 * @email 1260380285@qq.com
 * @date 2015/7/12
 */
public class RegisterActivity extends BaseActivity implements HttpApi.IInterface {
    @Bind(R.id.title_image)
    ImageView titleImage;
    @Bind(R.id.title)
    TextView titleTextView;
    @Bind(R.id.verify_button)
    Button verifyButton;
    @Bind(R.id.register_button)
    Button registerButton;
    //@Bind(R.id.phone)
    //EditText phone;

    //String phone_text;
    //http://121.40.17.228:180/api/common?userType=1&verType=1&userPhone=13336176591
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        ButterKnife.bind(this);
        init();


    }

    private void init() {
        titleImage.setVisibility(View.VISIBLE);
        titleTextView.setText(getString(R.string.register));
    }

    @Override
    public void getVerifyCode() {
        setType("1");
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
