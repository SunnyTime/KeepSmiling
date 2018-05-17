package com.du.keepsmiling.manger;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Looper;

import com.du.keepsmiling.utils.FileUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * ClassName: ImageDownManger
 * Function: 图片下载
 * date: 2018/5/17.
 * author dushiguang
 * version 0.1
 * Copyright (c) 2017, www.leadfund.com.cn All Rights Reserved.
 * 上海利得金融科技集团版权所有.
 */
public class ImageDownManger {

    public void saveHttpBitmap(final Map<String, String> map, String time) {
        //TODO 检查网络

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                String filePath = "";//下载的路径
                //创建临时下载文件夹 先删除再重新创建
                File file = new File(filePath + "TMP");
                file.delete();
                file.mkdirs();

                for (String name : map.keySet()) {
                    String url = map.get(name);

                    String downPath = filePath + "TMP" + File.separator + name;
                    try {
                        DownFileMethod(url, downPath);
                    } catch (Exception e) {
                        return;
                    }
                }

                FileUtil.deleteFile(filePath);
                file.renameTo(new File(filePath));
                Looper.loop();
            }
        }).start();

    }

    private void DownFileMethod(String url, String downPath) throws Exception {
        URL fileURL = new URL(url);
        Bitmap bitmap;

        HttpURLConnection connection = (HttpURLConnection) fileURL.openConnection();
        // 设置超时时间为6000毫秒，conn.setConnectionTiem(0);表示没有时间限制
        connection.setConnectTimeout(60000);
        // 连接设置获得数据流
        connection.setDoInput(true);
        // 不使用缓存
        connection.setUseCaches(true);

        InputStream is = connection.getInputStream();
        //解析图片
        bitmap = BitmapFactory.decodeStream(is);

        //关闭数据
        is.close();
        connection.disconnect();

        File file = new File(downPath);
        FileOutputStream fos = new FileOutputStream(file);
        if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)) {
            fos.flush();
            fos.close();
        }

        bitmap.recycle();
        System.gc();
    }
}
