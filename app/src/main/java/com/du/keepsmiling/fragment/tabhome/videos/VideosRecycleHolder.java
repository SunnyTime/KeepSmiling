package com.du.keepsmiling.fragment.tabhome.videos;

import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.du.keepsmiling.R;
import com.xiao.nicevideoplayer.NiceVideoPlayer;

/**
 * ClassName: JokesRecycleHolder
 * Function: JokesRecycleHolder
 * date: 2017/12/11.
 * author dushiguang
 * version 0.1
 */
public class VideosRecycleHolder extends ViewHolder {
    ImageView viewImage;
    TextView viewName;
    TextView viewTime;
    TextView viewContent;

    AppCompatCheckBox viewLove;
    AppCompatCheckBox viewHate;
    AppCompatCheckBox viewShare;

    NiceVideoPlayer viewNiceVideoPlayer;

    public VideosRecycleHolder(View itemView) {
        super(itemView);

        viewImage = (ImageView) itemView.findViewById(R.id.view_image);
        viewName = (TextView) itemView.findViewById(R.id.view_name);
        viewTime = (TextView) itemView.findViewById(R.id.view_time);
        viewContent = (TextView) itemView.findViewById(R.id.view_content);
        viewLove = (AppCompatCheckBox) itemView.findViewById(R.id.view_love);
        viewHate = (AppCompatCheckBox) itemView.findViewById(R.id.view_hate);
        viewShare = (AppCompatCheckBox)itemView.findViewById(R.id.view_share);
        viewNiceVideoPlayer = (NiceVideoPlayer)itemView.findViewById(R.id.view_video_player);

        // 将列表中的每个视频设置为默认16:9的比例
        ViewGroup.LayoutParams params = viewNiceVideoPlayer.getLayoutParams();
        params.width = itemView.getResources().getDisplayMetrics().widthPixels; // 宽度为屏幕宽度
        params.height = (int) (params.width * 9f / 16f);    // 高度为宽度的9/16
        viewNiceVideoPlayer.setLayoutParams(params);
    }
}
