package com.du.keepsmiling.iconfont;

import java.io.Serializable;

/**
 * Created by dushiguang on 16/12/8.
 */
public interface Icon extends Serializable {
    TypefaceManager.IconicTypeface getIconicTypeface();

    int getIconUtfValue();
}
