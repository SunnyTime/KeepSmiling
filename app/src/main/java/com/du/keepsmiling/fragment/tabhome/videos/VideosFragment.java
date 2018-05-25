package com.du.keepsmiling.fragment.tabhome.videos;

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
import com.xiao.nicevideoplayer.NiceVideoPlayer;
import com.xiao.nicevideoplayer.NiceVideoPlayerManager;

import app.demo.widget.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import app.demo.widget.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class VideosFragment extends BaseFragment implements VideosContract.View {
    @BindView(R.id.layout_refresh)
    TwinklingRefreshLayout mLayoutRefresh;
    @BindView(R.id.view_recycler)
    RecyclerView viewRecycler;

    private static String ARG_IMAGE_RESOURCE = "ARG_IMAGE_RESOURCE";

    private Unbinder mUnbinder;

    private VideosPresenter mPresenter;
    private VideosRecycleAdapter mAdapter;

    private int mPageIndex = 1;

    public static VideosFragment newInstance(int imageRes) {
        VideosFragment imageFragment = new VideosFragment();
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

        mPresenter = new VideosPresenter(this);
        mPresenter.reqData(mPageIndex, "");
        mAdapter = new VideosRecycleAdapter(this.getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        viewRecycler.setLayoutManager(layoutManager);
        viewRecycler.setAdapter(mAdapter);
        mLayoutRefresh.setOnRefreshListener(listener);

        viewRecycler.setRecyclerListener(new RecyclerView.RecyclerListener() {
            @Override
            public void onViewRecycled(RecyclerView.ViewHolder holder) {
                NiceVideoPlayer niceVideoPlayer = ((VideosRecycleHolder) holder).viewNiceVideoPlayer;
                if (niceVideoPlayer == NiceVideoPlayerManager.instance().getCurrentNiceVideoPlayer()) {
                    NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
                }
            }
        });

        viewRecycler.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {

            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                NiceVideoPlayer niceVideoPlayer = (NiceVideoPlayer) view.findViewById(R.id.view_video_player);
                if (niceVideoPlayer != null) {
                    niceVideoPlayer.release();
                }
            }
        });
    }

    @Override
    public void rtnData(JokesRecycleBean bean) {
        mAdapter.addData(bean.getPagebean().getContentlist(), 0);
        mAdapter.notifyDataSetChanged();
        mLayoutRefresh.finishLoadmore();
        mLayoutRefresh.finishRefreshing();
    }

    @Override
    public void onStop() {
        super.onStop();
        NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
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