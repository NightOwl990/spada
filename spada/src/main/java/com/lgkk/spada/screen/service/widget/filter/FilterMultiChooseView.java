package com.lgkk.spada.screen.service.widget.filter;

import android.content.Context;
import androidx.core.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lgkk.base_project.widget.flowlayout.AutoFlowLayout;
import com.lgkk.spada.R;
import com.lgkk.spada.model.FilterTypeDetail;
import com.lgkk.base_project.base.listener.OnCustomViewClickListener;


import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FilterMultiChooseView extends LinearLayout implements View.OnClickListener {
    public static final String TYPE_ROOT_CLICK = "multiChooseRoot";
    public static final String TYPE_LEFT_CLICK = "multiChooseReset";
    public static final String TYPE_CHOOSE_CLICK = "multiChooseSubmit";

    @BindView(R.id.rootCustomView)
    RelativeLayout rootCustomView;

    @BindView(R.id.btnReset)
    TextView btnReset;
    @BindView(R.id.btnConfirm)
    TextView btnConfirm;
    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;
    @BindView(R.id.filterMachine)
    FilterTagFlowLayout filterMachine;
    @BindView(R.id.filterBenefit)
    FilterTagFlowLayout filterBenefit;
    @BindView(R.id.filterSupport)
    FilterTagFlowLayout filterSupport;
    @BindView(R.id.edtRangePrice1)
    EditText edtRangePrice1;
    @BindView(R.id.edtRangePrice2)
    EditText edtRangePrice2;
    @BindView(R.id.filterReality)
    FilterTagFlowLayout filterReality;
    @BindView(R.id.filterTrademark)
    FilterTagFlowLayout filterTrademark;
    @BindView(R.id.filterComment)
    FilterTagFlowLayout filterComment;
    @BindView(R.id.filterRule)
    FilterTagFlowLayout filterRule;
    @BindView(R.id.filterLocation)
    FilterTagFlowLayout filterLocation;


    private LayoutInflater mLayoutInflater;
    private Context context;
    private Unbinder unbinder;
    private OnCustomViewClickListener listener;
    private List<FilterTypeDetail> machineList = new ArrayList<>();
    private List<FilterTypeDetail> benefitList = new ArrayList<>();
    private List<FilterTypeDetail> supportList = new ArrayList<>();
    private List<FilterTypeDetail> realityList = new ArrayList<>();
    private List<FilterTypeDetail> trademarkList = new ArrayList<>();
    private List<FilterTypeDetail> commentList = new ArrayList<>();
    private List<FilterTypeDetail> ruleList = new ArrayList<>();
    private List<FilterTypeDetail> locationList = new ArrayList<>();

    public FilterMultiChooseView(Context context) {
        this(context, null);
    }

    public FilterMultiChooseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_filter_multi_choose, this, true);
        mLayoutInflater = LayoutInflater.from(context);
        unbinder = ButterKnife.bind(this, view);
        this.context = context;
        initView();
    }

    public void setListener(OnCustomViewClickListener listener) {
        this.listener = listener;
    }

    public void setVisibleView(View view, boolean visible) {
        view.setVisibility(visible ? VISIBLE : GONE);
    }

    public List<Integer> getCheckedView(AutoFlowLayout autoFlowLayout) {
        List<View> listView = autoFlowLayout.getCheckedViews();
        List<Integer> listPosition = new ArrayList<>();
        listPosition.clear();
        for (int i = 0; i < listView.size(); i++) {
            listPosition.add((Integer) listView.get(i).getTag());
        }
        return listPosition;
    }

    public void clearDataAll() {
        machineList.clear();
        benefitList.clear();
        supportList.clear();
        realityList.clear();
        trademarkList.clear();
        ruleList.clear();
        locationList.clear();
        commentList.clear();
    }

    public void setData(List<FilterTypeDetail> filterTypeDetailList) {
        clearDataAll();
        for (int i = 0; i < filterTypeDetailList.size(); i++) {
            switch (filterTypeDetailList.get(i).getType()) {
                case "machine":
                    machineList.add(filterTypeDetailList.get(i));
                    break;
                case "benefit":
                    benefitList.add(filterTypeDetailList.get(i));
                    break;
                case "support":
                    supportList.add(filterTypeDetailList.get(i));
                    break;
                case "reality":
                    realityList.add(filterTypeDetailList.get(i));
                    break;
                case "trademark":
                    trademarkList.add(filterTypeDetailList.get(i));
                    break;
                case "comment":
                    commentList.add(filterTypeDetailList.get(i));
                    break;
                case "rule":
                    ruleList.add(filterTypeDetailList.get(i));
                    break;
                case "location":
                    locationList.add(filterTypeDetailList.get(i));
                    break;
            }
        }
        filterMachine.setData(machineList);
        filterBenefit.setData(benefitList);
        filterSupport.setData(supportList);
        filterReality.setData(realityList);
        filterTrademark.setData(trademarkList);
        filterComment.setData(commentList);
        filterRule.setData(ruleList);
        filterLocation.setData(locationList);
    }


    private void initView() {
        nestedScrollView.setScrollBarSize(2);
        rootCustomView.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rootCustomView:

                break;
            case R.id.btnReset:
                resetAll();
                break;
            case R.id.btnConfirm:
                confirmFilter();
                listener.onClickCustomView(v, TYPE_CHOOSE_CLICK, -1);
                break;
        }
    }

    private void confirmFilter() {
//        Log.d("kiemtra", JSON.toJSONString(filterMachine.getCheckedInfoList()));
//        Log.d("kiemtra", JSON.toJSONString(filterBenefit.getCheckedInfoList()));
    }

    private void resetAll() {
        filterLocation.removeAllSelected();
        filterRule.removeAllSelected();
        filterComment.removeAllSelected();
        filterTrademark.removeAllSelected();
        filterReality.removeAllSelected();
        filterMachine.removeAllSelected();
        filterSupport.removeAllSelected();
        filterBenefit.removeAllSelected();
        edtRangePrice1.setText("");
        edtRangePrice2.setText("");
    }
}