package com.lgkk.spada.screen.home.activity;

import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lgkk.base_project.base.Constants;
import com.lgkk.base_project.base.listener.OnFadeCommonToolbarListener;
import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.base_project.utils.MagicIndicatorUtil;
import com.lgkk.base_project.widget.FragmentPagerItem;
import com.lgkk.base_project.widget.FragmentPagerItemAdapter;
import com.lgkk.base_project.widget.FragmentPagerItems;
import com.lgkk.base_project.widget.NonSwipeableWithAnimationViewPager;
import com.lgkk.base_project.widget.magicindicator.MagicIndicator;
import com.lgkk.spada.R;
import com.lgkk.spada.base.BaseSpadaActivity;
import com.lgkk.spada.base.DBLocalData;
import com.lgkk.spada.base.ViewModelFactory;
import com.lgkk.base_project.widget.TitleFlashSale;
import com.lgkk.spada.model.shop.Product;
import com.lgkk.spada.screen.home.adapter.FlashSaleByHourAdapter;
import com.lgkk.spada.screen.home.fragment.FlashSaleByHourFragment;
import com.lgkk.spada.screen.home.viewmodel.FlashSaleViewModel;
import com.lgkk.spada.widget.FadeCommonToolbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.inject.Inject;

import butterknife.BindView;

public class FlashSaleActivity extends BaseSpadaActivity implements OnFadeCommonToolbarListener, OnItemRvClickListener<Object> {

    @BindView(R.id.rootView)
    LinearLayout rootView;

    @BindView(R.id.fadeCommonToolbar)
    FadeCommonToolbar fadeCommonToolbar;
    @BindView(R.id.rvFlashSaleByHour)
    RecyclerView rvFlashSaleByHour;
    @BindView(R.id.content_rl)
    RelativeLayout contentRl;
    @BindView(R.id.viewPager)
    NonSwipeableWithAnimationViewPager viewPager;
    @BindView(R.id.magicTabLayout)
    MagicIndicator magicTabLayout;

    List<TitleFlashSale> titleFlashSaleList = new ArrayList<>();
    List<Product> flashSaleByHourList = new ArrayList<>();
    private FlashSaleByHourAdapter flashSaleByHourAdapter;
    private static final String SHOP_ID = "shopId";
    private String shopId;
    @Inject
    ViewModelFactory viewModelFactory;
    private FlashSaleViewModel viewModel;

    public static void startActivity(Context context, String shopId) {
        Intent intent = new Intent(context, FlashSaleActivity.class);
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
        return R.layout.activity_flash_sale;
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
//        viewModel.getProductDetail().observe(this, result -> {
////            showProduct(result);
//        });

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
        fadeCommonToolbar.setAlphaToolbar(1);
        fadeCommonToolbar.setListener(this);
        titleFlashSaleList.clear();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"),
                Locale.getDefault());
        titleFlashSaleList.addAll(DBLocalData.getTitleFlashSaleList(calendar.getTimeInMillis(), Constants.DEFAULT_RANGE_FLASH_HOUR));

        flashSaleByHourAdapter = new FlashSaleByHourAdapter(this, flashSaleByHourList);
        initSettingLinearRv(rvFlashSaleByHour, flashSaleByHourAdapter, true, false);

        settingTabLayout();

        closeLoadingScreen();
    }

    protected FragmentPagerItems.Creator creator;
    protected FragmentPagerItemAdapter fragmentPagerItemAdapter;

    private void settingTabLayout() {
        creator = FragmentPagerItems.with(this);

        for (int i = 0; i < titleFlashSaleList.size(); i++) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("targetId", "123");
            creator.add(FragmentPagerItem.of(titleFlashSaleList.get(i).getStatus(), FlashSaleByHourFragment.class, bundle));
        }

        fragmentPagerItemAdapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), creator.create());
        viewPager.setAdapter(fragmentPagerItemAdapter);
        viewPager.disableScroll(true);
        viewPager.setOffscreenPageLimit(0);
        MagicIndicatorUtil.settingMagicIndicatorFlashSaleTabLayout(this, titleFlashSaleList, magicTabLayout, viewPager);
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
        }
    }
}

