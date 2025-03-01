package com.lgkk.module_home.screen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.lifecycle.ViewModelProviders;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.base_project.utils.toasty.Toasty;
import com.lgkk.module_home.R;
import com.lgkk.module_home.base.BaseHomeSpadaActivity;
import com.lgkk.module_home.base.HomeViewModelFactory;
import com.lgkk.module_home.model.TestResult;
import com.lgkk.module_home.screen.viewmodel.TestViewModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.OnClick;

@Route(path = "/test/test")
public class TestActivity extends BaseHomeSpadaActivity implements OnItemRvClickListener<Object> {

    RelativeLayout rootView;

    @Inject
    HomeViewModelFactory viewModelFactory;
    private TestViewModel viewModel;

    private static final String SHOP_ID = "shopId";
    private String shopId;

    public static void startActivity(Context context, String shopId) {
        Intent intent = new Intent(context, TestActivity.class);
        intent.putExtra(SHOP_ID, shopId);
        context.startActivity(intent);
    }

    @Override
    protected void initDataGetFromIntent(Bundle savedInstanceState) {
        super.initDataGetFromIntent(savedInstanceState);
        if (savedInstanceState != null) {
            shopId = savedInstanceState.getString(SHOP_ID);
        } else {
            shopId = getIntent().getStringExtra(SHOP_ID);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

//    @OnClick(R.id.btnRestart)
//    void btnRestart() {
//        ARouter.getInstance().build("/main/main2").navigation();
//    }
    //    show loading screen ---------

    @Override
    public void initDatas() {
        homeComponent().doInjection(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TestViewModel.class);
        observableViewModel();
        rootView = findViewById(R.id.rootView);
        showLoadingScreen(rootView);
        onRefreshing();
    }

    //    Setting view model ------
    private void observableViewModel() {
        viewModel.getTestResultList().observe(this, result -> {
            showTestResultList(result);
        });

        viewModel.getError().observe(this, isError -> {
            if (isError != null && isError) {
                showError();
            }
        });
    }

    private void showTestResultList(List<TestResult> result) {
        Toasty.success(this, result.get(0).getQuestion(), 200).show();
        closeLoadingScreen();
    }

    //    onRefreshing data ------------

    @Override
    protected void onRefreshing() {
        if (isErrorData) {
            showLoadingScreen(rootView);
        }
        isRefreshing = true;

        viewModel.loadTestResultList();
    }

    @Override
    public void configViews() {

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
        int i = view.getId();
        if (i == R.id.page_tip_eventview) {
            onRefreshing();

        }
    }
}


