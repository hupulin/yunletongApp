package com.fskj.applibrary;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.coder.zzq.smartshow.core.SmartShow;
import com.fskj.applibrary.api.ApiClient;
import com.fskj.applibrary.util.FileUtil;
import com.miot.android.BindService;


/**
 * Created by xzz on 2018/8/14.
 **/

public class MainApp extends Application {
    public static Context appContext;
    public static MainApp instance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
//        MultiDex.install(this);

        ApiClient.getInstance().init(this);
        BindService.getInstance(this).startBind(2,this.getPackageName());
        instance=this;
        SmartShow.init(this);



    }

    public static MainApp getInstance() {
        if (instance == null) {
            try {
                throw new Exception("MiotApplication is null ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static String getCacheImagePath() {
        return FileUtil.getSdCardPath();
    }


}
    