package com.du.keepsmiling.activity.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.du.keepsmiling.R;
import com.du.keepsmiling.fragment.tabhome.images.ImagesFragment;
import com.du.keepsmiling.fragment.tabhome.jokes.JokesFragment;
import com.du.keepsmiling.fragment.tabhome.videos.VideosFragment;

import app.demo.widget.adaptablebottomnavigation.adapter.FragmentStateAdapter;

public class ViewSwapperAdapter extends FragmentStateAdapter {

    private static final int INDEX_BUFFER = 0;
    private static final int INDEX_RETREAT = 1;
    private static final int INDEX_VALUES = 2;

    private JokesFragment mJokesFragment;
    private VideosFragment mVideosFragment;
    private ImagesFragment mImagesFragment;

    public ViewSwapperAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case INDEX_BUFFER:
                if (mJokesFragment == null) {
                    mJokesFragment = JokesFragment.newInstance(R.mipmap.icon_my_center);
                }
                return mJokesFragment;
            case INDEX_RETREAT:
                if (mVideosFragment == null) {
                    mVideosFragment = VideosFragment.newInstance(R.mipmap.icon_video);
                }
                return mVideosFragment;
            case INDEX_VALUES:
                if (mImagesFragment == null) {
                    mImagesFragment = ImagesFragment.newInstance(R.mipmap.icon_jokes);
                }
                return mImagesFragment;
        }
        return mJokesFragment = JokesFragment.newInstance(R.mipmap.icon_my_center);
    }

    @Override
    public int getCount() {
        return 3;
    }

}
