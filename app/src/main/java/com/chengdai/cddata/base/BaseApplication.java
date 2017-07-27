package com.chengdai.cddata.base;

import android.app.Application;


/**
 * 基础Application
 * Created by Administrator on 2016/8/29.
 */
public class BaseApplication extends Application {

    private static BaseApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
//        EventBus.builder().throwSubscriberException(BuildConfig.IS_DEBUG).installDefaultEventBus();
        application=this;
    }

    public static BaseApplication getInstance(){
        return application;
    }
}

