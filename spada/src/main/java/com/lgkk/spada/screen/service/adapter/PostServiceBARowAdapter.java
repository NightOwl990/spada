package com.lgkk.spada.screen.service.adapter;


import android.app.Activity;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.base_project.utils.ImageUtils;
import com.lgkk.spada.R;
import com.lgkk.spada.model.community.post.PostService;
import com.lgkk.base_project.widget.roundedimageview.RoundedImageView;

import java.util.List;

public class PostServiceBARowAdapter extends BaseQuickAdapter<PostService, BaseViewHolder> {
    public List<PostService> list;
    private Activity activity;
    OnItemRvClickListener onItemRvClickListener;


    public PostServiceBARowAdapter(Activity activity, List<PostService> data, OnItemRvClickListener onItemRvClickListener) {
        super(R.layout.item_service_before_after_live, data);
        this.onItemRvClickListener = onItemRvClickListener;
        this.list = data;
        this.activity = activity;
    }


    private RoundedImageView imgBefore;
    private ImageView imgLevel;
    @Override
    protected void convert(BaseViewHolder holder, PostService item) {
        imgBefore = holder.getView(R.id.imgBefore);
        imgLevel = holder.getView(R.id.imgLevel);
        ImageUtils.loadImageDrawableByGlide(activity,R.drawable.level17, imgLevel);
//        imgLevel.setImageDrawable(activity.getResources().getDrawable(R.drawable.level17));

    }
}