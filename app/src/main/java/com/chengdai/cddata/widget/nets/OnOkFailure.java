package com.chengdai.cddata.widget.nets;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import org.greenrobot.eventbus.EventBus;

/**
 * 用于处理服务器 错误码
 * Created by Administrator on 2016-09-06.
 */
public class OnOkFailure {

    public static void StartDoFailure(final Context context, String errorMessage) {

/*
        SPUtilHelpr.logOutClear();


        EventBusModel eventBusMode2=new EventBusModel();//结束主页
        eventBusMode2.setTag("MainActivityFinish");
        EventBus.getDefault().post(eventBusMode2);


        EventBusModel eventBusModel=new EventBusModel();
        eventBusModel.setTag("AllFINISH");
        EventBus.getDefault().post(eventBusModel); //结束掉所有界面

        EventBusModel eventBusModel2=new EventBusModel();
        eventBusModel2.setTag("LOGINSTATEREFHSH");
        eventBusModel2.setEvBoolean(false);
        EventBus.getDefault().post(eventBusModel2); //刷新未登录数据

        if (context == null) {
            Intent intent = new Intent(BaseApplication.getInstance(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("isStartMain", true);
            BaseApplication.getInstance().startActivity(intent);
            ToastUtil.show(BaseApplication.getInstance(),errorMessage);
            return;
        }

        try{
            if(context instanceof  Activity){
            }

        }catch (Exception e){

        }*/
    }

}
