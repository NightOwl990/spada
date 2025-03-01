package com.lgkk.spada.screen.service.fragment;


import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.base_project.widget.CustomFragmentHeader;
import com.lgkk.base_project.widget.FragmentPagerItem;
import com.lgkk.base_project.widget.FragmentPagerItemAdapter;
import com.lgkk.base_project.widget.FragmentPagerItems;
import com.lgkk.base_project.widget.GlideImageLoader;
import com.lgkk.spada.R;
import com.lgkk.spada.base.BaseSpadaFragment;
import com.lgkk.spada.base.ViewModelFactory;
import com.lgkk.spada.model.BannerDetail;
import com.lgkk.spada.model.community.post.PostService;
import com.lgkk.spada.model.service.ServiceCategory;
import com.lgkk.spada.model.service.ServiceUtility;
import com.lgkk.spada.model.shop.Category;
import com.lgkk.spada.screen.service.viewmodel.SuggestServiceViewModel;
import com.lgkk.spada.screen.service.activity.ServiceCategoryActivity;
import com.lgkk.spada.screen.service.adapter.ServiceCategoryAdapter;
import com.lgkk.spada.screen.service.adapter.ServiceUtilityAdapter;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.lgkk.base_project.banner.Banner;
import com.lgkk.base_project.banner.BannerConfig;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


public class SuggestServiceFragment extends BaseSpadaFragment implements OnItemRvClickListener<Object> {

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.rvCatService)
    RecyclerView rvCatService;
    @BindView(R.id.rvUtiService)
    RecyclerView rvUtiService;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.rootView)
    LinearLayout rootView;
    //    @BindView(R.id.nestedScrollView)
//    NestedScrollView nestedScrollView;
    @Inject
    ViewModelFactory viewModelFactory;
    @BindView(R.id.txtNameServ1)
    TextView txtNameServ1;
    @BindView(R.id.txtDescriptionServ1)
    TextView txtDescriptionServ1;
    @BindView(R.id.txtNameServ2)
    TextView txtNameServ2;
    @BindView(R.id.txtDescriptionServ2)
    TextView txtDescriptionServ2;
    @BindView(R.id.txtNameServ3)
    TextView txtNameServ3;
    @BindView(R.id.txtDescriptionServ3)
    TextView txtDescriptionServ3;
    @BindView(R.id.tabLayout)
    SmartTabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private SuggestServiceViewModel viewModel;
    private ServiceCategoryAdapter serviceCategoryAdapter;
    private ServiceUtilityAdapter serviceUtilityAdapter;
    //    private PostServiceBARowAdapter postServiceBARowAdapter;

    private List<ServiceUtility> serviceUtilityList = new ArrayList<>();
    private List<PostService> postDetailsList = new ArrayList<>();
    private List<BannerDetail> bannerList = new ArrayList<>();
    private List<ServiceCategory> serviceCategoryList = new ArrayList<>();

    protected FragmentPagerItemAdapter fragmentPagerItemAdapter;
    protected FragmentPagerItems.Creator creator;

    private String statusType;
    private static final String STATUS_TYPE = "status";

    public static Fragment newInstance(String getType) {
        Bundle bundle = new Bundle();
        bundle.putString(STATUS_TYPE, getType);
        Fragment fragment = new SuggestServiceFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_suggest_service;
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
    public void attachView() {

    }

    //    show loading screen ---------
    @Override
    public void initDatas() {
        appComponent().doInjection(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SuggestServiceViewModel.class);
        observableViewModel();

        showLoadingScreen(rootView);
        onRefreshing();
    }

    //    Setting view model ------
    private void observableViewModel() {
        viewModel.getCategoryServiceList().observe(this, listCat -> {
            showCategoryServiceList(listCat);
        });

        viewModel.getBannerDetailList().observe(this, listBanner -> {
            showBannerDetailList(listBanner);
        });

        viewModel.getServiceUtilityList().observe(this, listUtility -> {
            showUtilityServiceList(listUtility);
        });

        viewModel.getSuggestTabList().observe(this, listTab -> {
            showSuggestTabList(listTab);
        });


        viewModel.getError().observe(this, isError -> {
            if (isError != null && isError) {
                showError();
            }
        });
    }

    //    onRefreshing data ------------
    protected void onRefreshing() {
        if (isErrorData) {
            showLoadingScreen(rootView);
        }
        isRefreshing = true;
        viewModel.loadListBannerDetail(token, 0, 5);
        viewModel.loadListCategoryService(token, 0, 10);
        viewModel.loadListUtilityService(token, 0, 5);
        viewModel.loadListSuggestTab(token, 0, 5);
    }

    @Override
    public void configViews() {

        //        Setting RefreshLayout -----------------
        refreshLayout.setRefreshHeader(new CustomFragmentHeader(getContext()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(1000/*,false*/);
                onRefreshing();
                ;
            }
        });


//        Rv Category -----------------
        serviceCategoryAdapter = new ServiceCategoryAdapter(getActivity(), serviceCategoryList, this);
        GridLayoutManager gridCatLayoutManager = new GridLayoutManager(getContext(), 5);
        rvCatService.setLayoutManager(gridCatLayoutManager);
        rvCatService.setAdapter(serviceCategoryAdapter);

        serviceCategoryAdapter.setOnItemClickListener((adapter, view, position) -> ServiceCategoryActivity.startActivity(getContext(), "1"));

//        Rv Utility -----------------
        serviceUtilityAdapter = new ServiceUtilityAdapter(getActivity(), serviceUtilityList, this);
        LinearLayoutManager linearUtiLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvUtiService.setLayoutManager(linearUtiLayoutManager);
        rvUtiService.setAdapter(serviceUtilityAdapter);

    }

    public void showCategoryServiceList(List<ServiceCategory> serviceCategoryListResult) {
        serviceCategoryList.clear();
        serviceCategoryList.addAll(serviceCategoryListResult);
        serviceCategoryAdapter.notifyDataSetChanged();

        closeLoadingScreen();
    }

    public void showUtilityServiceList(List<ServiceUtility> serviceUtilityListResult) {
        serviceUtilityList.clear();
        serviceUtilityList.addAll(serviceUtilityListResult);
        serviceUtilityAdapter.notifyDataSetChanged();

        //        Close loading screen  ------------------
       closeLoadingScreen();
    }

    public void showSuggestTabList(List<Category> categoryList) {
        creator = FragmentPagerItems.with(getContext());

        for (int i = 0; i < categoryList.size(); i++) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("targetId", categoryList.get(i).getId());
            creator.add(FragmentPagerItem.of(categoryList.get(i).getName(), SuggestTabFragment.class, bundle));
        }

        fragmentPagerItemAdapter = new FragmentPagerItemAdapter(getChildFragmentManager(), creator.create());
        viewPager.setAdapter(fragmentPagerItemAdapter);
        viewPager.setOffscreenPageLimit(0);
        tabLayout.setViewPager(viewPager);

        closeLoadingScreen();
    }

    public void showBannerDetailList(List<BannerDetail> bannerDetailListResult) {
        bannerList.clear();
        bannerList.addAll(bannerDetailListResult);
        List<String> images = new ArrayList<>();
        for (int i = 0; i < bannerDetailListResult.size(); i++) {
            images.add(bannerDetailListResult.get(i).getImage());
        }
        banner.setImageLoader(new GlideImageLoader(getContext()));
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setImages(images);
        banner.isAutoPlay(true);
        banner.start();
        banner.setOnBannerListener(position -> {
//            switch (bannerList.get(position).getTargetType()) {
//                case "product":
//                    ProductDetailRatingActivity.startActivity(getContext(), bannerList.get(position).getTargetId());
//                    break;
//                case "subCategory":
//                    mPresenter.getSubCategoryDetailById(token, bannerList.get(position).getTargetId());
//                    break;
//                case "category":
//                    mPresenter.getCategoryDetailById(token, bannerList.get(position).getTargetId());
//                    break;
//            }
        });
        closeLoadingScreen();
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
    public void onItemRvClick(View view, Object item, int adapterPosition) {

    }
}

