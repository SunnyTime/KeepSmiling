package com.du.keepsmiling.fragment.tabhome.jokes;

import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.du.keepsmiling.R;

/**
 * ClassName: JokesRecycleHolder
 * Function: JokesRecycleHolder
 * date: 2017/12/11.
 * author dushiguang
 * version 0.1
 */
public class JokesRecycleHolder extends ViewHolder {
    ImageView viewImage;
    TextView viewName;
    TextView viewTime;
    TextView viewContent;

    AppCompatCheckBox viewLove;
    AppCompatCheckBox viewHate;
    AppCompatCheckBox viewShare;

    public JokesRecycleHolder(View itemView) {
        super(itemView);

        viewImage = (ImageView) itemView.findViewById(R.id.view_image);
        viewName = (TextView) itemView.findViewById(R.id.view_name);
        viewTime = (TextView) itemView.findViewById(R.id.view_time);
        viewContent = (TextView) itemView.findViewById(R.id.view_content);
        viewLove = (AppCompatCheckBox) itemView.findViewById(R.id.view_love);
        viewHate = (AppCompatCheckBox) itemView.findViewById(R.id.view_hate);
        viewShare = (AppCompatCheckBox)itemView.findViewById(R.id.view_share);
    }
}
