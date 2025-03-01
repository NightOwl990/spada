package com.lgkk.spada.screen.service.activity;

import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import android.view.View;
import android.widget.LinearLayout;

import com.lgkk.base_project.base.listener.OnFadeCommonToolbarListener;
import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.base_project.widget.CustomFragmentHeader;
import com.lgkk.spada.R;

import com.lgkk.spada.base.BaseSpadaActivity;
import com.lgkk.spada.base.ViewModelFactory;
import com.lgkk.spada.model.Doctor;
import com.lgkk.spada.screen.service.viewmodel.DoctorViewModel;
import com.lgkk.spada.widget.FadeCommonToolbar;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import javax.inject.Inject;

import butterknife.BindView;

public class DoctorInfoActivity extends BaseSpadaActivity implements OnItemRvClickListener<Object>, OnFadeCommonToolbarListener {

    @BindView(R.id.rootView)
    LinearLayout rootView;
    @BindView(R.id.fadeCommonToolbar)
    FadeCommonToolbar fadeCommonToolbar;
    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @Inject
    ViewModelFactory viewModelFactory;
    private DoctorViewModel viewModel;

    private static final String DOCTOR_ID = "doctorId";
    private String doctorId;
    protected Doctor doctorInfo;

    public static void startActivity(Context context, String shopId) {
        Intent intent = new Intent(context, DoctorInfoActivity.class);
        intent.putExtra(DOCTOR_ID, shopId);
        context.startActivity(intent);
    }

    @Override
    protected void initDataGetFromIntent(Bundle savedInstanceState) {
        super.initDataGetFromIntent(savedInstanceState);
        if (savedInstanceState != null) {
            doctorId = savedInstanceState.getString(DOCTOR_ID);
        } else {
            doctorId = getIntent().getStringExtra(DOCTOR_ID);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_doctor_info;
    }

    //    show loading screen ---------
    @Override
    public void initDatas() {
        appComponent().doInjection(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DoctorViewModel.class);
        initMarginTopToolbar(false);
        observableViewModel();

        showLoadingScreen(rootView);
        onRefreshing();
    }

    //    Setting view model ------
    private void observableViewModel() {
        viewModel.getDoctorInfo().observe(this, result -> {
            showDoctorInfo(result);
        });

        viewModel.getError().observe(this, isError -> {
            if (isError != null && isError) {
                showError();
            }
        });
    }

    private void showDoctorInfo(Doctor doctor) {
        this.doctorInfo = doctor;
    }


    //    onRefreshing data ------------

    @Override
    protected void onRefreshing() {
        if (isErrorData) {
            showLoadingScreen(rootView);
        }
        isRefreshing = true;

        viewModel.loadDoctorInfo(token, doctorId);
    }

    @Override
    public void configViews() {
        fadeCommonToolbar.setListener(this);
        nestedScrollView.getViewTreeObserver().addOnScrollChangedListener(() -> {
            int scrollY = nestedScrollView.getScrollY();
            fadeCommonToolbar.setAlphaToolbar(scrollY);
        });

        refreshLayout.setRefreshHeader(new CustomFragmentHeader(this));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(1000/*,false*/);
                onRefreshing();
            }
        });

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
       switch (action) {
           case FadeCommonToolbar.TYPE_LEFT_CLICK:
               onBackPressed();
           break;
           case FadeCommonToolbar.TYPE_MIDDLE_CLICK:

               break;
           case FadeCommonToolbar.TYPE_RIGHT_CLICK:
               onBackPressed();
               break;
       }
    }
}

