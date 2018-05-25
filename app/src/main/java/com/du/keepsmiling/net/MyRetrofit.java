package com.du.keepsmiling.net;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * ClassName: annerViewLayout
 * Function: Banner
 * date: 2018/5/24.
 * author dushiguang
 * version 0.1
 * Copyright (c) 2017, www.leadfund.com.cn All Rights Reserved.
 * 上海利得金融科技集团版权所有.
 */
public class MyRetrofit {
    private static Retrofit retrofit;

    private MyRetrofit() {

    }

    public static Retrofit getRetrofit() {
        if (null == retrofit) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.1.30.27:8080/TomcatTest/") // 设置网络请求的Url地址
                    .addConverterFactory(GsonConverterFactory.create()) // 设置数据解析器
                    .build();
        }
        return retrofit;
    }
}
