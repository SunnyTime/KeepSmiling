package com.du.keepsmiling.base;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.support.multidex.MultiDex;

import com.du.keepsmiling.common.config.ClientInfo;
import com.du.logger.AndroidLogAdapter;
import com.du.logger.Logger;
import com.du.logger.PrettyFormatStrategy;
import com.google.gson.Gson;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import keepsmiling.du.com.rxnet.HttpWorker;
import keepsmiling.du.com.rxnet.interceptor.HttpLogInterceptor;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * ClassName: annerViewLayout
 * Function: Banner
 * date: 2017/11/7.
 * author dushiguang
 * version 0.1
 */
public class BaseApplication extends Application {
    private static BaseApplication instance;
    private RefWatcher mRefWatcher;

    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //初始化LeakCanary
        mRefWatcher = ClientInfo.isApkDebugAble(getApplicationContext()) ? LeakCanary.install(this) : RefWatcher.DISABLED;
        initLog();
        //initBmob();
        initNet();
    }

    private void initNet() {
        HttpWorker.init(this);
        HttpWorker.CONFIG()
                .baseUrl("http://10.1.30.1:8080/TomcatTest/")
                .callAdapterFactory(RxJava2CallAdapterFactory.create())
                .converterFactory(GsonConverterFactory.create(new Gson()))
                .interceptor(new HttpLogInterceptor().setLevel(HttpLogInterceptor.Level.BODY));

    }

    /**
     * 分割 Dex 支持
     *
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
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

    public static RefWatcher getRefWatcher() {
        return getInstance().mRefWatcher;
    }

    /**
     * 判断当前应用是否是debug状态
     */

    public static boolean isApkInDebug(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }
}
