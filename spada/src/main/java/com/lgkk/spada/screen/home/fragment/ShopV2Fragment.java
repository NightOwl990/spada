package com.lgkk.spada.screen.home.fragment;


import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import com.google.android.material.appbar.AppBarLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lgkk.base_project.base.listener.OnFadeCommonToolbarListener;
import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.base_project.utils.CountdownTimerUtil;
import com.lgkk.base_project.utils.MagicIndicatorUtil;
import com.lgkk.base_project.utils.ScreenUtil;
import com.lgkk.base_project.widget.CustomFragmentHeader;
import com.lgkk.base_project.widget.FragmentPagerItem;
import com.lgkk.base_project.widget.FragmentPagerItemAdapter;
import com.lgkk.base_project.widget.FragmentPagerItems;
import com.lgkk.base_project.widget.GlideImageLoader;
import com.lgkk.base_project.widget.magicindicator.MagicIndicator;
import com.lgkk.base_project.widget.roundedimageview.RoundedImageView;
import com.lgkk.base_project.widget.viewflipper.VerticalScrollLayout;
import com.lgkk.spada.R;
import com.lgkk.spada.base.BaseSpadaFragment;
import com.lgkk.spada.base.ViewModelFactory;
import com.lgkk.spada.model.BannerDetail;
import com.lgkk.spada.model.FlashCategory;
import com.lgkk.spada.model.HotSpaDetail;
import com.lgkk.spada.model.RankingDetail;
import com.lgkk.spada.model.RecommendCity;
import com.lgkk.spada.model.service.ServiceCategory;
import com.lgkk.spada.model.shop.Product;
import com.lgkk.spada.screen.home.activity.FlashSaleActivity;
import com.lgkk.spada.screen.home.adapter.FlashCategoryAdapter;
import com.lgkk.spada.screen.home.adapter.FlipperHotProductAdapter;
import com.lgkk.spada.screen.home.adapter.HotSpaAdapter;
import com.lgkk.spada.screen.home.adapter.ShopRankAdapter;
import com.lgkk.spada.screen.home.adapter.ShopRecommendCityAdapter;
import com.lgkk.spada.screen.home.viewmodel.ShopViewModel;
import com.lgkk.spada.screen.home.widget.AmoyTicketView;
import com.lgkk.spada.screen.home.widget.BrowseEntity;
import com.lgkk.spada.screen.home.widget.FadeShopToolbar;
import com.lgkk.spada.screen.home.widget.OnHeaderRefreshListener;
import com.lgkk.spada.screen.service.activity.ServiceCategoryActivity;
import com.lgkk.spada.screen.service.adapter.ServiceCategoryAdapter;
import com.lgkk.spada.screen.service.fragment.ServiceCategoryDailyFragment;
import com.lgkk.spada.screen.service.fragment.ServiceCategoryProductFragment;
import com.lgkk.base_project.banner.Banner;
import com.lgkk.base_project.banner.BannerConfig;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshHeader;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import cn.iwgang.countdownview.CountdownView;

public class ShopV2Fragment extends BaseSpadaFragment implements OnItemRvClickListener<Object>, OnHeaderRefreshListener, OnFadeCommonToolbarListener {

    protected FlashCategoryAdapter flashCategoryAdapter;
    protected ShopRankAdapter rankAdapter;
    protected HotSpaAdapter hotSpaAdapter;
    protected ServiceCategoryAdapter serviceCategoryAdapter;
    protected ShopRecommendCityAdapter recommendCityAdapter;
    protected FlipperHotProductAdapter hotProductAdapter;
    protected List<FlashCategory> flashCategoryList = new ArrayList<>();
    protected List<HotSpaDetail> hotSpaDetailList = new ArrayList<>();
    protected List<RankingDetail> rankingList = new ArrayList<>();
    protected List<BannerDetail> bannerList = new ArrayList<>();
    protected List<ServiceCategory> serviceCategoryList = new ArrayList<>();
    protected List<RecommendCity> recommendCityList = new ArrayList<>();
    protected List<Product> hotProductList = new ArrayList<>();

    @Inject
    ViewModelFactory viewModelFactory;
    protected ShopViewModel viewModel;
    ArrayList<BrowseEntity> browseEntityList;
    protected FragmentPagerItemAdapter fragmentPagerItemAdapter;

    @BindView(R.id.imgTop)
    ImageView imgTop;
    @BindView(R.id.head_bubbleView)
    AmoyTicketView headBubbleView;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.rl_browse)
    FrameLayout rlBrowse;
    @BindView(R.id.rvCatService)
    RecyclerView rvCatService;
    @BindView(R.id.tv_show_label)
    TextView tvShowLabel;
    @BindView(R.id.btn_label_more)
    ImageButton btnLabelMore;
    @BindView(R.id.fl_label_more)
    LinearLayout flLabelMore;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.dividerTop)
    View dividerTop;
    @BindView(R.id.dividerBot)
    View dividerBot;
    @BindView(R.id.imgIcon)
    ImageView imgIcon;
    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.cdFlash)
    CountdownView cdFlash;
    @BindView(R.id.lnFlash)
    LinearLayout lnFlash;
    @BindView(R.id.txtDescription)
    TextView txtDescription;
    @BindView(R.id.txtDiscount)
    TextView txtDiscount;
    @BindView(R.id.imgFlash)
    ImageView imgFlash;
    @BindView(R.id.rvFlash)
    RecyclerView rvFlash;
    @BindView(R.id.commercial_line)
    View commercialLine;
    @BindView(R.id.hot_hospitals_title)
    TextView hotHospitalsTitle;
    @BindView(R.id.browse_filter)
    TextView browseFilter;
    @BindView(R.id.address_filter)
    TextView addressFilter;
    @BindView(R.id.ll_hot_hospitals)
    LinearLayout llHotHospitals;
    @BindView(R.id.rvHotSpa)
    RecyclerView rvHotSpa;
    @BindView(R.id.hot_hospitals_line)
    View hotHospitalsLine;
    @BindView(R.id.billboard_title)
    TextView billboardTitle;
    @BindView(R.id.rvRank)
    RecyclerView rvRank;
    @BindView(R.id.im_top_doc_one)
    RoundedImageView imTopDocOne;
    @BindView(R.id.im_top_doc_two)
    RoundedImageView imTopDocTwo;
    @BindView(R.id.billboard_bottom)
    LinearLayout billboardBottom;
    @BindView(R.id.recommend_city_line)
    View recommendCityLine;
    @BindView(R.id.recommend_city_title)
    TextView recommendCityTitle;
    @BindView(R.id.rvRecommendCity)
    RecyclerView rvRecommendCity;
    @BindView(R.id.feed_title)
    TextView feedTitle;
    @BindView(R.id.rootView)
    LinearLayout rootView;
    @BindView(R.id.placeholderToolbar)
    View placeholderToolbar;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.fadeShopToolbar)
    FadeShopToolbar fadeShopToolbar;

    @BindView(R.id.mainView)
    RelativeLayout mainView;
    @BindView(R.id.magicTabLayout)
    MagicIndicator magicTabLayout;
    @BindView(R.id.scrollLayout)
    VerticalScrollLayout scrollLayout;
    @BindView(R.id.rlFlashSale)
    RelativeLayout rlFlashSale;
    @BindView(R.id.imgHotSaleTitle)
    ImageView imgHotSaleTitle;
    @BindView(R.id.txtHotSaleTitle)
    TextView txtHotSaleTitle;
    @BindView(R.id.lnHotSale)
    LinearLayout lnHotSale;
    @BindView(R.id.rlHotFlipperProduct)
    RelativeLayout rlHotFlipperProduct;


    private FragmentPagerItems.Creator creator;
    private String[] mTitles;
    private List<String> titleList;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_shop_v2;
    }

    @Override
    public void attachView() {

    }

    //    show loading screen ---------
    @Override
    public void initDatas() {
        appComponent().doInjection(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ShopViewModel.class);
        observableViewModel();
        showLoadingScreen(rootView);
        onRefreshing();
    }

    //    Setting view model ------
    private void observableViewModel() {
        viewModel.getFlashCategoryList().observe(this, result -> {
            showFlashCategoryList(result);
        });

        viewModel.getRankingDetailList().observe(this, result -> {
            showRankingDetailList(result);
        });

        viewModel.getCategoryServiceList().observe(this, result -> {
            showCategoryServiceList(result);
        });

        viewModel.getBannerDetailList().observe(this, result -> {
            showBannerDetailList(result);
        });

        viewModel.getShopRecommendCityList().observe(this, result -> {
            showShopRecommendCityList(result);
        });

        viewModel.getHotFlipperProductList().observe(this, result -> {
            showHotFlipperProductList(result);
        });


        viewModel.getError().observe(this, isError -> {
            if (isError != null && isError) {
                showError();
            }
        });
    }

    private void showHotFlipperProductList(List<Product> result) {
        hotProductList.clear();
        hotProductList.addAll(result);
        settingHotProductScrollList();
    }

    private void showShopRecommendCityList(List<RecommendCity> listRecommendCity) {
        recommendCityList.clear();
        recommendCityList.addAll(listRecommendCity);
        recommendCityAdapter.notifyDataSetChanged();
    }


    private void showCategoryServiceList(List<ServiceCategory> listCat) {
        serviceCategoryList.clear();
        serviceCategoryList.addAll(listCat);
        serviceCategoryAdapter.notifyDataSetChanged();
    }

    private void showRankingDetailList(List<RankingDetail> rankingList) {
        this.rankingList.clear();
        this.rankingList.addAll(rankingList);
        rankAdapter.notifyDataSetChanged();
        closeLoadingScreen();
    }


    private void showFlashCategoryList(List<FlashCategory> flashResultList) {
        flashCategoryList.clear();
        flashCategoryList.addAll(flashResultList);
        flashCategoryAdapter.notifyDataSetChanged();

    }


    private void settingHotProductScrollList() {
        hotProductAdapter = new FlipperHotProductAdapter(hotProductList, getContext(), this);
        scrollLayout.setAdapter(hotProductAdapter);
    }


//    private void settingHotProductBanner() {
//        hotProductBanner
//                .bindView((view, data, position) -> {
////                        ImageView imageView = view.findViewById(R.id.imageView);
////                        TextView textView = view.findViewById(R.id.title);
////                        BanneModel entity = (BanneModel) data;
////
////                        textView.setText(entity.getTitle());
////                        Glide.with(view.getContext())
////                                .load(entity.getUrl())
////                                .into(imageView);
//                })
//                .createView((context, parent, viewType) ->
//                        LayoutInflater.from(context).inflate(R.layout.item_flash_sale, parent, false))
//                .setOnClickBannerListener((view, data, position) -> Toast.makeText(getContext(), ((FlashCategory) data).getTitle(), Toast.LENGTH_SHORT).show())
//                .execute(flashCategoryList);
//    }


    private void showBannerDetailList(List<BannerDetail> listBanner) {
        bannerList.clear();
        bannerList.addAll(listBanner);
        List<String> images = new ArrayList<>();
        for (int i = 0; i < listBanner.size(); i++) {
            images.add(listBanner.get(i).getImage());
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
    }

    @OnClick(R.id.rlFlashSale)
    void onBack() {
        FlashSaleActivity.startActivity(getContext(), "123");
    }


    //    onRefreshing data ------------
    protected void onRefreshing() {
        if (isErrorData) {
            showLoadingScreen(rootView);
        }
        isRefreshing = true;

        viewModel.loadFlashCategoryList(token);
        viewModel.loadRankingDetailList(token);
        viewModel.loadBannerDetailList(token, 0, 5);
        viewModel.loadCategoryServiceList(token, 0, 5);
        viewModel.loadShopRecommendCityList(token, 0, 4);
        viewModel.loadHotFlipperProductList(token, 0, 5);
    }

    @Override
    public void configViews() {
        CountdownTimerUtil.setCountDownTimer(cdFlash, 16320000);
        settingTabLayout();

        browseEntityList = new ArrayList<>();
        browseEntityList.add(new BrowseEntity("https://i.a4vn.com/2018/11/27/tong-hop-hinh-anh-cac-hotgirl-xinh-hot-nhat-nam-2018-7b8685.jpg", "1"));
        browseEntityList.add(new BrowseEntity("https://i.a4vn.com/2018/11/27/tong-hop-hinh-anh-cac-hotgirl-xinh-hot-nhat-nam-2018-0ddb27.jpg", "1"));
        browseEntityList.add(new BrowseEntity("https://i.a4vn.com/2018/11/27/tong-hop-hinh-anh-cac-hotgirl-xinh-hot-nhat-nam-2018-9fa595.jpg", "1"));
        browseEntityList.add(new BrowseEntity("https://i.a4vn.com/2018/11/27/tong-hop-hinh-anh-cac-hotgirl-xinh-hot-nhat-nam-2018-7b8685.jpg", "1"));
        browseEntityList.add(new BrowseEntity("https://i.a4vn.com/2018/11/27/tong-hop-hinh-anh-cac-hotgirl-xinh-hot-nhat-nam-2018-0ddb27.jpg", "1"));
        browseEntityList.add(new BrowseEntity("https://i.a4vn.com/2018/11/27/tong-hop-hinh-anh-cac-hotgirl-xinh-hot-nhat-nam-2018-9fa595.jpg", "1"));
        browseEntityList.add(new BrowseEntity("https://i.a4vn.com/2018/11/27/tong-hop-hinh-anh-cac-hotgirl-xinh-hot-nhat-nam-2018-7b8685.jpg", "1"));
        browseEntityList.add(new BrowseEntity("https://i.a4vn.com/2018/11/27/tong-hop-hinh-anh-cac-hotgirl-xinh-hot-nhat-nam-2018-0ddb27.jpg", "1"));
        browseEntityList.add(new BrowseEntity("https://i.a4vn.com/2018/11/27/tong-hop-hinh-anh-cac-hotgirl-xinh-hot-nhat-nam-2018-9fa595.jpg", "1"));

        headBubbleView.setBrowseEntities(browseEntityList);
        headBubbleView.startAnimator();

        placeholderToolbar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtil.getStatusHeight(getContext())));
        fadeShopToolbar.setListener(this);
        fadeShopToolbar.setAmountCart(0);

        appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            int scrollY = Math.abs(verticalOffset);
            fadeShopToolbar.setAlphaToolbar(scrollY);
        });


        refreshLayout.setRefreshHeader(new CustomFragmentHeader(getContext()));
//        refreshLayout.setOnMultiPurposeListener(new ShopRefreshMultiPurposeListener(this));
        refreshLayout.setOnRefreshListener(refreshLayout -> {
            refreshLayout.finishRefresh(1000/*,false*/);
            onRefreshing();
        });

//        Setting rv -------
        flashCategoryAdapter = new FlashCategoryAdapter(getActivity(), flashCategoryList, this);
        settingGridRecyclerView(rvFlash, flashCategoryAdapter, 2, true, false);

        hotSpaDetailList.add(new HotSpaDetail());
        hotSpaDetailList.add(new HotSpaDetail());
        hotSpaDetailList.add(new HotSpaDetail());

        hotSpaAdapter = new HotSpaAdapter(getActivity(), hotSpaDetailList, this);
        settingHorizontalLinearRecyclerView(rvHotSpa, hotSpaAdapter, true, false);

        rankAdapter = new ShopRankAdapter(getContext(), rankingList, this);
        settingHorizontalLinearRecyclerView(rvRank, rankAdapter, true, false);


        serviceCategoryAdapter = new ServiceCategoryAdapter(getActivity(), serviceCategoryList, this);
        settingGridRecyclerView(rvCatService, serviceCategoryAdapter, 5, true, false);

        serviceCategoryAdapter.setOnItemClickListener((adapter, view, position) -> {
            ServiceCategoryActivity.startActivity(getContext(), "123");
        });

        recommendCityAdapter = new ShopRecommendCityAdapter(getActivity(), recommendCityList);
        settingHorizontalLinearRecyclerView(rvRecommendCity, recommendCityAdapter, true, false);
    }

    private void settingTabLayout() {
        creator = FragmentPagerItems.with(getContext());
        mTitles = getResources().getStringArray(R.array.service_category_tablayout);

        for (int i = 0; i < mTitles.length; i++) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("targetId", "123");
            creator.add(FragmentPagerItem.of(mTitles[i], i == 0 ? ServiceCategoryProductFragment.class : ServiceCategoryDailyFragment.class, bundle));
        }

        fragmentPagerItemAdapter = new FragmentPagerItemAdapter(getChildFragmentManager(), creator.create());
        viewPager.setAdapter(fragmentPagerItemAdapter);
//        viewPager.disableScroll(true);
        viewPager.setOffscreenPageLimit(0);
//        tabLayout.setViewPager(viewPager);

        MagicIndicatorUtil.settingMagicIndicatorTabLayout(getContext(), R.array.service_category_tablayout, magicTabLayout, viewPager);
    }

    @Override
    public void onHeaderRefresh(RefreshHeader header, int offset, int headerHeight) {
        float scale = (float) offset / headerHeight;
        imgTop.setScaleY(scale + 1);
        imgTop.setScaleX(scale + 1);
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
    public void onClickedToolbar(View v, int action) {

    }
}

