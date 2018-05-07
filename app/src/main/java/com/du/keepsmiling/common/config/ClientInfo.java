package com.du.keepsmiling.common.config;

import android.content.Context;
import android.content.pm.ApplicationInfo;

import com.du.keepsmiling.BuildConfig;

public class ClientInfo {
    boolean IS_LOG = BuildConfig.SHOW_LOG;
    public static boolean APP_DUG = false; // 是否是debug模式

    private static final ClientInfo clientInfo = new ClientInfo();

    private ClientInfo() {
    }


    /**
     * 但是当我们没在AndroidManifest.xml中设置其debug属性时:
     * 使用Eclipse运行这种方式打包时其debug属性为true,使用Eclipse导出这种方式打包时其debug属性为法false.
     * 在使用ant打包时，其值就取决于ant的打包参数是release还是debug.
     * 因此在AndroidMainifest.xml中最好不设置android:debuggable属性置，而是由打包方式来决定其值.
     *
     * @param context
     * @return
     * @author SHANHY
     * @date 2015-8-7
     */
    public static boolean isApkDebugAble(Context context) {
        boolean isDebug = true;
        try {
            isDebug = context.getApplicationInfo() != null &&
                    (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {

        }
        return isDebug;
    }

    public boolean getDebug() {
        return false;
    }


    public static ClientInfo getInstance() {
        return clientInfo;
    }

}
