package com.lgkk.spada.screen._main;


import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.spada.R;

import java.util.List;

public class CheckUpdateContentAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public List<String> list;
    private Context context;
    private OnItemRvClickListener onItemRvClickListener;

    public CheckUpdateContentAdapter(Context context, List<String> data, OnItemRvClickListener onItemRvClickListener) {
        super(R.layout.item_check_update_content, data);
        this.onItemRvClickListener = onItemRvClickListener;
        this.list = data;
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder holder, String item) {
        holder.setText(R.id.txtDescription, "- " + item);
    }
}
