package com.du.keepsmiling.fragment.tabhome.jokes;

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

import app.demo.widget.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class JokesFragment extends BaseFragment implements JokesContract.View{
    @BindView(R.id.layout_refresh)
    TwinklingRefreshLayout mLayoutRefresh;
    @BindView(R.id.view_recycler)
    RecyclerView viewRecycler;

    private static String ARG_IMAGE_RESOURCE = "ARG_IMAGE_RESOURCE";
    private Unbinder mUnbinder;

    private JokesPresenter mPresenter;
    private JokesRecycleAdapter mAdapter;

    public static JokesFragment newInstance(int imageRes) {
        JokesFragment imageFragment = new JokesFragment();
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

        mPresenter = new JokesPresenter(this);
        mPresenter.reqData();
        mAdapter = new JokesRecycleAdapter(this.getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        viewRecycler.setLayoutManager(layoutManager);
        viewRecycler.setAdapter(mAdapter);
    }

    @Override
    public void rtnData(JokesRecycleBean bean) {
        mAdapter.addData(bean.getPagebean().getContentlist(), 0);
        mAdapter.notifyDataSetChanged();
    }

    //==============================================================================================
}