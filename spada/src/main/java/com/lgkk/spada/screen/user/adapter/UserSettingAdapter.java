package com.lgkk.spada.screen.user.adapter;


import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.spada.R;
import com.lgkk.spada.model.UserSetting;

import java.util.List;

public class UserSettingAdapter extends BaseQuickAdapter<UserSetting, BaseViewHolder> {

    public List<UserSetting> list;
    private Activity activity;
    OnItemRvClickListener onItemRvClickListener;


    public UserSettingAdapter(Activity activity, List<UserSetting> data, OnItemRvClickListener onItemRvClickListener) {
        super(R.layout.item_user_service, data);
        this. onItemRvClickListener = onItemRvClickListener;
        this.list = data;
        this.activity = activity;
    }

    private ImageView imgService;
    private TextView txtAmount;
    @Override
    protected void convert(final BaseViewHolder holder, UserSetting item) {
        holder.setText(R.id.txtDescription, item.getName());
        imgService = holder.getView(R.id.imgService);

        txtAmount = holder.getView(R.id.txtAmount);        // load image picasso
        Glide.with(activity).load(item.getDrawableImage()).into(imgService);

        txtAmount.setVisibility(item.getAmountNotification()>0 ? View.VISIBLE: View.GONE);
        txtAmount.setText(item.getAmountNotification()<=99 ? item.getAmountNotification()+"": "99+");
    }
}

