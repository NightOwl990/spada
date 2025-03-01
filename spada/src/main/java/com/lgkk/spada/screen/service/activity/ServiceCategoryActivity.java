package com.lgkk.spada.screen.service.activity;


import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.appbar.AppBarLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lgkk.base_project.base.listener.OnFadeCommonToolbarListener;
import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.base_project.utils.RxBus;
import com.lgkk.base_project.utils.ScreenUtil;
import com.lgkk.base_project.widget.CommunityFlycoTabLayoutAdapter;
import com.lgkk.base_project.widget.CustomFragmentHeader;
import com.lgkk.base_project.widget.FragmentPagerItem;
import com.lgkk.base_project.widget.FragmentPagerItemAdapter;
import com.lgkk.base_project.widget.FragmentPagerItems;
import com.lgkk.base_project.widget.GlideImageLoader;
import com.lgkk.base_project.widget.NonSwipeableViewPager;
import com.lgkk.spada.R;
import com.lgkk.spada.base.BaseSpadaActivity;
import com.lgkk.spada.base.ViewModelFactory;
import com.lgkk.spada.event.ScrollFragmentToTopEvent;
import com.lgkk.spada.model.BannerDetail;
import com.lgkk.spada.model.Doctor;
import com.lgkk.spada.model.community.post.PostServiceMostView;
import com.lgkk.spada.model.service.FirstServiceCategory;
import com.lgkk.spada.model.service.MainServiceCategory;
import com.lgkk.spada.model.service.SecondServiceCategory;
import com.lgkk.spada.screen.service.adapter.FirstOptionCategoryAdapter;
import com.lgkk.spada.screen.service.adapter.MainOptionCategoryAdapter;
import com.lgkk.spada.screen.service.adapter.PostServiceBAGridAdapter;
import com.lgkk.spada.screen.service.adapter.SecondOptionCategoryAdapter;
import com.lgkk.spada.screen.service.adapter.ServiceDoctorAdapter;
import com.lgkk.spada.screen.service.fragment.ServiceCategoryDailyFragment;
import com.lgkk.spada.screen.service.fragment.ServiceCategoryProductFragment;
import com.lgkk.spada.screen.service.viewmodel.ServiceViewModel;
import com.lgkk.spada.widget.FadeSearchToolbar;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.lgkk.base_project.banner.Banner;
import com.lgkk.base_project.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class ServiceCategoryActivity extends BaseSpadaActivity implements OnItemRvClickListener<Object>, OnFadeCommonToolbarListener {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.rvFirst)
    RecyclerView rvFirst;
    @BindView(R.id.rvSecond)
    RecyclerView rvSecond;
    @BindView(R.id.rvMain)
    RecyclerView rvMain;
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
    @BindView(R.id.txtMostView)
    TextView txtMostView;
    @BindView(R.id.txtMore)
    TextView txtMore;
    @BindView(R.id.imgMore)
    ImageView imgMore;
    @BindView(R.id.lnMore)
    LinearLayout lnMore;
    @BindView(R.id.rlMostView)
    RelativeLayout rlMostView;
    @BindView(R.id.rvMostView)
    RecyclerView rvMostView;
    @BindView(R.id.lnMostView)
    LinearLayout lnMostView;
    @BindView(R.id.txtTitleDoctor)
    TextView txtTitleDoctor;
    @BindView(R.id.txtMoreDoc)
    TextView txtMoreDoc;
    @BindView(R.id.imgMoreDoc)
    ImageView imgMoreDoc;
    @BindView(R.id.lnMoreDoc)
    LinearLayout lnMoreDoc;
    @BindView(R.id.rlDoctor)
    RelativeLayout rlDoctor;
    @BindView(R.id.rvDoctor)
    RecyclerView rvDoctor;
    @BindView(R.id.rootView)
    LinearLayout rootView;
    @BindView(R.id.tabLayout)
    SmartTabLayout tabLayout;
    @BindView(R.id.viewPager)
    NonSwipeableViewPager viewPager;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.searchToolbar)
    FadeSearchToolbar searchToolbar;


    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.placeholderToolbar)
    View placeholderToolbar;
    @BindView(R.id.mainView)
    RelativeLayout mainView;

    private FirstOptionCategoryAdapter firstAdapter;
    private SecondOptionCategoryAdapter secondAdapter;
    private MainOptionCategoryAdapter mainAdapter;
    private PostServiceBAGridAdapter mostViewAdapter;
    private ServiceDoctorAdapter doctorAdapter;

    private List<FirstServiceCategory> firstList = new ArrayList<>();
    private List<SecondServiceCategory> secondList = new ArrayList<>();
    private List<MainServiceCategory> mainList = new ArrayList<>();
    private List<BannerDetail> bannerList = new ArrayList<>();
    private List<PostServiceMostView> mostViewList = new ArrayList<>();
    private List<Doctor> doctorList = new ArrayList<>();

    private static final String SERVICE_ID = "serviceId";
    private String serviceId;

    CommunityFlycoTabLayoutAdapter communityFlycoTabLayoutAdapter;

    protected FragmentPagerItemAdapter fragmentPagerItemAdapter;
    protected FragmentPagerItems.Creator creator;
    protected String[] mTitles;

    @Inject
    ViewModelFactory viewModelFactory;
    private ServiceViewModel viewModel;
    
    @Override
    public int getLayoutId() {
        return R.layout.activity_service_category;
    }

    public static void startActivity(Context context, String shopId) {
        Intent intent = new Intent(context, ServiceCategoryActivity.class);
        intent.putExtra(SERVICE_ID, shopId);
        context.startActivity(intent);
    }


    @Override
    protected void initDataGetFromIntent(Bundle savedInstanceState) {
        super.initDataGetFromIntent(savedInstanceState);
        if (savedInstanceState != null) {
            serviceId = savedInstanceState.getString(SERVICE_ID);
        } else {
            serviceId = getIntent().getStringExtra(SERVICE_ID);
        }
    }

    //    show loading screen ---------
    @Override
    public void initDatas() {
        appComponent().doInjection(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ServiceViewModel.class);
        initMarginTopToolbar(false);
        observableViewModel();
        showLoadingScreen(rootView);
        onRefreshing();
    }

    private void observableViewModel() {
        viewModel.getFirstListServiceByServId().observe(this, result -> {
            showFirstListServiceByServId(result);
        });
        viewModel.getDoctorListByServId().observe(this, result -> {
            showDoctorListByServId(result);
        });
        viewModel.getListBannerByServId().observe(this, result -> {
            showListBannerByServId(result);
        });
        viewModel.getMainListServiceByServId().observe(this, result -> {
            showMainListServiceByServId(result);
        });
        viewModel.getMostViewListPostByServId().observe(this, result -> {
            showMostViewListPostByServId(result);
        });
        viewModel.getSecondListServiceByServId().observe(this, result -> {
            showSecondListServiceByServId(result);
        });

        viewModel.getError().observe(this, isError -> {
            if (isError != null && isError) {
                showError();
            }
        });

    }

    private void showDoctorListByServId(List<Doctor> result) {
        doctorList.clear();
        doctorList.addAll(result);
        doctorAdapter.notifyDataSetChanged();
    }

    public void showFirstListServiceByServId(List<FirstServiceCategory> firstListResult) {
        firstList.clear();
        firstList.addAll(firstListResult);
        firstAdapter.notifyDataSetChanged();

        //   Close loading screen ------------------
        closeLoadingScreen();
    }

    public void showSecondListServiceByServId(List<SecondServiceCategory> secondListResult) {
        secondList.clear();
        secondList.addAll(secondListResult);
        secondAdapter.notifyDataSetChanged();
    }

    public void showMainListServiceByServId(List<MainServiceCategory> mainListResult) {
        mainList.clear();
        mainList.addAll(mainListResult);
        mainAdapter.notifyDataSetChanged();
    }

    public void showListBannerByServId(List<BannerDetail> bannerDetailListResult) {
        bannerList.clear();
        bannerList.addAll(bannerDetailListResult);
        List<String> images = new ArrayList<>();
        for (int i = 0; i < bannerDetailListResult.size(); i++) {
            images.add(bannerDetailListResult.get(i).getImage());
        }
        banner.setImageLoader(new GlideImageLoader(this));
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        banner.setImages(images);
        banner.isAutoPlay(true);
        banner.start();
        banner.setOnBannerListener(position -> {
//            switch (bannerList.get(position).getTargetType()) {
//                case "product":
//                    ProductDetailRatingActivity.startActivity(mContext, bannerList.get(position).getTargetId());
//                    break;
//                case "subCategory":
//                    viewModel.loadSubCategoryDetailById(token, bannerList.get(position).getTargetId());
//                    break;
//                case "category":
//                    viewModel.loadCategoryDetailById(token, bannerList.get(position).getTargetId());
//                    break;
//            }
        });

    }

    public void showMostViewListPostByServId(List<PostServiceMostView> postListResult) {
        mostViewList.clear();
        mostViewList.addAll(postListResult);
        mostViewAdapter.notifyDataSetChanged();
    }

    //    onRefreshing data ------------

    @Override
    protected void onRefreshing() {
        if (isErrorData) {
            showLoadingScreen(rootView);
        }

        viewModel.loadFirstListServiceByServId(token, serviceId, 0, 6);
        viewModel.loadSecondListServiceByServId(token, serviceId, 0, 6);
        viewModel.loadMainListServiceByServId(token, serviceId, 0, 6);
        viewModel.loadListBannerByServId(token, serviceId, 0, 4);
        viewModel.loadMostViewListPostByServId(token, serviceId, 0, 4);
        viewModel.loadDoctorListByServId(token, serviceId, 0, 4);
    }

    //    show error screen -------------
    @Override
    public void showError() {
        isErrorData = true;
        showErrorScreen(rootView);

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
    public void configViews() {
        initRxBusListener();
        placeholderToolbar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtil.getStatusHeight(this)));
        searchToolbar.setListener(this);
        searchToolbar.setAmountCart(0);

        appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            int scrollY = Math.abs(verticalOffset);
            searchToolbar.setAlphaToolbar(scrollY);
        });

//        setting tablayout -----------

       settingTabLayout();

        closeLoadingScreen();

//        Setting RefreshLayout -----------------
        refreshLayout.setRefreshHeader(new CustomFragmentHeader(this));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(1000/*,false*/);
                ;
            }
        });

//        First rv ---------------------
        firstAdapter = new FirstOptionCategoryAdapter(this, firstList, this);
        initSettingGridRv(rvFirst, firstAdapter, 3, true, false);
        firstAdapter.setOnItemClickListener((adapter, view, position) -> {
            ServiceDetailActivity.startActivity(this, "123");
        });
        //        Second rv ---------------------
        secondAdapter = new SecondOptionCategoryAdapter(this, secondList, this);
        initSettingGridRv(rvSecond, secondAdapter, 3, true, false);

        //        Main rv ---------------------
        mainAdapter = new MainOptionCategoryAdapter(this, mainList, this);
        initSettingGridRv(rvMain, mainAdapter, 5, true, false);

        //        MostView rv ---------------------
        mostViewAdapter = new PostServiceBAGridAdapter(this, mostViewList, this);
        initSettingGridRv(rvMostView, mostViewAdapter, 2, true, false);

        doctorList.add(new Doctor());
        doctorList.add(new Doctor());
        doctorList.add(new Doctor());



        //        Doctor rv ---------------------
        doctorAdapter = new ServiceDoctorAdapter(this, doctorList, this);
        initSettingLinearHorizontalRv(rvDoctor, doctorAdapter,  true, false);

    }

    private void settingTabLayout() {
        creator = FragmentPagerItems.with(this);
        mTitles = getResources().getStringArray(R.array.service_category_tablayout);


        for (int i = 0; i < mTitles.length; i++) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("targetId", serviceId);
            creator.add(FragmentPagerItem.of(mTitles[i], i == 0 ? ServiceCategoryProductFragment.class : ServiceCategoryDailyFragment.class, bundle));

        }

        fragmentPagerItemAdapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), creator.create());
        viewPager.setAdapter(fragmentPagerItemAdapter);
        viewPager.disableScroll(true);
        viewPager.setOffscreenPageLimit(0);
        tabLayout.setViewPager(viewPager);
    }

    private void initRxBusListener() {
        Disposable disposable = RxBus.getInstance()
                .toObservable(ScrollFragmentToTopEvent.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (event) -> {
//                            coordinatorLayout.scrollTo(1000,1000);

                        }
                );
        addDisposable(disposable);
    }


    @Override
    public void onItemRvClick(View view, Object item, int adapterPosition) {

    }

    @Override
    public void complete() {
    }


    @Override
    public void onClickedToolbar(View v, int action) {
        switch (action) {
            case FadeSearchToolbar.TYPE_LEFT_CLICK:
                onBackPressed();
                break;
            case FadeSearchToolbar.TYPE_RIGHT_CLICK:
//                CartActivity.startActivity(this, "123");
                break;
            case FadeSearchToolbar.TYPE_SEARCH_CLICK:
//                ShopSearchActivity.startActivity(this, "123");
                break;
        }
    }
}


