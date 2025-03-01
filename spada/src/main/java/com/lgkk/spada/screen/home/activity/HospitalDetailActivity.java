package com.lgkk.spada.screen.home.activity;

import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.spada.R;
import com.lgkk.spada.base.BaseSpadaActivity;
import com.lgkk.spada.base.ViewModelFactory;
import com.lgkk.spada.screen.home.viewmodel.ProductDetailViewModel;

import javax.inject.Inject;

import butterknife.BindView;

public class HospitalDetailActivity extends BaseSpadaActivity implements OnItemRvClickListener<Object> {

    @BindView(R.id.rootView)
    LinearLayout rootView;

    @Inject
    ViewModelFactory viewModelFactory;
    private ProductDetailViewModel viewModel;

    private static final String SHOP_ID = "shopId";
    private String shopId;

    public static void startActivity(Context context, String shopId) {
        Intent intent = new Intent(context, HospitalDetailActivity.class);
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
        return R.layout.activity_hospital_detail;
    }

    //    show loading screen ---------
    @Override
    public void initDatas() {
        appComponent().doInjection(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ProductDetailViewModel.class);
        observableViewModel();

        showLoadingScreen(rootView);
        onRefreshing();
    }

    //    Setting view model ------
    private void observableViewModel() {
        viewModel.getProductDetail().observe(this, result -> {
//            showProduct(result);
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

//        viewModel.loadProduct(token, targetId);
    }

    @Override
    public void configViews() {

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
}


