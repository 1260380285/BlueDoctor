package com.blue.doctor.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blue.doctor.ConfirmAcitivity;
import com.blue.doctor.R;
import com.blue.doctor.http.HttpApi;
import com.blue.doctor.util.DiagUtil;
import com.blue.doctor.util.StringUtils;
import com.blue.doctor.util.TDevice;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * @author yuanbing
 * @ClassName: BlueDoctor
 * @Description: (这里用一句话描述这个类的作用)
 * @email 1260380285@qq.com
 * @date 2015/7/13
 */
public abstract class BaseActivity extends Activity {
    private static final String TAG=BaseActivity.class.getSimpleName();
    private String type;
    @Nullable
    @Bind(R.id.verify_code)
    EditText verifyCode;
    @Nullable
    @Bind(R.id.phone)
    EditText phone;
    String phone_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    //userType=1 表示是患者端 app,   verType=1 表示发注册验证码，  userPhone 就是用户手机号
    @Nullable
    @OnClick(R.id.verify_button)
    public void getVerifyCode() {
        phone_text = phone.getText().toString().trim();
        phone_text = "18606711725";//this is for test
        if (StringUtils.isEmpty(phone_text)) {
            DiagUtil.showCommonDialog(this, getString(R.string.putPhone));
        } else if (!StringUtils.isMobileNO(phone_text)) {
            DiagUtil.showCommonDialog(this, getString(R.string.phoneError));
        } else {
            if (!TDevice.hasInternet()) {
                DiagUtil.showCommonDialog(this, getString(R.string.internetError));
            } else {
                Map<String, String> map = new HashMap<>();
                map.put("userType", "1");
                map.put("verType", type);
                map.put("userPhone", phone_text);
                HttpApi.get(this, map, "common?");
                DiagUtil.showWaitDialog(this,getString(R.string.getMessage));
            }

        }

    }
    @Nullable
    @OnClick(R.id.register_button)
    public void register() {
        Intent intent=new Intent(this,ConfirmAcitivity.class);
        intent.putExtra("phone",phone_text);
        intent.putExtra("verCode",verifyCode.getText().toString().trim());
        startActivity(intent);
    }
    @Nullable
    @OnClick(R.id.title_image)
    public void back2Logon() {
        finish();
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
