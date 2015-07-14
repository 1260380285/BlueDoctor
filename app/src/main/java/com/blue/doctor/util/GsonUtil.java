package com.blue.doctor.util;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author yuanbing
 * @ClassName: BlueDoctor
 * @Description: (这里用一句话描述这个类的作用)
 * @email 1260380285@qq.com
 * @date 2015/7/12
 */
public class GsonUtil {
    private static final String TAG = GsonUtil.class.getSimpleName();
    private static Gson gson = null;

    public static Gson getGsonInstance() {
        if (gson == null) {
            synchronized (GsonUtil.class) {
                if (gson == null) {
                    gson = new Gson();
                }

            }
        }
        return gson;
    }

    public static <T> T getObjectFromJson(String mStr, Class<T> toJsonClass) {

        try {

            return getGsonInstance().fromJson(mStr, toJsonClass);

        } catch (Exception e) {
            Log.i(TAG, "error to json" + e);
        }

        return null;
    }
}
