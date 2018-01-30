package com.demo.verify;

import android.app.Application;
import android.content.Context;

/**
 * @Author: ponos.peng
 * Time: 2018/1/29  15:25
 * Description:
 */

public class MainApplication extends Application {
    public static Context appContext = null;
    public static String BaseUrl="";
    public static String BaseInterface="";
    public static String completeUrl=BaseUrl+BaseInterface;

    @Override
    public void onCreate() {
        super.onCreate();

        appContext = getApplicationContext();
    }

    public static Context getContext() {
        return appContext;
    }
}
