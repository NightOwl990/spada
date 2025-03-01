package com.lgkk.spada.screen.service.activity;


import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.lgkk.base_project.base.listener.OnFadeCommonToolbarListener;
import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.spada.R;
import com.lgkk.spada.base.BaseSpadaActivity;
import com.lgkk.spada.base.ViewModelFactory;
import com.lgkk.spada.model.service.ServiceDetails;
import com.lgkk.spada.screen.service.adapter.ServiceProductAdapter;
import com.lgkk.spada.screen.service.viewmodel.FilterViewModel;
import com.lgkk.spada.screen.service.widget.CategoryProductRvView;
import com.lgkk.spada.screen.service.widget.FilterToolbar;
import com.lgkk.spada.screen.service.widget.PopupFilterMenu;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class FilterServiceListActivity extends BaseSpadaActivity implements OnItemRvClickListener<Object>, OnFadeCommonToolbarListener {

    @BindView(R.id.rootView)
    LinearLayout rootView;

    @BindView(R.id.filterToolbar)
    FilterToolbar filterToolbar;
    @BindView(R.id.popupFilterMenu)
    PopupFilterMenu popupFilterMenu;

    protected List<View> popupViews = new ArrayList<>();

    @Inject
    ViewModelFactory viewModelFactory;
    protected FilterViewModel viewModel;
    protected CategoryProductRvView categoryProductRvView;


    protected ServiceProductAdapter mAdapter;
    protected List<ServiceDetails> serviceDetailList = new ArrayList<>();


    protected static final String TARGET_ID = "targetId";
    protected String targetId;

    public static void startActivity(Context context, String targetId) {
        Intent intent = new Intent(context, FilterServiceListHandlerActivity.class);
        intent.putExtra(TARGET_ID, targetId);
        context.startActivity(intent);
    }

    @Override
    protected void initDataGetFromIntent(Bundle savedInstanceState) {
        super.initDataGetFromIntent(savedInstanceState);
        if (savedInstanceState != null) {
            targetId = savedInstanceState.getString(TARGET_ID);
        } else {
            targetId = getIntent().getStringExtra(TARGET_ID);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_filter_service_list;
    }

    //    show loading screen ---------
    @Override
    public void initDatas() {
        appComponent().doInjection(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(FilterViewModel.class);
        initMarginTopToolbar(false);
        observableViewModel();
        showLoadingScreen(rootView);
        onRefreshing();
    }

    //    Setting view model ------
    protected void observableViewModel() {
        viewModel.getListServiceDetailByCategoryId().observe(this, serviceList -> {
            showListServiceDetailByCategoryId(serviceList);
        });

        viewModel.getError().observe(this, isError -> {
            if (isError != null && isError) {
                showError();
            }
        });
    }

    //    onRefreshing data ------------

    @Override
    protected void onRefreshing() {
        if (isErrorData) {
            showLoadingScreen(rootView);
        }
        isRefreshing = true;
        viewModel.loadListServiceDetailByCategoryId(token, targetId, 0, 5);
//        viewModel.loadProduct(token, targetId);
    }

    @Override
    public void configViews() {
        filterToolbar.setListener(this);
    }


    protected void showListServiceDetailByCategoryId(List<ServiceDetails> listService) {
        serviceDetailList.clear();
        serviceDetailList.addAll(listService);
        categoryProductRvView.setDataProduct(listService);
        closeLoadingScreen();
    }




    @Override
    public void onItemRvClick(View view, Object item, int adapterPosition) {

    }

    @Override
    public void complete() {
    }

    //    show error screen -------------
    @Override
    public void showError() {
        if (isRefreshing) {
            isErrorData = true;
            showErrorScreen(rootView);
        }
    }

    //    click loading screen -----------
    @Override
    public void onSkeletonViewClick(View view) {
        switch (view.getId()) {
            case R.id.page_tip_eventview:
                onRefreshing();
                break;
        }
    }

    @Override
    public void onClickedToolbar(View v, int action) {
        popupFilterMenu.closeMenu();
        switch (action) {
            case FilterToolbar.TYPE_LEFT_CLICK:
                onBackPressed();
                break;
        }
    }
}


