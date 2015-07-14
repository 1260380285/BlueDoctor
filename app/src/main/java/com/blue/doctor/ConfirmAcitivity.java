package com.blue.doctor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
 * @date 2015/7/13
 */
public class ConfirmAcitivity extends Activity implements HttpApi.IInterface {
    private String phoneNumber;
    private String verCode;
    @Bind(R.id.title_image)
    ImageView titleImage;
    @Bind(R.id.title)
    TextView titleTextView;
    @Bind(R.id.confirm_button)
    Button confirmButton;
    @Bind(R.id.nickname)
    EditText nickname;
    @Bind(R.id.password)
    EditText password;
    @Bind(R.id.confirmpassword)
    EditText confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm);
        ButterKnife.bind(this);
        init();
    }

    public void init() {
        titleImage.setVisibility(View.VISIBLE);
        titleTextView.setText(getString(R.string.register));
        Intent intent = getIntent();
        phoneNumber = intent.getStringExtra("phone");
        verCode = intent.getStringExtra("verCode");

    }
    @OnClick(R.id.title_image)
    public  void back2Verify(){
        finish();
    }
    @OnClick(R.id.confirm_button)
    public void confirm() {
        String name = nickname.getText().toString().trim();
        String pwd = password.getText().toString().trim();
        String rePwd = confirmPassword.getText().toString().trim();
        verify(name, pwd, rePwd);

    }

    private void verify(String name, String pwd, String rePwd) {
        if (StringUtils.isEmpty(pwd) || StringUtils.isEmpty(rePwd)) {
            DiagUtil.showCommonDialog(this, getString(R.string.passwordError));
        } else if (!pwd.equals(rePwd)) {
            DiagUtil.showCommonDialog(this, getString(R.string.passwordNotSame));
        } else {
            if (!TDevice.hasInternet()) {
                DiagUtil.showCommonDialog(this, getString(R.string.internetError));
            } else {
                Map<String, String> map = new HashMap<>();
                map.put("id", "-1");
                map.put("nickname", name);
                map.put("userName", phoneNumber);
                map.put("pwd", pwd);
                HttpApi.getInstance().addHeader("verCode", verCode);
                HttpApi.post(this, map, "Users/register");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void callBack(String result) {

    }
}
