package com.lgkk.spada.screen.home.activity;

import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.lgkk.base_project.base.listener.OnFadeCommonToolbarListener;
import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.base_project.widget.CustomFragmentHeader;
import com.lgkk.spada.R;
import com.lgkk.spada.base.BaseSpadaActivity;
import com.lgkk.spada.base.ViewModelFactory;
import com.lgkk.spada.model.HospitalDetail;
import com.lgkk.spada.screen.home.widget.HospitalRvView;
import com.lgkk.spada.screen.service.adapter.ServiceProductAdapter;
import com.lgkk.spada.screen.service.viewmodel.FilterViewModel;
import com.lgkk.spada.screen.service.widget.FilterToolbar;
import com.lgkk.spada.screen.service.widget.PopupFilterMenu;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class FilterHospitalListActivity extends BaseSpadaActivity implements OnItemRvClickListener<Object>, OnFadeCommonToolbarListener {

    @BindView(R.id.rootView)
    LinearLayout rootView;

    @BindView(R.id.filterToolbar)
    FilterToolbar filterToolbar;
    @BindView(R.id.popupFilterMenu)
    PopupFilterMenu popupFilterMenu;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    protected List<View> popupViews = new ArrayList<>();

    @Inject
    ViewModelFactory viewModelFactory;
    protected FilterViewModel viewModel;
    protected HospitalRvView hospitalRvView;


    protected ServiceProductAdapter mAdapter;
    protected List<HospitalDetail> hospitalDetailList = new ArrayList<>();


    protected static final String TARGET_ID = "targetId";
    protected String targetId;


    public static void startActivity(Context context, String targetId) {
        Intent intent = new Intent(context, FilterHospitalListHandlerActivity.class);
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
        return R.layout.activity_filter_hospital_list;
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
        viewModel.getListHospitalDetailByCategoryId().observe(this, result -> {
            showListHospitalDetailByCategoryId(result);
        });

        viewModel.getError().observe(this, isError -> {
            if (isError != null && isError) {
                showError();
            }
        });
    }

    private void showListHospitalDetailByCategoryId(List<HospitalDetail> result) {
        hospitalDetailList.clear();
        hospitalDetailList.addAll(result);
        hospitalRvView.setDataProduct(result);
        closeLoadingScreen();
    }

    //    onRefreshing data ------------

    @Override
    protected void onRefreshing() {
        if (isErrorData) {
            showLoadingScreen(rootView);
        }
        isRefreshing = true;
        viewModel.loadListHospitalDetailByCategoryId(token, targetId, 0, 5);
//        viewModel.loadProduct(token, targetId);
    }

    @Override
    public void configViews() {
        refreshLayout.setRefreshHeader(new CustomFragmentHeader(this));
        refreshLayout.setOnRefreshListener(refreshLayout -> {
            refreshLayout.finishRefresh(1000/*,false*/);
            onRefreshing();
        });

        filterToolbar.setListener(this);
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


