package com.lgkk.spada.screen.service.widget.filter;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.spada.R;

import com.lgkk.spada.model.FilterTypeDetail;
import com.lgkk.base_project.base.listener.OnCustomViewClickListener;
import com.lgkk.spada.screen.service.widget.filter.adapter.FilterLinearAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FilterPlaceView extends LinearLayout implements View.OnClickListener, OnItemRvClickListener {
    public static final String TYPE_CHOOSE_CLICK = "filterPlace";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.lnPlaceEmpty)
    LinearLayout lnPlaceEmpty;

    private Unbinder unbinder1;
    private OnCustomViewClickListener listener;
    private Context context;
    private FilterLinearAdapter mAdapter;
    private List<FilterTypeDetail> dataList = new ArrayList<>();
    private int lastSelected = -1;

    public FilterPlaceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_filter_place, this, true);
        unbinder1 = ButterKnife.bind(this, view);
        this.context = context;

    }

    public FilterPlaceView(Context context) {
        this(context, (AttributeSet) null);
    }

    public FilterPlaceView(Context context, List<FilterTypeDetail> dataList) {
        this(context);
        this.dataList = dataList;
        initView();
    }


    public void setListener(OnCustomViewClickListener listener) {
        this.listener = listener;
    }

    public void setEmptyPlace() {
        lnPlaceEmpty.setVisibility(VISIBLE);
        recyclerView.setVisibility(GONE);
    }

    public void showPlace() {
        lnPlaceEmpty.setVisibility(GONE);
        recyclerView.setVisibility(VISIBLE);
    }

    public void setData(List<FilterTypeDetail> typeList) {
        this.dataList = typeList;
        showPlace();
        initView();
    }


    private void initView() {
        mAdapter = new FilterLinearAdapter(context, dataList, this);
        LinearLayoutManager layoutManagerNews = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManagerNews);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            dataList.get(position).setSelected(true);
            if (lastSelected!=-1 && lastSelected!= position) {
                dataList.get(lastSelected).setSelected(false);
            }
            lastSelected = position;
            mAdapter.notifyDataSetChanged();
            listener.onClickCustomView(view, TYPE_CHOOSE_CLICK, position);
        });
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

