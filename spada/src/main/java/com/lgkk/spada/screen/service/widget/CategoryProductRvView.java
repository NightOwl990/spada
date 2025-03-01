package com.lgkk.spada.screen.service.widget;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;


import com.lgkk.base_project.base.listener.OnCustomViewClickListener;
import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.spada.R;
import com.lgkk.spada.model.service.ServiceDetails;
import com.lgkk.spada.screen.service.adapter.ServiceProductAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CategoryProductRvView extends LinearLayout implements View.OnClickListener, OnItemRvClickListener {
    public static final int TYPE_LEFT_CLICK = 0;
    public static final int TYPE_RIGHT_CLICK = 1;
    public static final int TYPE_TOOLBAR_CLICK = 2;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private Unbinder unbinder1;
    private OnCustomViewClickListener listener;
    private Context context;
    private ServiceProductAdapter mAdapter;
    private List<ServiceDetails> serviceDetailsList = new ArrayList<>();

    public CategoryProductRvView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_category_product_rv, this, true);
        unbinder1 = ButterKnife.bind(this, view);
        this.context = context;
     ;    }

    public CategoryProductRvView(Context context) {
        this(context, (AttributeSet) null);
    }

    public CategoryProductRvView(Context context, List<ServiceDetails> serviceDetailsList) {
        this(context);
        this.serviceDetailsList = serviceDetailsList;
        initView();
    }

    public void setDataProduct(List<ServiceDetails> serviceDetailsList) {
        this.serviceDetailsList.clear();
        this.serviceDetailsList.addAll(serviceDetailsList);
        mAdapter.notifyDataSetChanged();
    }


    public void setListener(OnCustomViewClickListener listener) {
        this.listener = listener;
    }

    private void initView() {
        mAdapter = new ServiceProductAdapter(context, serviceDetailsList, this);
        LinearLayoutManager layoutManagerNews = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManagerNews);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }

    }

    @Override
    public void onItemRvClick(View view, Object item, int adapterPosition) {

    }
}


