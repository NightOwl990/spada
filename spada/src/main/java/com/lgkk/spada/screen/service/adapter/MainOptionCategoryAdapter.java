package com.lgkk.spada.screen.service.adapter;


import android.app.Activity;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.base_project.utils.ImageUtils;
import com.lgkk.spada.R;
import com.lgkk.spada.model.service.MainServiceCategory;

import java.util.List;

public class MainOptionCategoryAdapter extends BaseQuickAdapter<MainServiceCategory, BaseViewHolder> {
    public List<MainServiceCategory> list;
    private Activity activity;
    OnItemRvClickListener onItemRvClickListener;


    public MainOptionCategoryAdapter(Activity activity, List<MainServiceCategory> data, OnItemRvClickListener onItemRvClickListener) {
        super(R.layout.item_service_category_main, data);
        this.onItemRvClickListener = onItemRvClickListener;
        this.list = data;
        this.activity = activity;
    }

    ImageView imgService;

    @Override
    protected void convert(BaseViewHolder holder, MainServiceCategory item) {
        holder.setText(R.id.txtName, item.getName());
        imgService = holder.getView(R.id.imgService);

//        ImageUtils.loadImageByGlide(activity, item.getImage(), imgService);
        ImageUtils.loadImageDrawableByGlide(activity, item.getDrawableImage(), imgService);

    }
}


