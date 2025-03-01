package com.lgkk.spada.screen.service.widget.filter.adapter;


import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.spada.R;
import com.lgkk.spada.model.FilterTypeSecond;

import java.util.List;

public class SecondCityFilterAdapter extends BaseQuickAdapter<FilterTypeSecond, BaseViewHolder> {
    public List<FilterTypeSecond> list;
    private Context context;
    OnItemRvClickListener onItemRvClickListener;


    public SecondCityFilterAdapter(Context context, List<FilterTypeSecond> data, OnItemRvClickListener onItemRvClickListener) {
        super(R.layout.item_second_city_filter, data);
        this.onItemRvClickListener = onItemRvClickListener;
        this.list = data;
        this.context = context;
    }


    @Override
    protected void convert(BaseViewHolder holder, FilterTypeSecond item) {
        holder.setText(R.id.txtName, item.getTagName());
        holder.getView(R.id.txtName).setSelected(item.isSelected());
        holder.getView(R.id.lnName).setSelected(item.isSelected());
    }
}
