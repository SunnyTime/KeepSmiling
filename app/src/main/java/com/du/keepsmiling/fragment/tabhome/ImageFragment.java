package com.du.keepsmiling.fragment.tabhome;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.du.keepsmiling.R;

import app.demo.widget.adaptablebottomnavigation.view.ViewSwapper;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ImageFragment extends Fragment {
    @BindView(R.id.layout_refresh)
    ViewSwapper viewSwapper;

    private static String ARG_IMAGE_RESOURCE = "ARG_IMAGE_RESOURCE";
    private int imageRes;
    private Unbinder mUnbinder;

    public static ImageFragment newInstance(int imageRes) {
        ImageFragment imageFragment = new ImageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_IMAGE_RESOURCE, imageRes);
        imageFragment.setArguments(bundle);
        return imageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageRes = getArguments().getInt(ARG_IMAGE_RESOURCE);
        mUnbinder = ButterKnife.bind(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_image, container, false);

        ImageView iconImage = (ImageView) fragmentView.findViewById(R.id.image_icon);
        iconImage.setImageResource(imageRes);

        return fragmentView;
    }
}