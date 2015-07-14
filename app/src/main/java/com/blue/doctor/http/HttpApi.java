package com.blue.doctor.http;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.blue.doctor.R;
import com.blue.doctor.constant.Constant;
import com.blue.doctor.util.DiagUtil;
import com.blue.doctor.util.StringUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.util.Map;

/**
 * @author yuanbing
 * @ClassName: BlueDoctor
 * @Description: (这里用一句话描述这个类的作用)
 * @email 1260380285@qq.com
 * @date 2015/7/11
 */
public class HttpApi {
    private static final String TAG = HttpApi.class.getSimpleName();
    private static AsyncHttpClient client;
    private static String result = null;
    private static IInterface iInterface;

    public static AsyncHttpClient getInstance() {
        if (client == null) {
            synchronized (HttpApi.class) {
                if (client == null) {
                    client = new AsyncHttpClient();

                }
            }
        }
        return client;
    }

    public static void post(Activity context, Map map, String str) {
        iInterface = (IInterface) context;
        RequestParams params = StringUtils.getPostRequestParam(map);
        getInstance().post(context, Constant.HOST + str, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                result = new String(responseBody);
                Log.i(TAG, result);
                iInterface.callBack(result);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                result = new String(responseBody);
                Log.i(TAG, result);
            }
        });
    }

    public static void get(Activity context, Map map, String str) {
        iInterface = (IInterface) context;
        String url = StringUtils.getGetRequestParam(map);
        getInstance().get(Constant.HOST+str+url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response=new String(responseBody);
                Log.i(TAG, response);
                iInterface.callBack(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.i(TAG, new String(responseBody));
            }
        });

    }


    public void setiInterface(IInterface iInterface) {
        this.iInterface = iInterface;
    }

    public interface IInterface {
        void callBack(String result);
    }
}
