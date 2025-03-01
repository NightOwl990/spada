package com.lgkk.spada.screen.home.activity;

import android.view.View;

import com.lgkk.base_project.base.listener.OnCustomViewClickListener;
import com.lgkk.base_project.base.listener.PopUpFilterTabClickListener;
import com.lgkk.spada.R;
import com.lgkk.spada.model.FilterTypeDetail;
import com.lgkk.spada.model.FilterTypeSecond;
import com.lgkk.spada.screen.home.widget.HospitalRvView;
import com.lgkk.spada.screen.service.activity.FilterServiceListActivity;
import com.lgkk.spada.screen.service.widget.filter.FilterCityView;
import com.lgkk.spada.screen.service.widget.filter.FilterMultiChooseView;
import com.lgkk.spada.screen.service.widget.filter.FilterPlaceView;
import com.lgkk.spada.screen.service.widget.filter.FilterProductView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterHospitalListHandlerActivity extends FilterHospitalListActivity implements PopUpFilterTabClickListener, OnCustomViewClickListener {

    protected FilterCityView filterCityView;
    protected FilterMultiChooseView filterMultiChooseView;
    protected FilterProductView filterProductView;
    protected FilterPlaceView filterPlaceView;

    protected String[] headers;
    protected List<FilterTypeDetail> multiFilterList = new ArrayList<>();
    protected List<FilterTypeDetail> productFilterList = new ArrayList<>();
    protected List<FilterTypeDetail> placeFilterList = new ArrayList<>();
    protected List<FilterTypeDetail> cityFilterList = new ArrayList<>();

    private int currentChoosePlace = -1;
    @Override
    protected void onRefreshing() {
        super.onRefreshing();
        viewModel.loadMultiTypeFilterList(token, targetId);
        viewModel.loadProductFilterList(token, targetId);
        viewModel.loadCityFilterList(token, targetId);
    }

    @Override
    public void configViews() {
        super.configViews();
        settingPopupView();

    }

    @Override
    protected void observableViewModel() {
        super.observableViewModel();

        viewModel.getMultiTypeFilterList().observe(this, filterTypeDetailList -> {
            showMultiTypeFilter(filterTypeDetailList);
        });

        viewModel.getProductFilterList().observe(this, productFilterList -> {
            showProductFilterList(productFilterList);
        });

        viewModel.getCityFilterList().observe(this, cityFilterList -> {
            showCityFilterList(cityFilterList);
        });

        viewModel.getPlaceFilterList().observe(this, placeFilterList -> {
            showPlaceFilterList(placeFilterList);
        });

    }

    private void showPlaceFilterList(List<FilterTypeDetail> placeFilterList) {
        this.placeFilterList.clear();
        this.placeFilterList.addAll(placeFilterList);
        filterPlaceView.setData(this.placeFilterList);
    }

    protected void showCityFilterList(List<FilterTypeDetail> cityFilterList) {
        this.cityFilterList.clear();
        this.cityFilterList.addAll(cityFilterList);
        filterCityView.setData(this.cityFilterList, 0);
    }

    protected void showProductFilterList(List<FilterTypeDetail> productFilterList) {
        this.productFilterList.clear();
        this.productFilterList.addAll(productFilterList);
        filterProductView.setData(this.productFilterList);
    }

    protected void showMultiTypeFilter(List<FilterTypeDetail> multiFilterList) {
        this.multiFilterList.clear();
        this.multiFilterList.addAll(multiFilterList);
        filterMultiChooseView.setData(this.multiFilterList);

        closeLoadingScreen();
    }


    protected void settingPopupView() {
        hospitalRvView = new HospitalRvView(this, hospitalDetailList);
        hospitalRvView.setListener(this);
        popupFilterMenu.setListener(this);
        headers = getResources().getStringArray(R.array.filter_tab_hospital);
        filterCityView = new FilterCityView(this);
        filterCityView.setListener(this);
        filterProductView = new FilterProductView(this);
        filterProductView.setListener(this);
//        filterLocationView = new FilterLocationView(this, Arrays.asList(headers));
        filterPlaceView = new FilterPlaceView(this);
        filterPlaceView.setListener(this);
        filterPlaceView.setEmptyPlace();
        filterMultiChooseView = new FilterMultiChooseView(this);
        filterMultiChooseView.setListener(this);

        popupViews.clear();
        popupViews.add(filterCityView);
//        popupViews.add(filterPlaceView);
        popupViews.add(filterProductView);
//        popupViews.add(filterMultiChooseView);
        popupFilterMenu.initPopupMenu(Arrays.asList(headers), popupViews, hospitalRvView);

    }


    @Override
    public void onTabClick(View v, int position, boolean isOpen) {
        if (position == 6 && isOpen) {

        }
    }

    @Override
    public void onClickCustomView(View v, String action, int position) {
        switch (action) {
            case FilterMultiChooseView.TYPE_CHOOSE_CLICK:
                popupFilterMenu.closeMenu();
                break;
            case FilterProductView.TYPE_CHOOSE_CLICK:
                popupFilterMenu.setTabText(productFilterList.get(position).getTagName());
                popupFilterMenu.closeMenu();
                break;
            case FilterPlaceView.TYPE_CHOOSE_CLICK:
                popupFilterMenu.setTabText(placeFilterList.get(position).getTagName());
                popupFilterMenu.closeMenu();
                break;
            case HospitalRvView.TYPE_REFRESH_DATA:
                onRefreshing();
                break;
        }
    }

    @Override
    public void onClickCustomView(View v, String action, int position, int position2) {
        switch (action) {
            case FilterCityView.TYPE_CHOOSE_CLICK:
                FilterTypeSecond filterTypeSecond = cityFilterList.get(position).getSecondTypeList().get(position2);
                popupFilterMenu.setTabText(filterTypeSecond.getTagName());
//                popupFilterMenu.setTabTextPosition(1, "Địa điểm");
                currentChoosePlace = -1;
//                viewModel.loadPlaceFilterList(token, filterTypeSecond.getId());
                popupFilterMenu.closeMenu();
                break;
        }
    }
}

