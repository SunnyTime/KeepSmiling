package com.du.keepsmiling.fragment.tabhome.images;

import com.du.keepsmiling.bean.JokesRecycleBean;

/**
 * ClassName: annerViewLayout
 * Function: Banner
 * date: 2018/5/7.
 * author dushiguang
 * version 0.1
 * Copyright (c) 2017, www.leadfund.com.cn All Rights Reserved.
 * 上海利得金融科技集团版权所有.
 */
public interface ImagesContract {
    interface View {
        /**
         * 下发的数据
         */
        void rtnData(JokesRecycleBean bean);
    }

    interface Presenter {
        void reqData(int pageIndex, String keyWords);
    }

}
