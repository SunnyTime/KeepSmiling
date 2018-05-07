package com.du.keepsmiling.activity.home;

import android.os.Bundle;

import com.du.keepsmiling.R;
import com.du.keepsmiling.base.BaseActivity;

import app.demo.widget.adaptablebottomnavigation.view.AdaptableBottomNavigationView;
import app.demo.widget.adaptablebottomnavigation.view.ViewSwapper;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
