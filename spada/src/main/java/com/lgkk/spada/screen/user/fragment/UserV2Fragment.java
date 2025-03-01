package com.lgkk.spada.screen.user.fragment;


import androidx.lifecycle.ViewModelProviders;
import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lgkk.base_project.base.listener.OnFadeCommonToolbarListener;
import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.base_project.utils.ImageUtils;
import com.lgkk.base_project.utils.ScreenUtil;
import com.lgkk.base_project.widget.CircleImageView;
import com.lgkk.base_project.widget.CustomFragmentHeader;
import com.lgkk.spada.R;
import com.lgkk.spada.base.BaseSpadaFragment;
import com.lgkk.spada.base.ViewModelFactory;
import com.lgkk.spada.model.UserSetting;
import com.lgkk.spada.model.service.ServiceDetails;
import com.lgkk.spada.model.user.UserInfo;
import com.lgkk.spada.screen.user.widget.FadeUserToolbar;
import com.lgkk.spada.screen.service.adapter.ServiceProductAdapter;
import com.lgkk.spada.screen.user.adapter.UserSettingAdapter;
import com.lgkk.spada.screen.user.viewmodel.UserViewModel;
import com.lgkk.base_project.widget.roundedimageview.RoundedImageView;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class UserV2Fragment extends BaseSpadaFragment implements OnItemRvClickListener<Object>, OnFadeCommonToolbarListener {

    @BindView(R.id.rootView)
    RelativeLayout rootView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.userToolbar)
    FadeUserToolbar userToolbar;
    @BindView(R.id.imgBgMember)
    RoundedImageView imgBgMember;
    @BindView(R.id.txtVipLevel)
    TextView txtVipLevel;
    @BindView(R.id.txtVipPromote)
    TextView txtVipPromote;
    @BindView(R.id.txtVipMemberCount)
    TextView txtVipMemberCount;
    @BindView(R.id.imgUser)
    CircleImageView imgUser;
    @BindView(R.id.rlAvatar)
    RelativeLayout rlAvatar;
    @BindView(R.id.txtName)
    TextView txtName;
    @BindView(R.id.imgLevel)
    ImageView imgLevel;
    @BindView(R.id.txtPostNumber)
    TextView txtPostNumber;
    @BindView(R.id.txtFollowedNumber)
    TextView txtFollowedNumber;
    @BindView(R.id.btnCamera)
    ImageView btnCamera;
    @BindView(R.id.btnSuggest)
    ImageView btnSuggest;
    @BindView(R.id.btnStyleRv)
    RelativeLayout btnStyleRv;
    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;
    @BindView(R.id.rvOrder)
    RecyclerView rvOrder;
    @BindView(R.id.rlTopContent)
    RelativeLayout rlTopContent;
    @BindView(R.id.fakeStatusBar)
    View fakeStatusBar;
    @BindView(R.id.rvSetting)
    RecyclerView rvSetting;
    @BindView(R.id.rvRecommend)
    RecyclerView rvRecommend;


    @Inject
    ViewModelFactory viewModelFactory;
    private UserViewModel viewModel;

    private UserSettingAdapter orderAdapter, settingAdapter;
    private ServiceProductAdapter recommendAdapter;


    List<UserSetting> orderSettingList = new ArrayList<>();
    List<UserSetting> mainSettingList = new ArrayList<>();
    List<ServiceDetails> recommendServiceList = new ArrayList<>();

    private UserInfo userInfo;
    private String userId;
    private static final String USER_ID = "userId";

    //    public static Fragment newInstance(String userId) {
//        Bundle bundle = new Bundle();
//        bundle.putString(USER_ID, userId);
//        Fragment fragment = new OrderListFragment();
//        fragment.setArguments(bundle);
//        return fragment;
//    }
//
//    @Override
//    protected void initDataGetFromArgument(Bundle savedInstanceState) {
//        super.initDataGetFromArgument(savedInstanceState);
//        if (savedInstanceState != null) {
//            userId = savedInstanceState.getString(USER_ID);
//        } else {
//            userId = getArguments().getString(USER_ID);
//        }
//    }
//
    @Override
    public int getLayoutResId() {
        return R.layout.fragment_user_v2;
    }


    @Override
    public void attachView() {

    }

    //    show loading screen ---------
    @Override
    public void initDatas() {
        appComponent().doInjection(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserViewModel.class);
        observableViewModel();

        showLoadingScreen(rootView);
        onRefreshing();
    }

    //    Setting view model ------
    private void observableViewModel() {
        viewModel.getUserInfo().observe(this, userInfo -> {
            showUserInfo(userInfo);
        });

        viewModel.getOrderSettingList().observe(this, orderSettingList -> {
            showOrderSettingList(orderSettingList);
        });

        viewModel.getMainSettingList().observe(this, settingList -> {
            showMainSettingList(settingList);
        });

        viewModel.getRecommendServiceList().observe(this, recommendList -> {
            showRecommendServiceList(recommendList);
        });


        viewModel.getError().observe(this, isError -> {
            if (isError != null && isError) {
                showError();
            }
        });
    }

    private void showRecommendServiceList(List<ServiceDetails> recommendList) {
//        recommendServiceList.clear();
//        recommendServiceList.addAll(recommendList);
//        settingAdapter.notifyDataSetChanged();
        closeLoadingScreen();
    }

    private void showMainSettingList(List<UserSetting> settingList) {
        mainSettingList.clear();
        mainSettingList.addAll(settingList);
        settingAdapter.notifyDataSetChanged();
        closeLoadingScreen();
    }

    private void showOrderSettingList(List<UserSetting> orderSettingList) {
        this.orderSettingList.clear();
        this.orderSettingList.addAll(orderSettingList);
        recommendAdapter.notifyDataSetChanged();
        closeLoadingScreen();
    }

    private void showUserInfo(UserInfo userInfo) {
    }

    //    onRefreshing data ------------
    protected void onRefreshing() {
        if (isErrorData) {
            showLoadingScreen(rootView);
        }
        isRefreshing = true;

        viewModel.loadOrderSettingList(token);
        viewModel.loadMainSettingList(token);
        viewModel.loadRecommendServiceList(token);
    }

    @Override
    public void configViews() {
        userToolbar.setListener(this);      ;
        nestedScrollView.getViewTreeObserver().addOnScrollChangedListener(() -> {
            int scrollY = nestedScrollView.getScrollY();
            userToolbar.setAlphaToolbar(scrollY);
        });


        int statusBarHeight = ScreenUtil.getStatusHeight(getContext());
        fakeStatusBar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight));

//      Setting refreshlayout ----------
        refreshLayout.setRefreshHeader(new CustomFragmentHeader(getContext()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(1000/*,false*/);
                onRefreshing();
            }
        });

//      Config Top Layout ----------------
        ImageUtils.loadImageDrawableByGlide(getContext(), R.drawable.level20, imgLevel);
        ImageUtils.loadImageDrawableByGlide(getContext(), R.drawable.me_mirror, btnCamera);
        ImageUtils.loadImageDrawableByGlide(getContext(), R.drawable.me_punch_the_clock_icon, btnSuggest);


//        Setting Recycler View ----------
        orderAdapter = new UserSettingAdapter(getActivity(), orderSettingList, this);
        settingGridRecyclerView(rvOrder, orderAdapter, 5,true, false);

        settingAdapter = new UserSettingAdapter(getActivity(), mainSettingList, this);
        settingGridRecyclerView(rvSetting, settingAdapter, 4,true, false);

        recommendAdapter = new ServiceProductAdapter(getActivity(), recommendServiceList, this);
        settingLinearRecyclerView(rvRecommend, recommendAdapter, true, false);
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


