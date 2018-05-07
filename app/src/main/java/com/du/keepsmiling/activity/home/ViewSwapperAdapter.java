package com.du.keepsmiling.activity.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.du.keepsmiling.R;
import com.du.keepsmiling.fragment.tabhome.ImageFragment;
import com.du.keepsmiling.fragment.tabhome.JokesFragment;

import app.demo.widget.adaptablebottomnavigation.adapter.FragmentStateAdapter;

public class ViewSwapperAdapter extends FragmentStateAdapter {

    private static final int INDEX_BUFFER = 0;
    private static final int INDEX_RETREAT = 1;
    private static final int INDEX_VALUES = 2;

    public ViewSwapperAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case INDEX_BUFFER:
                return JokesFragment.newInstance(R.mipmap.icon_my_center);
            case INDEX_RETREAT:
                return ImageFragment.newInstance(R.mipmap.icon_video);
            case INDEX_VALUES:
                return ImageFragment.newInstance(R.mipmap.icon_jokes);
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

}
