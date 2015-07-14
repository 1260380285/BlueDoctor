package com.blue.doctor.base;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

/**
 * @author yuanbing
 * @ClassName: BlueDoctor
 * @Description: (这里用一句话描述这个类的作用)
 * @email 1260380285@qq.com
 * @date 2015/7/11
 */
public abstract class BaseFragment extends Fragment {

    private static final  String TAG=BaseFragment.class.getSimpleName();
    protected AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
            Log.i(TAG,statusCode+";"+headers+";"+responseBody);
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            Log.i(TAG,statusCode+";"+headers+";"+responseBody);
        }
    };
}
