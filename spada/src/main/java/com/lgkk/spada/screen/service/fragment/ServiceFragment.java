package com.lgkk.spada.screen.service.fragment;


import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.base_project.utils.ScreenUtil;
import com.lgkk.base_project.widget.FragmentPagerItem;
import com.lgkk.base_project.widget.FragmentPagerItemAdapter;
import com.lgkk.base_project.widget.FragmentPagerItems;
import com.lgkk.base_project.widget.NonSwipeableViewPager;
import com.lgkk.spada.R;
import com.lgkk.spada.base.BaseSpadaFragment;
import com.lgkk.spada.model.shop.Product;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import butterknife.BindView;

public class ServiceFragment extends BaseSpadaFragment implements OnItemRvClickListener<Product> {

    @BindView(R.id.viewPager)
    NonSwipeableViewPager viewPager;
    @BindView(R.id.fakeStatusBar)
    View fakeStatusBar;
    @BindView(R.id.lnLocation)
    LinearLayout lnLocation;
    @BindView(R.id.tabLayout)
    SmartTabLayout tabLayout;
    @BindView(R.id.lnTop)
    LinearLayout lnTop;
    @BindView(R.id.txtSeach)
    TextView txtSeach;
    @BindView(R.id.btnCamera)
    ImageView btnCamera;
    @BindView(R.id.rlSearch)
    RelativeLayout rlSearch;

    protected FragmentPagerItemAdapter fragmentPagerItemAdapter;
    private FragmentPagerItems.Creator creator;
    private String[] mTitles;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_service;
    }

    @Override
    public void attachView() {

    }


    //    show loading screen ---------
    @Override
    public void initDatas() {

        onRefreshing();
    }

    //    show error screen -------------
    public void showError() {
        isErrorData = true;
        ;
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

    //    onRefreshing data ------------
    private void onRefreshing() {
        if (isErrorData) {

        }

    }

    @Override
    public void configViews() {
        int statusBarHeight = ScreenUtil.getStatusHeight(getContext());
        fakeStatusBar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight));

        handleNestedScrollView();
        settingTablayout();
    }

    private void settingTablayout() {
        mTitles = getResources().getStringArray(R.array.home_service_top_tablayout);

        creator = FragmentPagerItems.with(getContext());
        for (int i = 0; i < mTitles.length; i++) {
            creator.add(FragmentPagerItem.of(mTitles[i], SuggestServiceFragment.class));
        }

        fragmentPagerItemAdapter = new FragmentPagerItemAdapter(getChildFragmentManager(), creator.create());
        viewPager.setAdapter(fragmentPagerItemAdapter);
        viewPager.disableScroll(true);
        viewPager.setOffscreenPageLimit(1);
        tabLayout.setViewPager(viewPager);
    }

    private void handleNestedScrollView() {
    }

    @Override
    public void onItemRvClick(View view, Product item, int adapterPosition) {

    }
}



