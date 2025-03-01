package com.lgkk.spada.screen.service.fragment;


import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.spada.R;
import com.lgkk.spada.base.BaseSpadaFragment;
import com.lgkk.spada.base.ViewModelFactory;
import com.lgkk.spada.model.service.ServiceDetails;
import com.lgkk.spada.screen.service.viewmodel.SuggestTabViewModel;
import com.lgkk.spada.screen.service.activity.ServiceDetailActivity;
import com.lgkk.spada.screen.service.adapter.ServiceProductAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class ServiceCategoryProductFragment extends BaseSpadaFragment implements OnItemRvClickListener<Object> {

    @BindView(R.id.rootView)
    LinearLayout rootView;
    @BindView(R.id.rvProduct)
    RecyclerView rvProduct;

    private ServiceProductAdapter mAdapter;
    private List<ServiceDetails> serviceDetailList = new ArrayList<>();
    private List<String> locationList = Arrays.asList("Không giới hạn", "Vũ Hán", "Bắc Kinh", "Thượng Hải", "Thành Đô", "Quảng Châu", "Thâm Quyến", "Trùng Khánh", "Thiên Tân", "Tây An", "Nam Kinh", "Hàng Châu");
    private List<String> memoryList = Arrays.asList("Ghi nhơ A", "Ghi nhơ B", "Ghi nhơ C", "Ghi nhơ D");

    @Inject
    ViewModelFactory viewModelFactory;
    private SuggestTabViewModel viewModel;


    private String targetId = "";
    private static final String TARGET_ID = "targetId";

    public static Fragment newInstance(String targetId) {
        Bundle bundle = new Bundle();
        bundle.putString(TARGET_ID, targetId);
        Fragment fragment = new ServiceCategoryProductFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initDataGetFromArgument(Bundle savedInstanceState) {
        super.initDataGetFromArgument(savedInstanceState);
        if (savedInstanceState != null) {
            targetId = savedInstanceState.getString(TARGET_ID);
        } else {
            targetId = getArguments().getString(TARGET_ID);
        }
    }


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_service_category_product;
    }

    @Override
    public void attachView() {

    }

    //    show loading screen ---------
    @Override
    public void initDatas() {
        appComponent().doInjection(this);
        targetId = getArguments().getString(TARGET_ID);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SuggestTabViewModel.class);

        observableViewModel();
        showLoadingScreen(rootView);
        onRefreshing();
    }

    //    Setting view model ------
    private void observableViewModel() {
        viewModel.getListServiceDetailByCategoryId().observe(this, serviceList -> {
            showListServiceDetailByCategoryId(serviceList);
        });

        viewModel.getError().observe(this, isError -> {
            if (isError != null && isError) {
                showError();
            }
        });
    }

    private void showListServiceDetailByCategoryId(List<ServiceDetails> listService) {
        serviceDetailList.clear();
        serviceDetailList.addAll(listService);

        mAdapter.notifyDataSetChanged();

        closeLoadingScreen();
    }

    //    onRefreshing data ------------
    protected void onRefreshing() {
        if (isErrorData) {
            showLoadingScreen(rootView);
        }
        isRefreshing = true;
        viewModel.loadListServiceDetailByCategoryId(token, targetId, 0, 5);

    }

    @Override
    public void configViews() {
        String[] titleList = new String[]{"Địa điểm A", "Địa điểm B", "Địa điểm C"};
        mAdapter = new ServiceProductAdapter(getActivity(), serviceDetailList, this);
        settingLinearRecyclerView(rvProduct, mAdapter, true, true);
        mAdapter.setOnItemClickListener((adapter, view, position) -> ServiceDetailActivity.startActivity(getContext(), "1234"));
    }

    void onShowFilterLocation() {

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
}
