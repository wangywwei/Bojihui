package com.hxbj.bijihui.global;

import android.app.Application;

import com.hxbj.bijihui.base.BaseActivity;
import com.tencent.rtmp.TXLiveBase;


public class MyApp extends Application {
    public static BaseActivity mContext=null;

    public static MyApp instance;

    @Override
    public void onCreate() {

        super.onCreate();

        instance = this;

        TXLiveBase.setConsoleEnabled(true);



    }



    public static MyApp getApplication() {
        return instance;
    }
}
