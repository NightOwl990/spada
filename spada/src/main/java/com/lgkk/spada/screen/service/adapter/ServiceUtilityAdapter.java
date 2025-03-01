package com.lgkk.spada.screen.service.adapter;


import android.app.Activity;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.base_project.utils.ImageUtils;
import com.lgkk.spada.R;
import com.lgkk.spada.model.service.ServiceUtility;

import java.util.List;

public class ServiceUtilityAdapter extends BaseQuickAdapter<ServiceUtility, BaseViewHolder> {
    public List<ServiceUtility> list;
    private Activity activity;
    OnItemRvClickListener onItemRvClickListener;


    public ServiceUtilityAdapter(Activity activity, List<ServiceUtility> data, OnItemRvClickListener onItemRvClickListener) {
        super(R.layout.item_service_utility, data);
        this.onItemRvClickListener = onItemRvClickListener;
        this.list = data;
        this.activity = activity;
    }

    ImageView imgService;


    @Override
    protected void convert(BaseViewHolder holder, ServiceUtility item) {
        holder.setText(R.id.txtDescription, item.getDescription());
        holder.setText(R.id.txtName, item.getName());
        imgService = holder.getView(R.id.imgService);

//        ImageUtils.loadImageByGlide(activity, item.getDrawableImage(), imgService);
        ImageUtils.loadImageDrawableByGlide(activity, item.getDrawableImage(), imgService);

    }
}
