package com.lgkk.spada.screen.service.adapter;


import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.base_project.utils.ImageUtils;
import com.lgkk.spada.R;
import com.lgkk.spada.model.community.post.PostService;

import java.util.List;

public class StaggedGridPostAdapter extends BaseQuickAdapter<PostService, BaseViewHolder> {
    public List<PostService> list;
    private Context context;
    OnItemRvClickListener onItemRvClickListener;


    public StaggedGridPostAdapter(Context context, List<PostService> data, OnItemRvClickListener onItemRvClickListener) {
        super(R.layout.item_stagged_grid_post, data);
        this.onItemRvClickListener = onItemRvClickListener;
        this.list = data;
        this.context = context;
    }


    @Override
    protected void convert(BaseViewHolder holder, PostService item) {
        if (item.getImages().size()>=2) {
            ImageUtils.loadImageByGlide(context, item.getImages().get(0), holder.getView(R.id.imgAfter),"fitXY");
            holder.setGone(R.id.imgBefore, true);
            ImageUtils.loadImageByGlide(context, item.getImages().get(1), holder.getView(R.id.imgBefore));
        } else {
            ImageUtils.loadImageByGlide(context, item.getImages().get(0), holder.getView(R.id.imgAfter),"fitXY");
            holder.setGone(R.id.imgBefore, false);
        }

        holder.setText(R.id.txtContent, item.getContent())
                .setText(R.id.txtLikeNumber, item.getLikeNumber()+"");

    }
}

