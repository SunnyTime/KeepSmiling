package com.du.keepsmiling.base;

import android.app.Application;

import com.du.keepsmiling.common.config.ClientInfo;
import com.du.logger.AndroidLogAdapter;
import com.du.logger.Logger;
import com.du.logger.PrettyFormatStrategy;
import com.squareup.leakcanary.LeakCanary;

/**
 * ClassName: annerViewLayout
 * Function: Banner
 * date: 2017/11/7.
 * author dushiguang
 * version 0.1
 */
public class BaseApplication extends Application {
    private static BaseApplication instance;

    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        //初始化LeakCanary
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

        initLog();
        //initBmob();
    }

    /*public void initBmob() {
        Bmob.initialize(getApplicationContext(), keys.BmobApplicationID);
    }*/

    private void initLog() {
        PrettyFormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(1)         // (Optional) How many method line to show. Default 2
                //.methodOffset(5)        // (Optional) Hides internal method calls up to offset. Default 5 设置调用堆栈的函数偏移值，默认是 5
                //.logStrategy(ls) // (Optional) Changes the log strategy to print out. Default LogCat
                .tag("LeadBank_Log")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return ClientInfo.isApkDebugAble(getApplicationContext());
            }
        });


    }
}
