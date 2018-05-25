package com.du.keepsmiling.fragment.tabhome.images;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.du.keepsmiling.R;
import com.du.keepsmiling.base.BaseFragment;
import com.du.keepsmiling.bean.JokesRecycleBean;

import app.demo.widget.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import app.demo.widget.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ImagesFragment extends BaseFragment implements ImagesContract.View {
    @BindView(R.id.layout_refresh)
    TwinklingRefreshLayout mLayoutRefresh;
    @BindView(R.id.view_recycler)
    RecyclerView viewRecycler;

    private static String ARG_IMAGE_RESOURCE = "ARG_IMAGE_RESOURCE";
    private Unbinder mUnbinder;

    private ImagesPresenter mPresenter;
    private ImagesRecycleAdapter mAdapter;

    private int mPageIndex = 1;

    public static ImagesFragment
    newInstance(int imageRes) {
        ImagesFragment imageFragment = new ImagesFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_IMAGE_RESOURCE, imageRes);
        imageFragment.setArguments(bundle);
        return imageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_jokes, container, false);
        mUnbinder = ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAdapter = new ImagesRecycleAdapter(this.getContext(), getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        viewRecycler.setLayoutManager(layoutManager);
        viewRecycler.setAdapter(mAdapter);
        mLayoutRefresh.setOnRefreshListener(listener);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new ImagesPresenter(this);
        mPresenter.reqData(mPageIndex, "");
    }

    @Override
    public void rtnData(JokesRecycleBean bean) {
        mAdapter.addData(bean.getPagebean().getContentlist(), 0);
        mAdapter.notifyDataSetChanged();
        mLayoutRefresh.finishLoadmore();
        mLayoutRefresh.finishRefreshing();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    //==============================================================================================

    RefreshListenerAdapter listener = new RefreshListenerAdapter() {

        @Override
        public void onRefresh(TwinklingRefreshLayout refreshLayout) {
            super.onRefresh(refreshLayout);
            mPageIndex = 1;
            mAdapter.cleanData();
            mPresenter.reqData(mPageIndex, "");
        }

        @Override
        public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
            super.onLoadMore(refreshLayout);
            mPresenter.reqData(++mPageIndex, "");
        }
    };
}