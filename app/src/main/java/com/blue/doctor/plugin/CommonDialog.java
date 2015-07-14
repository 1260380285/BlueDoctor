package com.blue.doctor.plugin;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.blue.doctor.R;

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
public class CommonDialog extends Dialog {
    @Bind(R.id.dialog_text)
    TextView _messageTv;
    @Bind(R.id.dialog_btn)
    Button btn;

    public CommonDialog(Context context) {
        super(context);
        init(context);
    }

    public CommonDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    public CommonDialog(Context context, int theme) {
        super(context, theme);
        init(context);
    }

    private void init(Context context) {
        setCancelable(false);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = LayoutInflater.from(context).inflate(R.layout.common_dialog, null);
        ButterKnife.bind(this,view);
       // _messageTv = (TextView) view.findViewById(R.id.dialog_text);
        setContentView(view);
    }

    public TextView get_messageTv() {
        return _messageTv;
    }

    public void set_messageTv(String message) {
        this._messageTv.setText(message);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.dismiss();
    }
    @OnClick(R.id.dialog_btn)
    public void dismissCommonsDialog(){
        this.dismiss();
    }
}
