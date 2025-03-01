package com.lgkk.spada.screen.notification.fragment;


import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


import com.lgkk.base_project.base.listener.OnItemMultiRvClickListener;
import com.lgkk.base_project.utils.ScreenUtil;
import com.lgkk.base_project.widget.FragmentPagerItem;
import com.lgkk.base_project.widget.FragmentPagerItemAdapter;
import com.lgkk.base_project.widget.FragmentPagerItems;
import com.lgkk.spada.R;
import com.lgkk.spada.base.BaseSpadaFragment;
import com.lgkk.spada.base.ViewModelFactory;
import com.lgkk.spada.model.home.NotificationBean;
import com.lgkk.spada.screen.notification.viewmodel.NotificationViewModel;
import com.lgkk.spada.screen.user.adapter.NotificationListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class NotificationFragment extends BaseSpadaFragment implements OnItemMultiRvClickListener<NotificationBean> {

//    @BindView(R.id.tabLayout)
//    SmartTabLayout tabLayout;
//    @BindView(R.id.viewPager)
//    ViewPager viewPager;
    @BindView(R.id.viewTop)
    View viewTop;
    @BindView(R.id.rvNotification)
    RecyclerView rvNotification;

    @Inject
    ViewModelFactory viewModelFactory;
    private NotificationViewModel viewModel;

    protected FragmentPagerItemAdapter fragmentPagerItemAdapter;
    protected FragmentPagerItems.Creator creator;
    private List<NotificationBean> notificationList = new ArrayList<>();
    private NotificationListAdapter mAdapter;

    String[] mTitles;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_notification;
    }


    @Override
    public void attachView() {

    }

    //    show loading screen ---------
    @Override
    public void initDatas() {
        appComponent().doInjection(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(NotificationViewModel.class);
        observableViewModel();

//        showLoadingScreen(rootView);
        onRefreshing();
    }

    //    Setting view model ------
    private void observableViewModel() {
        viewModel.getNotificationList().observe(this, result -> {
            showNotificationList(result);
        });

        viewModel.getError().observe(this, isError -> {
            if (isError != null && isError) {
                showError();
            }
        });
    }

    private void showNotificationList(List<NotificationBean> notificationListResult) {
        notificationList.clear();
        notificationList.addAll(notificationListResult);

        notificationList.add(new NotificationBean("1"));
        notificationList.add(new NotificationBean("1"));
        notificationList.add(new NotificationBean("1"));
        mAdapter.notifyDataSetChanged();


        closeLoadingScreen();
    }

    //    onRefreshing data ------------
    protected void onRefreshing() {
        if (isErrorData) {
//            showLoadingScreen(rootView);
        }
        isRefreshing = true;

        viewModel.loadNotificationList(token, 0, 50);
    }

    @Override
    public void configViews() {
        int statusBarHeight = ScreenUtil.getStatusHeight(getContext());
        viewTop.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight));

//        setting rv ----
        mAdapter = new NotificationListAdapter(getActivity(), notificationList, this);
        settingLinearRecyclerView(rvNotification, mAdapter, true, false);

//        setting tablayout --------------------
//        settingTabLayout();

    }

    private void settingTabLayout() {
        mTitles = getResources().getStringArray(R.array.notification_tab);
        creator = FragmentPagerItems.with(getContext());
        creator.add(FragmentPagerItem.of(mTitles[0], NotiPrivateMessageFragment.class));
        creator.add(FragmentPagerItem.of(mTitles[1], NotiCommentFragment.class));
        creator.add(FragmentPagerItem.of(mTitles[2], NotiFavouriteFragment.class));
        creator.add(FragmentPagerItem.of(mTitles[3], NotiMailFragment.class));
        creator.add(FragmentPagerItem.of(mTitles[4], NotiScienceFragment.class));

        fragmentPagerItemAdapter = new FragmentPagerItemAdapter(getChildFragmentManager(), creator.create());
//        viewPager.setAdapter(fragmentPagerItemAdapter);
//        viewPager.setOffscreenPageLimit(0);
//        tabLayout.setViewPager(viewPager);
    }


    //    show error screen -------------
    public void showError() {
        if (isRefreshing) {
            isErrorData = true;
//            showErrorScreen(rootView);
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
    public void onItemRvClick(View view, NotificationBean item, int adapterPosition, String type) {

    }
}


