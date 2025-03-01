package com.lgkk.spada.screen.home.adapter;


import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.base_project.utils.ImageUtils;
import com.lgkk.base_project.utils.StringUtils;
import com.lgkk.spada.R;
import com.lgkk.spada.model.shop.Product;

import java.util.List;

public class ProductRecommendAdapter extends BaseQuickAdapter<Product, BaseViewHolder> {

    public List<Product> list;
    private Context context;
    OnItemRvClickListener onItemRvClickListener;

    public ProductRecommendAdapter(Context context, List<Product> data, OnItemRvClickListener onItemRvClickListener    ) {
        super(R.layout.item_grid_product_recommend, data);
        this. onItemRvClickListener = onItemRvClickListener;
        this.list = data;
        this.context = context;    }

    TextView txtName, txtTag, txtPrice, txtDiscountPrice, txtQuantity;
    ImageView imgProduct;

    @Override
    protected void convert(BaseViewHolder holder, Product item) {
//        Config View -----------------------
        txtName = holder.getView(R.id.txtName);
        txtPrice = holder.getView(R.id.txtPrice);
        txtDiscountPrice = holder.getView(R.id.txtDiscountPrice);
        txtQuantity = holder.getView(R.id.txtOrderNumber);
        imgProduct = holder.getView(R.id.imgProduct);

//        Setting Data ------------------------

        txtName.setText(item.getName());
        txtPrice.setText(item.getPrice()+"");
        txtDiscountPrice.setText(StringUtils.formatPrice(item.getPrice()*(100-item.getDiscountPercent())/100 + ""));
        txtQuantity.setText(String.format(context.getString(R.string.shop_quantity), item.getQuantity()));
        txtQuantity.setVisibility(View.GONE);
        if (item.getDiscountPercent()>0) {
            holder.setGone(R.id.txtDiscountPercent, true);
            holder.setText(R.id.txtDiscountPercent, item.getDiscountPercent() + "%");
        } else {
            holder.setGone(R.id.txtDiscountPercent, false);
        }

        ImageUtils.loadImageByGlide(context,  item.getAvatar(), imgProduct);
    }
}


