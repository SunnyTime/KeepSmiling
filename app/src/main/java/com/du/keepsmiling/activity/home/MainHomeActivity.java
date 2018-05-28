package com.du.keepsmiling.activity.home;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;

import com.du.keepsmiling.R;
import com.du.keepsmiling.base.BaseActivity;
import com.du.keepsmiling.bean.RespBean1;
import com.du.logger.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import app.demo.widget.adaptablebottomnavigation.view.AdaptableBottomNavigationView;
import app.demo.widget.adaptablebottomnavigation.view.ViewSwapper;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import keepsmiling.du.com.rxnet.request.GetRequest;
import keepsmiling.du.com.rxnet.HttpWorker;
import keepsmiling.du.com.rxnet.response.RCallBack;

/**
 * ClassName: annerViewLayout
 * Function: Banner
 * date: 2018/5/4.
 * author dushiguang
 * version 0.1
 * Copyright (c) 2017, www.leadfund.com.cn All Rights Reserved.
 * 上海利得金融科技集团版权所有.
 */
public class MainHomeActivity extends BaseActivity {
    @BindView(R.id.view_swapper)
    ViewSwapper viewSwapper;
    @BindView(R.id.view_bottom_navigation)
    AdaptableBottomNavigationView bottomNavigationView;

    private ViewSwapperAdapter viewSwapperAdapter;

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mian_home);
        mUnbinder = ButterKnife.bind(this);

        viewSwapperAdapter = new ViewSwapperAdapter(getSupportFragmentManager());
        viewSwapper.setAdapter(viewSwapperAdapter);
        bottomNavigationView.setupWithViewSwapper(viewSwapper);

        request();
        //request1();
    }

    private void request1() {
        //开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("http://10.1.30.27:8080/TomcatTest/HelloForm");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    //下面对获取到的输入流进行读取
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null){
                        response.append(line);
                    }
                    Logger.e("-----" + response.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if (reader != null){
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    private void request() {
        GetRequest r = new GetRequest("HelloForm");
        r.request(new RCallBack<RespBean1>() {
            @Override
            public void onSuccess(RespBean1 data) {
                Logger.i("String ======" + data.getFixInvestPartUrl());
            }

            @Override
            public void onFail(int errCode, String errMsg) {
                Logger.e(errMsg);
            }
        });

        Map<String, String> map = new ArrayMap<>();
        map.put("name","name");
        map.put("password","111111");
        HttpWorker.GET("HelloForm")
                .addParams(map)
                .request(new RCallBack<RespBean1>() {
                    @Override
                    public void onSuccess(RespBean1 data) {
                        Logger.i("String ======" + data.getFixInvestPartUrl());
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        Logger.e(errMsg);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
