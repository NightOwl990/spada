package com.lgkk.spada.screen.service.adapter;


import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.base_project.utils.ImageUtils;
import com.lgkk.spada.R;
import com.lgkk.spada.model.service.ServiceComment;

import java.util.List;

public class ServiceCommentAdapter extends BaseQuickAdapter<ServiceComment, BaseViewHolder> {
    public List<ServiceComment> list;
    private Activity activity;
    OnItemRvClickListener onItemRvClickListener;


    public ServiceCommentAdapter(Activity activity, List<ServiceComment> data, OnItemRvClickListener onItemRvClickListener) {
        super(R.layout.item_service_detail_comment, data);
        this.onItemRvClickListener = onItemRvClickListener;
        this.list = data;
        this.activity = activity;
    }
    private ImageView imgLevel;
    private RatingBar rbRate;
    @Override
    protected void convert(BaseViewHolder holder, ServiceComment item) {
//        holder.setText(R.id.txtName, item.getName());
        imgLevel = holder.getView(R.id.imgLevel);
        rbRate = holder.getView(R.id.rbRate);

        Drawable starDrawable = activity.getResources().getDrawable(R.drawable.evaluate_star_10);
        int height = starDrawable.getMinimumHeight();
        ViewGroup.LayoutParams params =  rbRate.getLayoutParams();
        params.height = (int) (height * 1.3);
        rbRate.setLayoutParams(params);
//
////        ImageUtils.loadImageByGlide(activity, item.getImage(), imgService);
        ImageUtils.loadImageDrawableByGlide(activity, R.drawable.level15, imgLevel);

    }
}
