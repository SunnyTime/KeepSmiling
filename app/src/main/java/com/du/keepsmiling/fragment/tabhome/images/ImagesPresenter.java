package com.du.keepsmiling.fragment.tabhome.images;

import com.du.keepsmiling.base.BaseBean;
import com.du.keepsmiling.base.BasePresenter;
import com.du.keepsmiling.bean.JokesRecycleBean;
import com.du.keepsmiling.utils.GsonUtil;
import com.du.logger.Logger;
import com.show.api.ShowApiRequest;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * ClassName: annerViewLayout
 * Function: Banner
 * date: 2018/5/7.
 * author dushiguang
 * version 0.1
 * Copyright (c) 2017, www.leadfund.com.cn All Rights Reserved.
 * 上海利得金融科技集团版权所有.
 */
public class ImagesPresenter extends BasePresenter implements ImagesContract.Presenter {
    private String TAG = "JokesPresenter";

    private ImagesContract.View view;

    public ImagesPresenter(ImagesContract.View view) {
        this.view = view;
    }

    @Override
    public void reqData(final int pageIndex, final String keyWords) {
        //创建一个被观察者(发布者)
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                String res = new ShowApiRequest("http://route.showapi.com/255-1", appid, secret)
                        .addTextPara("type", "10")
                        .addTextPara("title", keyWords)
                        .addTextPara("page", String.valueOf(pageIndex))
                        .post();
                e.onNext(res);
                e.onComplete();
            }
        });

        //创建一个观察者
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onError(Throwable e) {
                Logger.t(TAG).e("ERROR.. :" + e.toString());
            }

            @Override
            public void onComplete() {
                Logger.t(TAG).e("onCompleted..:");
            }

            @Override
            public void onNext(String str) {
                BaseBean bean = GsonUtil.fromJson(str, BaseBean.class);
                if ("0".equals(bean.getShowapi_res_code())) {
                    String data = GsonUtil.fromJsonString(str, "showapi_res_body");
                    view.rtnData(GsonUtil.fromJson(data, JokesRecycleBean.class));
                }
                Logger.t(TAG).i("onNext.. integer:" + str);
                Logger.t(TAG).json(str);
            }
        };

        //注册后就会开始调用call()中的观察者执行的方法 onNext() onCompleted()等
        //设置观察者和发布者代码所要运行的线程后注册观察者
        observable.subscribeOn(Schedulers.newThread())//在当前线程执行subscribe()方法
                .observeOn(AndroidSchedulers.mainThread())//在UI线程执行观察者的方法
                .subscribe(observer);
    }
}
