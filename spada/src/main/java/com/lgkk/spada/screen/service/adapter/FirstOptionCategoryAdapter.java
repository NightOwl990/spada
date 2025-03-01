package com.lgkk.spada.screen.service.adapter;


import android.app.Activity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.spada.R;
import com.lgkk.spada.model.service.FirstServiceCategory;
import com.lgkk.spada.screen.service.activity.FilterServiceListHandlerActivity;

import java.util.List;

public class FirstOptionCategoryAdapter extends BaseQuickAdapter<FirstServiceCategory, BaseViewHolder> {
    public List<FirstServiceCategory> list;
    private Activity activity;
    OnItemRvClickListener onItemRvClickListener;


    public FirstOptionCategoryAdapter(Activity activity, List<FirstServiceCategory> data, OnItemRvClickListener onItemRvClickListener) {
        super(R.layout.item_text_service_category, data);
        this.onItemRvClickListener = onItemRvClickListener;
        this.list = data;
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder holder, FirstServiceCategory item) {
        holder.setText(R.id.txtName, item.getName());
        holder.getView(R.id.rootRvView).setOnClickListener(v -> {
            FilterServiceListHandlerActivity.startActivity(activity, "123");
        });
//        imgService = holder.getView(R.id.imgService);
//
////        ImageUtils.loadImageByGlide(activity, item.getImage(), imgService);
//        ImageUtils.loadImageDrawableByGlide(activity, item.getDrawableImage(), imgService);

    }
}

