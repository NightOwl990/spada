package com.lgkk.spada.screen.service.adapter;


import android.app.Activity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.spada.R;
import com.lgkk.spada.model.service.SecondServiceCategory;

import java.util.List;

public class SecondOptionCategoryAdapter extends BaseQuickAdapter<SecondServiceCategory, BaseViewHolder> {
    public List<SecondServiceCategory> list;
    private Activity activity;
    OnItemRvClickListener onItemRvClickListener;


    public SecondOptionCategoryAdapter(Activity activity, List<SecondServiceCategory> data, OnItemRvClickListener onItemRvClickListener) {
        super(R.layout.item_text_service_category, data);
        this.onItemRvClickListener = onItemRvClickListener;
        this.list = data;
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder holder, SecondServiceCategory item) {
        holder.setText(R.id.txtName, item.getName());
//        imgService = holder.getView(R.id.imgService);
//
////        ImageUtils.loadImageByGlide(activity, item.getImage(), imgService);
//        ImageUtils.loadImageDrawableByGlide(activity, item.getDrawableImage(), imgService);

    }
}
