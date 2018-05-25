package com.du.keepsmiling.net;

import com.du.logger.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * ClassName: annerViewLayout
 * Function: Banner
 * date: 2018/5/24.
 * author dushiguang
 * version 0.1
 * Copyright (c) 2017, www.leadfund.com.cn All Rights Reserved.
 * 上海利得金融科技集团版权所有.
 */
public class Request {
    public void request() {
        Retrofit retrofit = MyRetrofit.getRetrofit();

        // 创建 网络请求接口 的实例
        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

        //对 发送请求 进行封装
        Call<BaseRespBean> call = request.getCall();

        //发送网络请求(异步)
        call.enqueue(new Callback<BaseRespBean>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<BaseRespBean> call, Response<BaseRespBean> response) {
                //请求处理,输出结果
                Logger.t("dushiguang").i("body==="+ response.body().toString());
                response.body().toString();
            }

            //请求失败时候的回调
            @Override
            public void onFailure(Call<BaseRespBean> call, Throwable throwable) {
                Logger.t("dushiguang").i("ERROR" + throwable.toString());
                System.out.println("连接失败");
            }
        });

        // 发送网络请求（同步）
       /* try {
            Response<BaseRespBean> response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
