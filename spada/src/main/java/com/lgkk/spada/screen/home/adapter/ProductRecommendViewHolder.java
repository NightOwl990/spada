package com.lgkk.spada.screen.home.adapter;


import android.content.Context;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.spada.R;
import com.lgkk.spada.model.shop.Product;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductRecommendViewHolder implements MZViewHolder<List<Product>>, OnItemRvClickListener<Product> {

    @BindView(R.id.rvRecommend)
    RecyclerView rvRecommend;
    private ProductRecommendAdapter productRecommendAdapter;
    OnItemRvClickListener onItemRvClickListener;

    public ProductRecommendViewHolder(OnItemRvClickListener onItemRvClickListener) {
        this.onItemRvClickListener = onItemRvClickListener;
    }

    @Override
    public View createView(Context context) {
        // 返回页面布局
        View view = LayoutInflater.from(context).inflate(R.layout.custom_product_recommend_banner,null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onBind(Context context, int position, List<Product> data) {

//        Rv Product -----------------
        productRecommendAdapter = new ProductRecommendAdapter(context, data, this);      ;
        rvRecommend.setHasFixedSize(true);
        rvRecommend.setNestedScrollingEnabled(false);

        GridLayoutManager gridLayoutManagerShop = new GridLayoutManager(context, 3);
        rvRecommend.setLayoutManager(gridLayoutManagerShop);
        rvRecommend.setAdapter(productRecommendAdapter);
        productRecommendAdapter.notifyDataSetChanged();
        productRecommendAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                onItemRvClickListener.onItemRvClick(view, data.get(position), position);
            }
        });
    }

    @Override
    public void onItemRvClick(View view, Product item, int adapterPosition) {

    }
}
