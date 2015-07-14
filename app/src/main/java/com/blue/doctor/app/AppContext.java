package com.blue.doctor.app;

import android.app.Application;

import com.blue.doctor.base.BaseApplication;
import com.blue.doctor.cache.CacheManager;

/**
 * @author yuanbing
 * @ClassName: BlueDoctor
 * @Description: (这里用一句话描述这个类的作用)
 * @email 1260380285@qq.com
 * @date 2015/7/11
 */
public class AppContext extends BaseApplication {
    private static AppContext instance;
    @Override

    public void onCreate()
    {
        super.onCreate();
        instance = this;
        CacheManager.getInstance().initCache(this);
    }
    /**
     * 获得当前app运行的AppContext
     *
     * @return
     */
    public static AppContext getInstance() {
        return instance;
    }
}
