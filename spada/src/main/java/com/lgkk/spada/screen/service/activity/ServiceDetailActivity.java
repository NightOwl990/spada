package com.lgkk.spada.screen.service.activity;


import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lgkk.base_project.base.listener.OnFadeCommonToolbarListener;
import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.base_project.flowlayout.FlowLayout;
import com.lgkk.base_project.flowlayout.TagAdapter;
import com.lgkk.base_project.flowlayout.TagFlowLayout;
import com.lgkk.spada.R;
import com.lgkk.spada.base.BaseSpadaActivity;
import com.lgkk.spada.base.ViewModelFactory;
import com.lgkk.spada.model.service.ServiceComment;
import com.lgkk.spada.model.service.ServiceDetails;
import com.lgkk.spada.model.service.ServiceTagComment;
import com.lgkk.spada.screen.service.adapter.ServiceCommentAdapter;
import com.lgkk.spada.screen.service.viewmodel.ServiceViewModel;
import com.lgkk.spada.widget.FadeCartToolbar;
import com.lgkk.spada.widget.ServiceDetailTagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import cn.iwgang.countdownview.CountdownView;

public class ServiceDetailActivity extends BaseSpadaActivity implements OnItemRvClickListener<Object>, OnFadeCommonToolbarListener {
    //
//    @BindView(R.id.toolBar)
//    RelativeLayout toolBar;
//    @BindView(R.id.refreshLayout)
//    RefreshLayout refreshLayout;
//    @BindView(R.id.tabLayout)
//    SmartTabLayout tabLayout;
//    @BindView(R.id.viewPager)
//    ViewPager viewPager;
    @BindView(R.id.rootView)
    RelativeLayout rootView;
    @BindView(R.id.cdTimer)
    CountdownView cdTimer;
    @BindView(R.id.flowLayout)
    TagFlowLayout flowLayout;
    @BindView(R.id.flowServiceCoupon)
    TagFlowLayout flowServiceCoupon;
    @BindView(R.id.flowCommonCoupon)
    TagFlowLayout flowCommonCoupon;
    @BindView(R.id.rbHos)
    RatingBar rbHos;
    @BindView(R.id.tagFlowLayout)
    ServiceDetailTagFlowLayout tagFlowLayout;
    @BindView(R.id.rvPinComment)
    RecyclerView rvPinComment;
    @BindView(R.id.cartToolbar)
    FadeCartToolbar cartToolbar;
    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;

    private List<ServiceComment> commentList = new ArrayList<>();

    private ServiceCommentAdapter serviceCommentAdapter;
    @Inject
    ViewModelFactory viewModelFactory;
    private ServiceViewModel viewModel;

    private static final String SERVICE_ID = "serviceID";
    private String serviceId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_service_detail;
    }

    public static void startActivity(Context context, String serviceId) {
        Intent intent = new Intent(context, ServiceDetailActivity.class);
        intent.putExtra(SERVICE_ID, serviceId);
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
//    @OnClick(R.id.btnBack)
//    public void onBack() {
//        onBackPressed();
//    }

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
        viewModel.getServiceDetailById().observe(this, result -> {
            showServiceDetailById(result);
        });

        viewModel.getError().observe(this, isError -> {
            if (isError != null && isError) {
                showError();
            }
        });
    }


    public void showServiceDetailById(ServiceDetails serviceResult) {

        //   Close loading screen ------------------
        closeLoadingScreen();
    }

    //    onRefreshing data ------------

    @Override
    protected void onRefreshing() {
        if (isErrorData) {
            showLoadingScreen(rootView);
        }
        viewModel.loadServiceDetailById(token, serviceId);
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
    public void onClickedToolbar(View v, int action) {
        switch (action) {
            case FadeCartToolbar.TYPE_LEFT_CLICK:
                onBackPressed();
                break;
            case FadeCartToolbar.TYPE_RIGHT_CLICK:
//                CartActivity.startActivity(this, "123");
                break;
        }
    }

    @Override
    public void configViews() {
        cartToolbar.setListener(this);
        cdTimer.setTag("cdtimer1");
        long time1 = (long) 5 * 60 * 60 * 1000;
        cdTimer.start(time1);

//        Setting Flow layout ----------------
        List<ServiceTagComment> mData = new ArrayList<>();
        for (int i = 0; i <= 20; i++) {
            mData.add(new ServiceTagComment("abc",i));
        }
        tagFlowLayout.setData(this, mData);

        String[] mVals1 = new String[]
                {"Khí Oxy", "Tia Flash", "Hoàn tiền"};
        String[] couponService = new String[]
                {"50.000đ", "200.000đ"};
        String[] couponCommon = new String[]
                {"300.000đ", "100.000đ"};
        LayoutInflater mInflater = LayoutInflater.from(this);
        flowLayout.setAdapter(new TagAdapter<String>(mVals1) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                LinearLayout lnServiceTag = (LinearLayout) mInflater.inflate(R.layout.layout_service_detail_flow_tag,
                        flowLayout, false);
                TextView txtNameTag = lnServiceTag.findViewById(R.id.txtNameTag);
                txtNameTag.setText(s);
                return lnServiceTag;
            }
        });

        flowServiceCoupon.setAdapter(new TagAdapter<String>(couponService) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                LinearLayout lnServiceTag = (LinearLayout) mInflater.inflate(R.layout.layout_service_detail_flow_coupon,
                        flowServiceCoupon, false);
                TextView txtNameTag = lnServiceTag.findViewById(R.id.txtNameTag);
                txtNameTag.setText(s);
                return lnServiceTag;
            }
        });

        flowCommonCoupon.setAdapter(new TagAdapter<String>(couponCommon) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                LinearLayout lnServiceTag = (LinearLayout) mInflater.inflate(R.layout.layout_service_detail_flow_coupon,
                        flowCommonCoupon, false);
                TextView txtNameTag = lnServiceTag.findViewById(R.id.txtNameTag);
                txtNameTag.setText(s);
                return lnServiceTag;
            }
        });


//        Setting RatingBar -----------
        Drawable starDrawable = getResources().getDrawable(R.drawable.evaluate_star_10);
        int height = starDrawable.getMinimumHeight();
        ViewGroup.LayoutParams params = rbHos.getLayoutParams();
        params.height = (int) (height * 1.3);
        rbHos.setLayoutParams(params);

//        Setting RefreshLayout -----------------
//        refreshLayout.setRefreshHeader(new CustomFragmentHeader(mContext));
//        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//                refreshLayout.finishRefresh(1000/*,false*/);
//                ;
//            }
//        });

//        mTabLayout.setTabData(mTabEntities, this, R.id.fl_change, mFragments);

//        Setting RV PinComment ---------------
        commentList.add(new ServiceComment());
        commentList.add(new ServiceComment());
        serviceCommentAdapter = new ServiceCommentAdapter(this, commentList, this);
        initSettingLinearRv(rvPinComment, serviceCommentAdapter, true,false);


        nestedScrollView.getViewTreeObserver().addOnScrollChangedListener(() -> {
            int scrollY = nestedScrollView.getScrollY();
           cartToolbar.setAlphaToolbar(scrollY);
        });

    }


    @Override
    public void onItemRvClick(View view, Object item, int adapterPosition) {

    }

    @Override
    public void complete() {
    }


}


