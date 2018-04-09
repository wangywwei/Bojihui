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


//        File file = getFilesDir();
//        Log.w("DemoApplication", "load:" + file.getAbsolutePath());
//        TXLiveBase.setLibraryPath(file.getAbsolutePath());
        //测试代码
//        TCHttpEngine.getInstance().initContext(getApplicationContext());
//        mRefWatcher = LeakCanary.install(this);
    }

//    public static RefWatcher getRefWatcher(Context context) {
//        DemoApplication application = (DemoApplication) context.getApplicationContext();
//        return application.mRefWatcher;
//    }

    public static MyApp getApplication() {
        return instance;
    }
}
