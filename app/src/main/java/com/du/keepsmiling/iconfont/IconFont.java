package com.du.keepsmiling.iconfont;

import com.du.keepsmiling.R;

/**
 * Created by dushiguang on 16/12/8.
 */
public enum IconFont implements Icon {
    IC_MENU_MORE(0xe6a7), IC_BACK(0xe697), IC_ACCOUNT_ICON(0xe6b8), IC_PASSWORD_ICON(0xe82b),
    IC_PHONE_ICON(0xe72a), IC_SETTING_ICON(0xe6ae);

    IconFont(int i) {
        mIconUtfValue = i;
    }

    private final int mIconUtfValue;
    @Override
    public TypefaceManager.IconicTypeface getIconicTypeface() {
        return TypefaceManager.IconicTypeface.getInstance(R.raw.keepsmile);
    }

    @Override
    public int getIconUtfValue() {
        return mIconUtfValue;
    }
}
