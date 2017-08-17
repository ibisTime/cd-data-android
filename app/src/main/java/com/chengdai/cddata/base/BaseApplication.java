package com.chengdai.cddata.base;

import android.app.Application;

import com.chengdai.cddata.widget.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;


/**
 * 基础Application
 * Created by Administrator on 2016/8/29.
 */
public class BaseApplication extends Application {

    private static BaseApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.builder().throwSubscriberException(LogUtil.isLog).installDefaultEventBus();
        application=this;
    }

    public static BaseApplication getInstance(){
        return application;
    }
}

