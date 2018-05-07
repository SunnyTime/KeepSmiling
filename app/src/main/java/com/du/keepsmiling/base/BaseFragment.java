package com.du.keepsmiling.base;

import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.du.keepsmiling.common.key.keys;

import cn.bmob.v3.Bmob;

/**
 * ClassName: annerViewLayout
 * Function: Banner
 * date: 2017/11/7.
 * author dushiguang
 * version 0.1
 */
public class BaseFragment extends Fragment {
    public void showToast(String str) {
        Toast.makeText(getActivity().getApplicationContext(),str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        Bmob.initialize(getActivity().getApplicationContext(), keys.BmobApplicationID);
    }
}
