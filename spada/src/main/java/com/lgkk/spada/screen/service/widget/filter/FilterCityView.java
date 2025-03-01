package com.lgkk.spada.screen.service.widget.filter;


import android.content.Context;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.spada.R;
import com.lgkk.spada.model.FilterTypeDetail;
import com.lgkk.spada.model.FilterTypeSecond;
import com.lgkk.base_project.base.listener.OnCustomViewClickListener;
import com.lgkk.spada.screen.service.widget.filter.adapter.FirstCityFilterAdapter;
import com.lgkk.spada.screen.service.widget.filter.adapter.SecondCityFilterAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FilterCityView extends LinearLayout implements View.OnClickListener, OnItemRvClickListener {
    public static final String TYPE_CHOOSE_CLICK = "filterCity";

    private FirstCityFilterAdapter firstAdapter;
    private SecondCityFilterAdapter secondAdapter;

    @BindView(R.id.rvFirst)
    RecyclerView rvFirst;
    @BindView(R.id.rvSecond)
    RecyclerView rvSecond;

    private Unbinder unbinder1;
    private OnCustomViewClickListener listener;
    private Context context;
    private List<FilterTypeDetail> dataList = new ArrayList<>();
    private List<FilterTypeSecond> dataDistrictList = new ArrayList<>();
    private int lastSelectedCity = -1;
    private int lastSelectedDistrict = -1;
    private int savedSelectedCity = -1;
    private int savedSelectedDistrict = -1;

    public FilterCityView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_filter_city, this, true);
        unbinder1 = ButterKnife.bind(this, view);
        this.context = context;

    }

    public FilterCityView(Context context) {
        this(context, (AttributeSet) null);
    }


    public FilterCityView(Context context, List<FilterTypeDetail> dataList) {
        this(context);
        this.dataList = dataList;
        initView();
    }



    public void setData(List<FilterTypeDetail> dataSetList, int startPosition) {
        dataList.clear();
        dataList.addAll(dataSetList);
        dataDistrictList.clear();
        dataList.get(startPosition).setSelected(true);
        if (dataList.get(startPosition).getSecondTypeList()!=null)
        dataDistrictList.addAll(dataList.get(startPosition).getSecondTypeList());
        dataList.get(startPosition).setLastSelectedPosition(startPosition);
        lastSelectedCity = 0;
        initView();
    }

    public void setListener(OnCustomViewClickListener listener) {
        this.listener = listener;
    }



    private void initView() {
        firstAdapter = new FirstCityFilterAdapter(context, dataList, this);
        LinearLayoutManager layoutManagerNews = new LinearLayoutManager(context);
        rvFirst.setLayoutManager(layoutManagerNews);
        rvFirst.setAdapter(firstAdapter);

        secondAdapter = new SecondCityFilterAdapter(context, dataDistrictList, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
        rvSecond.setLayoutManager(gridLayoutManager);
        rvSecond.setAdapter(secondAdapter);

        firstAdapter.setOnItemClickListener((adapter, view, position) -> {
            dataList.get(position).setSelected(true);
            if (lastSelectedCity!=-1 && lastSelectedCity!= position) {
                dataList.get(lastSelectedCity).setSelected(false);
            }
            lastSelectedCity = position;
            dataDistrictList.clear();
            if (dataList.get(position).getSecondTypeList()!=null) {
                dataDistrictList.addAll(dataList.get(position).getSecondTypeList());
            }

            secondAdapter.notifyDataSetChanged();
            firstAdapter.notifyDataSetChanged();
        });

        secondAdapter.setOnItemClickListener((adapter, view, position2) -> {
            if (savedSelectedDistrict!=-1 &&  savedSelectedCity!=-1) {
                dataList.get(savedSelectedCity).getSecondTypeList().get(savedSelectedDistrict).setSelected(false);
            }
            List<FilterTypeSecond> districtList = dataList.get(lastSelectedCity).getSecondTypeList();
            districtList.get(position2).setSelected(true);
            if (lastSelectedDistrict!=-1 && lastSelectedDistrict!= position2) {
                dataDistrictList.get(lastSelectedDistrict).setSelected(false);
            }
            lastSelectedDistrict = position2;
            savedSelectedCity = lastSelectedCity;
            savedSelectedDistrict = position2;

            secondAdapter.notifyDataSetChanged();

            listener.onClickCustomView(view, TYPE_CHOOSE_CLICK, savedSelectedCity, savedSelectedDistrict);
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

