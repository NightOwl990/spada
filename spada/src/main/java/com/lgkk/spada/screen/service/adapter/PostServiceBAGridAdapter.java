package com.lgkk.spada.screen.service.adapter;


import android.app.Activity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.base_project.utils.FormatUtils;
import com.lgkk.base_project.utils.ImageUtils;
import com.lgkk.spada.R;
import com.lgkk.spada.model.community.post.PostServiceMostView;

;import java.util.List;

public class PostServiceBAGridAdapter extends BaseQuickAdapter<PostServiceMostView, BaseViewHolder> {
    public List<PostServiceMostView> list;
    private Activity activity;
    OnItemRvClickListener onItemRvClickListener;


    public PostServiceBAGridAdapter(Activity activity, List<PostServiceMostView> data, OnItemRvClickListener onItemRvClickListener) {
        super(R.layout.item_service_before_after_style1, data);
        this.onItemRvClickListener = onItemRvClickListener;
        this.list = data;
        this.activity = activity;
    }


    @Override
    protected void convert(BaseViewHolder holder, PostServiceMostView item) {
        holder.setText(R.id.txtTitle, item.getTitle());
        holder.setText(R.id.txtName, item.getUser().getName());
        holder.setText(R.id.txtViewNumber, FormatUtils.withSuffix(item.getViewNumber()));

//        ImageUtils.loadRoundedImageByGlide(activity, item.getImages().get(0), holder.getView(R.id.imgBefore));
//        ImageUtils.loadRoundedImageByGlide(activity, item.getImages().get(1), holder.getView(R.id.imgAfter));
        ImageUtils.loadCircleImageAvatarByGlide(activity, item.getUser().getAvatar(), holder.getView(R.id.imgAvatar));

    }
}