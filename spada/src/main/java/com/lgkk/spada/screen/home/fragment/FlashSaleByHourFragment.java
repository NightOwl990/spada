package com.lgkk.spada.screen.home.fragment;


import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.base_project.utils.CountdownTimerUtil;
import com.lgkk.base_project.widget.CustomFragmentHeader;
import com.lgkk.spada.R;
import com.lgkk.spada.base.BaseSpadaFragment;
import com.lgkk.spada.base.ViewModelFactory;
import com.lgkk.spada.model.shop.Product;
import com.lgkk.spada.screen.home.adapter.FlashSaleByHourAdapter;
import com.lgkk.spada.screen.home.viewmodel.FlashSaleViewModel;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.iwgang.countdownview.CountdownView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FlashSaleByHourFragment extends BaseSpadaFragment implements OnItemRvClickListener<Object> {

    @BindView(R.id.rootView)
    LinearLayout rootView;

    @Inject
    ViewModelFactory viewModelFactory;
    @BindView(R.id.rvFlashSaleByHour)
    RecyclerView rvFlashSaleByHour;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    @BindView(R.id.imgIcon)
    ImageView imgIcon;
    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.cdFlash)
    CountdownView cdFlash;
    @BindView(R.id.lnFlash)
    LinearLayout lnFlash;
    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;
    Unbinder unbinder1;
    private FlashSaleViewModel viewModel;

    protected FlashSaleByHourAdapter mAdapter;
    protected List<Product> flashSaleByHourList = new ArrayList<>();
    private String statusType;
    private static final String STATUS_TYPE = "status";

    public static Fragment newInstance(String getType) {
        Bundle bundle = new Bundle();
        bundle.putString(STATUS_TYPE, getType);
        Fragment fragment = new FlashSaleByHourFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initDataGetFromArgument(Bundle savedInstanceState) {
        super.initDataGetFromArgument(savedInstanceState);
        if (savedInstanceState != null) {
            statusType = savedInstanceState.getString(STATUS_TYPE);
        } else {
            statusType = getArguments().getString(STATUS_TYPE);
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_flash_sale_by_hour;
    }


    @Override
    public void attachView() {

    }

    //    show loading screen ---------
    @Override
    public void initDatas() {
        appComponent().doInjection(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(FlashSaleViewModel.class);
        observableViewModel();
        showLoadingScreen(rootView);
        onRefreshing();
    }

    //    Setting view model ------
    private void observableViewModel() {
        viewModel.getFlashSaleByHourList().observe(this, result -> {
            showFlashSaleByHourList(result);
        });

        viewModel.getError().observe(this, isError -> {
            if (isError != null && isError) {
                showError();
            }
        });
    }

    private void showFlashSaleByHourList(List<Product> result) {
        flashSaleByHourList.clear();
        flashSaleByHourList.addAll(result);
        mAdapter.notifyDataSetChanged();

        closeLoadingScreen();
    }

    //    onRefreshing data ------------
    protected void onRefreshing() {
        if (isErrorData) {
            showLoadingScreen(rootView);
        }
        isRefreshing = true;

        viewModel.loadFlashSaleByHourList(token, 123456789);
    }

    @Override
    public void configViews() {
        CountdownTimerUtil.setCountDownTimer(cdFlash, 16320000);

        refreshLayout.setRefreshHeader(new CustomFragmentHeader(getContext()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(1000/*,false*/);
                onRefreshing();
            }
        });

        mAdapter = new FlashSaleByHourAdapter(getActivity(), flashSaleByHourList);
        settingLinearRecyclerView(rvFlashSaleByHour, mAdapter, true, false);
    }

    @Override
    public void onItemRvClick(View view, Object item, int adapterPosition) {

    }


    //    show error screen -------------
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }
}


