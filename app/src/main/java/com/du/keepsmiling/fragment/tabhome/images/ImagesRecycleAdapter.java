package com.du.keepsmiling.fragment.tabhome.images;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.du.keepsmiling.R;
import com.du.keepsmiling.bean.JokesRecycleBean;
import com.du.keepsmiling.manger.AlxGifHelper;
import com.du.keepsmiling.utils.PicassoUtil;

import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifDrawable;

/**
 * ClassName: JokesRecycleAdapter
 * Function: JokesRecycleAdapter
 * date: 2017/12/11.
 * author dushiguang
 * version 0.1
 */
public class ImagesRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private Activity mActivity;
    private List<JokesRecycleBean.contentBeanlist> bean = new ArrayList<>();
    private int mHeaderCount = 0;//头部View个数
    private int mBottomCount = 0;//底部View个数
    private int headData;
    //item类型
    private static final int ITEM_TYPE_HEADER = 0;
    private static final int ITEM_TYPE_CONTENT = 1;
    private static final int ITEM_TYPE_BOTTOM = 2;

    public void addData(List<JokesRecycleBean.contentBeanlist> beanList, int headData) {
        bean.addAll(beanList);
        this.headData = headData;
    }

    public void cleanData() {
        bean.clear();
    }

    public ImagesRecycleAdapter(Context context, Activity activity) {
        this.mContext = context;
        this.mActivity = activity;
    }

    //判断当前item是否是HeadView
    public boolean isHeaderView(int position) {
        return mHeaderCount != 0 && position < mHeaderCount;
    }

    //内容长度
    private int getContentItemCount() {
        return bean.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_HEADER) {
            /*View view = LayoutInflater.from(context).inflate(R.layout.view_head_recycle, parent, false);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(lp);
            return new HeaderViewHolder(view);*/
        } else if (viewType == ITEM_TYPE_CONTENT) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_images_list, parent, false);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(lp);
            return new ImagesRecycleHolder(view);
        } else if (viewType == ITEM_TYPE_BOTTOM) {
            //return new BottomViewHolder(mLayoutInflater.inflate(R.layout.rv_footer, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ImagesRecycleHolder) {
            final ImagesRecycleHolder holder = (ImagesRecycleHolder) viewHolder;
            final int p = position - mHeaderCount;
            PicassoUtil.load(bean.get(p).getProfile_image(), holder.viewImage);
            holder.viewName.setText(bean.get(p).getName());
            holder.viewTime.setText(bean.get(p).getCreate_time());
            holder.viewGif.setTag(bean.get(p).getCdn_img());

            int length = bean.get(p).getCdn_img().length();
            String str = "*";
            if (length >= 3) {
                str = bean.get(p).getCdn_img().substring(length - 3, length);
            }

            if("gif".equals(str)) {
                AlxGifHelper.displayImage(bean.get(p).getCdn_img(), holder.viewGif, holder.viewProgress, 0);
                final GifDrawable gifDrawable;
                try {
                    gifDrawable = (GifDrawable) holder.viewGif.getDrawable();
                    gifDrawable.stop();
                    holder.viewGif.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            gifDrawable.start();
                        }
                    });
                } catch (Exception e) {

                }
            } else {
                PicassoUtil.load(bean.get(p).getCdn_img(), holder.viewGif);
            }

            /**
             * gifDrawable.start(); //开始播放
             gifDrawable.stop(); //停止播放
             gifDrawable.reset(); //复位，重新开始播放
             gifDrawable.isRunning(); //是否正在播放
             gifDrawable.setLoopCount( 2 ); //设置播放的次数，播放完了就自动停止
             gifDrawable.getCurrentLoop()； //获取正在播放的次数
             gifDrawable.getCurrentPosition ; //获取现在到从开始播放所经历的时间
             gifDrawable.getDuration() ; //获取播放一次所需要的时间
             */

        }
    }

    @Override
    public int getItemCount() {
        //返回 x + 1,因为多了一个headView
        return null == bean ? (mHeaderCount + mBottomCount) : bean.size() + (mHeaderCount + mBottomCount);
    }

    //判断当前item类型
    @Override
    public int getItemViewType(int position) {
        int dataItemCount = getContentItemCount();
        if (mHeaderCount != 0 && position < mHeaderCount) {
            //头部View
            return ITEM_TYPE_HEADER;
        } else if (mBottomCount != 0 && position >= (mHeaderCount + dataItemCount)) {
            //底部View
            return ITEM_TYPE_BOTTOM;
        } else {
            //内容View
            return ITEM_TYPE_CONTENT;
        }
    }
}
