package com.blue.doctor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blue.doctor.base.BaseActivity;
import com.blue.doctor.bean.LogonBean;
import com.blue.doctor.constant.Constant;
import com.blue.doctor.http.HttpApi;
import com.blue.doctor.util.DiagUtil;
import com.blue.doctor.util.GsonUtil;
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

public class LogonActivity extends BaseActivity implements HttpApi.IInterface {
    private static final String TAG = LogonActivity.class.getSimpleName();
    @Bind(R.id.phone)
    EditText phoneEditText;
    @Bind(R.id.password)
    EditText passwordEditText;
    @Bind(R.id.forgetpassword)
    TextView forgetTextView;
    @Bind(R.id.logon_button)
    Button logonButton;
    @Bind(R.id.registe_button)
    Button regidterButton;
    @Bind(R.id.title_image)
    ImageView title_imageView;
    @Bind(R.id.title)
    TextView title_textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logon);

        ButterKnife.bind(this);
        title_imageView.setVisibility(View.VISIBLE);
        title_textView.setText(getString(R.string.logon));
    }

    @OnClick(R.id.forgetpassword)
    public void getPassword() {
        Intent intent=new Intent(this,ModifyPwdActivity.class);
        startActivity(intent);

    }

    @OnClick(R.id.title_image)
    public void back2Main() {
        finish();
    }

    @OnClick(R.id.logon_button)
    public void logon() {
        String phone = phoneEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        verify(phone, password);

    }

    @OnClick(R.id.registe_button)
    public void register() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void verify(String phone, String password) {
        Map<String, String> map = new HashMap<>();
        /*phone = "18606711725";
        password = "111121";*/
        if (!StringUtils.isMobileNO(phone)) {
            DiagUtil.showCommonDialog(this, getString(R.string.phoneError));
        } else if (StringUtils.isEmpty(password)) {
            DiagUtil.showCommonDialog(this, getString(R.string.passwordError));
        } else {
            if (!TDevice.hasInternet()) {
                DiagUtil.showCommonDialog(this, getString(R.string.internetError));
            } else {
                map.put("userName", phone);
                map.put("pwd", password);
                DiagUtil.showWaitDialog(this, getString(R.string.logonnow));
                HttpApi.post(this, map, "Users/login");

            }
        }
    }

    private LogonBean parseString2Bean(String result, Class className) {
        LogonBean logonBean;
        logonBean = (LogonBean) GsonUtil.getObjectFromJson(result, className);
        if (logonBean == null) {
            return null;
        }
        return logonBean;
    }

    private void setLogonResult(LogonBean bean) {
        DiagUtil.dismissWaitDialog();
        int status = bean.getStatus();
        Log.i(TAG, status + "");
        if (status == Constant.LOGON_SUCCESS) {

        } else if (status == Constant.LOGON_FALSE) {
            DiagUtil.showCommonDialog(this, bean.getFriendlyMessage());
        }
    }

    @Override
    public void callBack(String result) {
        LogonBean bean = parseString2Bean(result, LogonBean.class);
        setLogonResult(bean);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
