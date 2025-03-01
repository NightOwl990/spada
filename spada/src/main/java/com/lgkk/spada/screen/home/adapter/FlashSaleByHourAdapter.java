package com.lgkk.spada.screen.home.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lgkk.base_project.widget.QuantityPercentView;
import com.lgkk.base_project.widget.roundedimageview.RoundedImageView;
import com.lgkk.spada.R;
import com.lgkk.spada.model.shop.Product;
import com.lgkk.spada.screen.service.activity.ServiceDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FlashSaleByHourAdapter extends BaseQuickAdapter<Product, BaseViewHolder> {

    @BindView(R.id.imgBefore)
    RoundedImageView imgBefore;
    @BindView(R.id.rlImage)
    RelativeLayout rlImage;
    @BindView(R.id.txtName)
    TextView txtName;
    @BindView(R.id.rlName)
    RelativeLayout rlName;
    @BindView(R.id.txtShortDescription)
    TextView txtShortDescription;
    @BindView(R.id.txtPrice)
    TextView txtPrice;
    @BindView(R.id.txtDiscountPrice)
    TextView txtDiscountPrice;
    @BindView(R.id.lnDiscountPrice)
    LinearLayout lnDiscountPrice;
    @BindView(R.id.txtVipPrice)
    TextView txtVipPrice;
    @BindView(R.id.txtBuyNow)
    TextView txtBuyNow;
    @BindView(R.id.percentView)
    QuantityPercentView percentView;
    @BindView(R.id.rlTop)
    RelativeLayout rlTop;
    @BindView(R.id.lnMain)
    LinearLayout lnMain;
    private Activity activity;
    List<Product> list;

    public FlashSaleByHourAdapter(Activity activity, List<Product> data) {
        super(R.layout.item_flash_sale_by_hour, data);
        this.list = data;
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder holder, Product item) {
        ButterKnife.bind(this, holder.itemView);

        percentView.setData(43, 25);

        lnMain.setOnClickListener(v -> {
            ServiceDetailActivity.startActivity(activity, item.getId());
        });
    }
}


