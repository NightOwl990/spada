package com.lgkk.spada.screen.home.adapter;


import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.base_project.utils.ImageUtils;
import com.lgkk.spada.R;
import com.lgkk.spada.model.FlashCategory;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.iwgang.countdownview.CountdownView;

public class FlashCategoryAdapter extends BaseQuickAdapter<FlashCategory, BaseViewHolder> {

    public List<FlashCategory> list;
    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.cdFlash)
    CountdownView cdFlash;
    @BindView(R.id.lnFlash)
    LinearLayout lnFlash;
    @BindView(R.id.txtDescription)
    TextView txtDescription;
    @BindView(R.id.txtDiscount)
    TextView txtDiscount;
    @BindView(R.id.imgFlash)
    ImageView imgFlash;
    @BindView(R.id.imgIcon)
    ImageView imgIcon;
    @BindView(R.id.dividerTop)
    View dividerTop;
    @BindView(R.id.dividerBot)
    View dividerBot;
    private Activity activity;
    OnItemRvClickListener onItemRvClickListener;

    public FlashCategoryAdapter(Activity activity, List<FlashCategory> data, OnItemRvClickListener onItemRvClickListener) {
        super(R.layout.item_flash_sale, data);
        this.onItemRvClickListener = onItemRvClickListener;
        this.list = data;
        this.activity = activity;
    }


    @Override
    protected void convert(BaseViewHolder holder, FlashCategory item) {
        ButterKnife.bind(this, holder.itemView);
        if (item.getType().equals("flash")) {
            cdFlash.setTag("cdtimer1");
            long time1 = (long) 5 * 60 * 60 * 1000;
            cdFlash.start(time1);
        } else cdFlash.setVisibility(View.GONE);

        txtTitle.setText(item.getTitle());
        txtDescription.setText(item.getDescription());
        txtDiscount.setText(item.getDiscountDescription());
        ImageUtils.loadImageDrawableByGlide(activity, item.getImageDrawable(), imgFlash);
        ImageUtils.loadImageDrawableByGlide(activity, item.getDrawable(), imgIcon);
        if (holder.getAdapterPosition()==0 || holder.getAdapterPosition()==1) {
            dividerTop.setVisibility(View.GONE);
        }
        if (holder.getAdapterPosition()==2 || holder.getAdapterPosition()==3) {
            dividerBot.setVisibility(View.GONE);
        }
    }
}



