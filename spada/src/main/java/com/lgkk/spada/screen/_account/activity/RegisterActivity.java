package com.lgkk.spada.screen._account.activity;

import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.base_project.utils.RxBus;
import com.lgkk.base_project.utils.ScreenUtil;
import com.lgkk.base_project.widget.FragmentPagerItemAdapter;
import com.lgkk.base_project.widget.FragmentPagerItems;
import com.lgkk.base_project.widget.NonSwipeableWithAnimationViewPager;
import com.lgkk.base_project.widget.flycotablayout.SlidingTabLayout;
import com.lgkk.spada.R;

import com.lgkk.spada.base.BaseSpadaActivity;

import com.lgkk.spada.base.ViewModelFactory;
import com.lgkk.spada.event.EventManager;
import com.lgkk.spada.screen._account.adapter.StepPagerAdapter;
import com.lgkk.spada.screen._account.fragment.RegisterInfoFragment;
import com.lgkk.spada.screen._account.fragment.RegisterPhoneFragment;
import com.lgkk.spada.screen._account.viewmodel.RegisterViewModel;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class RegisterActivity extends BaseSpadaActivity implements OnItemRvClickListener<Object> {

    @BindView(R.id.fakeStatusBar)
    View fakeStatusBar;
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.tabLayout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.viewPager)
    NonSwipeableWithAnimationViewPager viewPager;
    @BindView(R.id.rootView)
    LinearLayout rootView;
    @BindView(R.id.fb_login)
    ImageView fbLogin;
    @BindView(R.id.btnFacebook)
    ImageView btnFacebook;
    @BindView(R.id.btnGoogle)
    ImageView btnGoogle;
    @BindView(R.id.btnZalo)
    ImageView btnZalo;
    @BindView(R.id.other_login_logo)
    LinearLayout otherLoginLogo;
    private List<Fragment> mFragments = new ArrayList<>();
    private RegisterViewModel viewModel;
    @Inject
    ViewModelFactory viewModelFactory;
    StepPagerAdapter mAdapter;

    protected FragmentPagerItemAdapter fragmentPagerItemAdapter;
    private FragmentPagerItems.Creator creator;
    private String[] mTitles;

    private static final String SHOP_ID = "shopId";
    private String shopId;

    public static void startActivity(Context context, String shopId) {
        Intent intent = new Intent(context, RegisterActivity.class);
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
        return R.layout.activity_register;
    }

    //    show loading screen ---------
    @Override
    public void initDatas() {
        appComponent().doInjection(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RegisterViewModel.class);
        observableViewModel();
        initMarginTopToolbar(false);
        showLoadingScreen(rootView);
        onRefreshing();
    }

    //    Setting view model ------
    private void observableViewModel() {
//        viewModel.getProductDetail().observe(this, product -> {
////            showProduct(product);
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
        initRxBusListener();
        int statusBarHeight = ScreenUtil.getStatusHeight(this);
        fakeStatusBar.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight));
        settingTabLayout();
        closeLoadingScreen();
    }

    private void initRxBusListener() {
        Disposable disposable = RxBus.getInstance()
                .toObservable(EventManager.StepChooseEvent.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (event) -> {
                            switch (event.type) {
                                case "next":
                                    viewPager.setCurrentItem(1);
                                    break;
                                case "back":
                                    viewPager.setCurrentItem(0);
                                    break;
                            }
                        }
                );
        addDisposable(disposable);
    }

    private void settingTabLayout() {
        mTitles = getResources().getStringArray(R.array.stepLabels);
        mFragments.clear();
        mFragments.add(new RegisterPhoneFragment());
        mFragments.add(new RegisterInfoFragment());
        mAdapter = new StepPagerAdapter(getSupportFragmentManager(), mFragments, mTitles);
        viewPager.disableScroll(true);
        viewPager.setOffscreenPageLimit(2);
//        viewPager.setPageTransformer(true , new FadePageTransformer());
        viewPager.setAdapter(mAdapter);
        tabLayout.setViewPager(viewPager);
    }


    @Override
    public void onItemRvClick(View view, Object item, int adapterPosition) {

    }

    @OnClick(R.id.btnBack)
    void onBack() {
        onBackPressed();
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

