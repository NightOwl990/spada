package com.lgkk.spada.screen.service.widget.filter.adapter;


import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.spada.R;
import com.lgkk.spada.model.FilterTypeDetail;

import java.util.List;

public class FirstCityFilterAdapter extends BaseQuickAdapter<FilterTypeDetail, BaseViewHolder> {
    public List<FilterTypeDetail> list;
    private Context context;
    OnItemRvClickListener onItemRvClickListener;


    public FirstCityFilterAdapter(Context context, List<FilterTypeDetail> data, OnItemRvClickListener onItemRvClickListener) {
        super(R.layout.item_first_city_filter, data);
        this.onItemRvClickListener = onItemRvClickListener;
        this.list = data;
        this.context = context;
    }


    @Override
    protected void convert(BaseViewHolder holder, FilterTypeDetail item) {
        holder.setText(R.id.txtName, item.getTagName());
        holder.getView(R.id.txtName).setSelected(item.isSelected());
    }
}


