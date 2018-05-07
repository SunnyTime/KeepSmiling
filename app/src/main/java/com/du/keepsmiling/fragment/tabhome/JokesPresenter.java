package com.du.keepsmiling.fragment.tabhome;

import com.du.keepsmiling.base.BasePresenter;
import com.du.logger.Logger;
import com.show.api.ShowApiRequest;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * ClassName: annerViewLayout
 * Function: Banner
 * date: 2018/5/7.
 * author dushiguang
 * version 0.1
 * Copyright (c) 2017, www.leadfund.com.cn All Rights Reserved.
 * 上海利得金融科技集团版权所有.
 */
public class JokesPresenter extends BasePresenter implements JokesContract.Presenter {
    private String TAG = "JokesPresenter";

    @Override
    public void reqData() {
        //创建一个被观察者(发布者)
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                String appid="64056";//要替换成自己的
                String secret="ccb9bf54f37942b485a61e5a860f76da";//要替换成自己的
                String res=new ShowApiRequest( "http://route.showapi.com/255-1", appid, secret)
                        .addTextPara("type", "29")
                        .addTextPara("title", "")
                        .addTextPara("page", "1")
                        .post();
                subscriber.onNext(res);
                subscriber.onCompleted();
            }
        });

        //创建一个观察者
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Logger.t(TAG).e("onCompleted..:");
            }

            @Override
            public void onError(Throwable e) {
                Logger.t(TAG).e("ERROR.. :" + e.toString());
            }

            @Override
            public void onNext(String str) {
                Logger.t(TAG).e( "onNext.. integer:" + str);
            }
        };

        //注册观察者(这个方法名看起来有点怪，还不如写成regisiterSubscriber(..)或者干脆addSubscriber(..))
        //注册后就会开始调用call()中的观察者执行的方法 onNext() onCompleted()等
        //设置观察者和发布者代码所要运行的线程后注册观察者
        observable.subscribeOn(Schedulers.newThread())//在当前线程执行subscribe()方法
                .observeOn(AndroidSchedulers.mainThread())//在UI线程执行观察者的方法
                .subscribe(subscriber);
    }
}
