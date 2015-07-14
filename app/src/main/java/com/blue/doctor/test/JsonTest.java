package com.blue.doctor.test;

import android.util.Log;


import com.blue.doctor.bean.LogonBean;
import com.blue.doctor.util.GsonUtil;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * @author yuanbing
 * @ClassName: BlueDoctor
 * @Description: (这里用一句话描述这个类的作用)
 * @email 1260380285@qq.com
 * @date 2015/7/12
 */
public class JsonTest {
   /* private int ID;
    private String username;
    private String pwd;
    private String nickname;
    private String realname;
    private int acceptNews;
    private String profilePath;
    private String createTime;
    private String updateTime;*/
    private static final String TAG=JsonTest.class.getSimpleName();
    public static String json="{\"status\":0,\"data\":{\"ID\":10036,\"username\":\"18606711725\",\"pwd\":\"9ade105c8aa1b22817d72a57651dd5d4\",\"nickname\":\"111111\",\"realname\":null,\"acceptNews\":0,\"profilePath\":null,\"createTime\":null,\"updateTime\":null},\"message\":\"ok\",\"friendlyMessage\":\"\",\"requestId\":\"\",\"token\":{\"ID\":10560,\"token\":\"20150712175622106674\",\"userType\":1,\"userName\":\"18606711725\",\"startTime\":1436694982000,\"endTime\":1436781382000,\"createTime\":1436694982000,\"disableFlag\":0}}";
    public static  String data="{\"ID\":1,\"username\":\"allen\",\"pwd\":\"ww\",\"nickname\":\"yuanbing\",\"realname\":\"yb\",\"acceptNews\":1,\"profilePath\":\"http\",\"createTime\":\"123\",\"updateTime\":\"124\"}";
    public static String text="{\"id\":1,\"name\":\"allen\"}";
    public static String text2="{\"name\":\"allen\"}";
    public static  void getinst(){
        Gson gson=new Gson();
        Text bean= null;
        try {
            bean = gson.fromJson(text,Text.class);
            Log.i(TAG,bean.getName());
            Text bean1 = gson.fromJson(text2,Text.class);
            String s=bean1.getName();
            Log.i(TAG,s+"");
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        //LogonBean bean=GsonUtil.getObjectFromJson(json, LogonBean.class);
        String data=bean.getName();
        Log.i(TAG,data+"");

    }
}
