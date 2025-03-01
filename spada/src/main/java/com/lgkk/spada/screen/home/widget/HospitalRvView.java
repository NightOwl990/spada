package com.lgkk.spada.screen.home.widget;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.lgkk.base_project.base.listener.OnCustomViewClickListener;
import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.spada.R;
import com.lgkk.spada.model.HospitalDetail;
import com.lgkk.spada.screen.home.adapter.HospitalListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HospitalRvView extends LinearLayout implements View.OnClickListener, OnItemRvClickListener {

    public static final String TYPE_REFRESH_DATA = "refreshData";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private Unbinder unbinder1;
    private OnCustomViewClickListener listener;
    private Context context;
    private HospitalListAdapter mAdapter;
    private List<HospitalDetail> hospitalDetailList = new ArrayList<>();

    public HospitalRvView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_hospital_rv, this, true);
        unbinder1 = ButterKnife.bind(this, view);
        this.context = context;
        ;
    }

    public HospitalRvView(Context context) {
        this(context, (AttributeSet) null);
    }

    public HospitalRvView(Context context, List<HospitalDetail> hospitalDetailList) {
        this(context);
        this.hospitalDetailList = hospitalDetailList;
        initView();
    }

    public void setDataProduct(List<HospitalDetail> hospitalDetailList) {
        this.hospitalDetailList.clear();
        this.hospitalDetailList.addAll(hospitalDetailList);
        mAdapter.notifyDataSetChanged();
    }


    public void setListener(OnCustomViewClickListener listener) {
        this.listener = listener;
    }

    private void initView() {

        mAdapter = new HospitalListAdapter(context, hospitalDetailList);
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



