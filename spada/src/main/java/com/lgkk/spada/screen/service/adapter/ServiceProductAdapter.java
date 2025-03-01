package com.lgkk.spada.screen.service.adapter;


import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.spada.R;
import com.lgkk.spada.model.service.ServiceDetails;
import com.lgkk.spada.screen.service.activity.ServiceDetailActivity;

import java.util.List;

public class ServiceProductAdapter extends BaseQuickAdapter<ServiceDetails, BaseViewHolder> {
    public List<ServiceDetails> list;
    private Context context;
    OnItemRvClickListener onItemRvClickListener;


    public ServiceProductAdapter(Context context, List<ServiceDetails> data, OnItemRvClickListener onItemRvClickListener) {
        super(R.layout.item_service_product, data);
        this.onItemRvClickListener = onItemRvClickListener;
        this.list = data;
        this.context = context;
    }

    ImageView imgService;


    @Override
    protected void convert(BaseViewHolder holder, ServiceDetails item) {
        holder.getView(R.id.lnMain).setOnClickListener(v -> {
            ServiceDetailActivity.startActivity(context, item.getId());
        });
    }
}
