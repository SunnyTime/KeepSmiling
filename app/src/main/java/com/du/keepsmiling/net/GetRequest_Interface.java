package com.du.keepsmiling.net;

import retrofit2.Call;
import retrofit2.http.HTTP;

/**
 * ClassName: annerViewLayout
 * Function: Banner
 * date: 2018/5/24.
 * author dushiguang
 * version 0.1
 * Copyright (c) 2017, www.leadfund.com.cn All Rights Reserved.
 * 上海利得金融科技集团版权所有.
 */
public interface GetRequest_Interface {
    @HTTP(method = "GET", path = "HelloForm")
    Call<BaseRespBean> getCall();
    // @GET注解的作用:采用Get方法发送网络请求

    // getCall() = 接收网络请求数据的方法
    // 其中返回类型为Call<*>，*是接收数据的类（即上面定义的Translation类）
    // 如果想直接获得Responsebody中的内容，可以定义网络请求返回值为Call<ResponseBody>
}
