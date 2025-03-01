package com.lgkk.spada.screen.service.adapter;


import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.spada.R;
import com.lgkk.spada.model.Doctor;
import com.lgkk.spada.screen.service.activity.DoctorInfoActivity;

import java.util.List;

public class ServiceDoctorAdapter extends BaseQuickAdapter<Doctor, BaseViewHolder> {
    public List<Doctor> list;
    private Activity activity;
    OnItemRvClickListener onItemRvClickListener;


    public ServiceDoctorAdapter(Activity activity, List<Doctor> data, OnItemRvClickListener onItemRvClickListener) {
        super(R.layout.item_service_category_doctor, data);
        this.onItemRvClickListener = onItemRvClickListener;
        this.list = data;
        this.activity = activity;
    }

    ImageView imgService;


    @Override
    protected void convert(BaseViewHolder holder, Doctor item) {
        holder.getView(R.id.rlDoctor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoctorInfoActivity.startActivity(activity, item.getId());
            }
        });
    }
}

