package com.blue.doctor.util;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.blue.doctor.R;
import com.blue.doctor.bean.LogonBean;
import com.blue.doctor.plugin.CommonDialog;
import com.blue.doctor.plugin.WaitDialog;

/**
 * @author yuanbing
 * @ClassName: BlueDoctor
 * @Description: (这里用一句话描述这个类的作用)
 * @email 1260380285@qq.com
 * @date 2015/7/12
 */
public class DiagUtil {
    private static final String TAG=DiagUtil.class.getSimpleName();
    private static WaitDialog waitDialog;
    private static CommonDialog commonDialog;

    public static WaitDialog getWaitDialog(Activity activity, String message) {
        WaitDialog dialog = null;
        try {
            dialog = new WaitDialog(activity, R.style.dialog_waiting);
            dialog.setMessage(message);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dialog;
    }

    public static void showWaitDialog(Activity activity, String message) {
        if(!activity.isFinishing()){
            if (waitDialog == null||!activity.equals(waitDialog.getOwnerActivity())) {
                waitDialog = getWaitDialog(activity, message);
           }
            waitDialog.show();
        }


    }

    public static void dismissWaitDialog() {
        if (waitDialog != null) {
            waitDialog.dismiss();
        }

    }

    public static void showCommonDialog(Activity activity, String message) {
        if (commonDialog == null) {
            Log.i(TAG,message);
            commonDialog = new CommonDialog(activity, R.style.dialog_common);
        }
        commonDialog.set_messageTv(message);
        commonDialog.show();

    }

}
