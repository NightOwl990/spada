package com.lgkk.spada.screen.home.adapter;

import android.app.Activity;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lgkk.spada.R;
import com.lgkk.base_project.widget.TitleFlashSale;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TitleFlashSaleAdapter extends BaseQuickAdapter<TitleFlashSale, BaseViewHolder> {

    List<TitleFlashSale> list;
    @BindView(R.id.txtHour)
    TextView txtHour;
    @BindView(R.id.txtStatus)
    TextView txtStatus;
    private Activity activity;

    public TitleFlashSaleAdapter(Activity activity, List<TitleFlashSale> data) {
        super(R.layout.item_title_flash_sale, data);
        this.list = data;
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder holder, TitleFlashSale item) {
        ButterKnife.bind(this, holder.itemView);

        txtHour.setText(item.getHour()+":00");
        txtStatus.setText(item.getStatus());
    }
}

