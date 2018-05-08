package com.du.keepsmiling.utils;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.du.keepsmiling.base.BaseApplication;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

/**
 * 增加Picasso 管理
 */
public class PicassoUtil {

    public static void load(String url, ImageView imageView) {
        load(url, imageView,0,0,0,0 );
    }

    public static void load(int id, ImageView imageView) {
        load(id, imageView,0,0,0,0 );
    }
    /**
     * 指定宽高
     * @param id
     * @param imageView
     * @param width
     * @param height
     */
    public static void load(int id, ImageView imageView, int width, int height) {
        load(id, imageView,0,0,width,height );
    }
    /**
     * 指定宽高
     * @param url
     * @param imageView
     * @param width
     * @param height
     */
    public static void load(String url, ImageView imageView, int width, int height) {
        load(url, imageView,0,0,width,height );
    }

    /**
     * 加载图片
     * @param url 图片地址
     * @param placeholder 默认占位图
     * @param error 加载错误显示图片
     * @param imageView 显示位置
     */
    public static void load(String url, int placeholder, int error, ImageView imageView){
        if(url != null && !url.isEmpty() && imageView != null) {
            Picasso.with(BaseApplication.getInstance())
                    .load(url)
                    .placeholder(placeholder)
                    .error(error).
                    config(Bitmap.Config.RGB_565)
                    .into(imageView);
        }
    }

    /**
     * 加载图片
     * for url
     *
     * @param url         图片地址
     * @param placeholder 默认占位图
     * @param error       加载错误显示图片
     * @param imageView   显示位置
     */
    public static void load(String url, ImageView imageView, int placeholder, int error, int width, int height) {
        if (url != null && !url.isEmpty() && imageView != null) {
            RequestCreator requestCreator = Picasso.with(BaseApplication.getInstance()).load(url).config(Bitmap.Config.RGB_565);
            if (placeholder != 0) {
                requestCreator.placeholder(placeholder);
            }
            if (error != 0) {
                requestCreator.error(error);
            }
            if (width > 0 && height > 0) {
                requestCreator.resize(width, height);
            }
            requestCreator.into(imageView);
        }
    }

    /**
     * 加载图片
     * for ID
     *
     * @param id         图片地址
     * @param placeholder 默认占位图
     * @param error       加载错误显示图片
     * @param imageView   显示位置
     */
    public static void load(int id, ImageView imageView, int placeholder, int error, int width, int height) {
        if (id != 0  && imageView != null) {
            RequestCreator requestCreator = Picasso.with(BaseApplication.getInstance()).load(id).config(Bitmap.Config.RGB_565);
            if (placeholder != 0) {
                requestCreator.placeholder(placeholder);
            }else{
                requestCreator.placeholder(id);
            }
            if (error != 0) {
                requestCreator.error(error);
            }
            if (width > 0 && height > 0) {
                requestCreator.resize(width, height);
            }
            requestCreator.into(imageView);
        }
    }


}
