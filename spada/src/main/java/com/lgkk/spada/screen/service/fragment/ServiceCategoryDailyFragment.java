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
import com.lgkk.spada.model.community.post.PostService;
import com.lgkk.spada.screen.service.adapter.PostServiceBARowAdapter;
import com.lgkk.spada.screen.service.viewmodel.SuggestTabViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class ServiceCategoryDailyFragment extends BaseSpadaFragment implements OnItemRvClickListener<Object> {


    @BindView(R.id.rvDaily)
    RecyclerView rvDaily;
    @BindView(R.id.rootView)
    LinearLayout rootView;

    //    private PostServiceBARowAdapter postServiceBARowAdapter;
    private PostServiceBARowAdapter mAdapter;
    private List<PostService> postDetailsList = new ArrayList<>();

    @Inject
    ViewModelFactory viewModelFactory;
    private SuggestTabViewModel viewModel;


    private String targetId="";
    private static final String TARGET_ID = "targetId";

    public static Fragment newInstance(String targetId) {
        Bundle bundle = new Bundle();
        bundle.putString(TARGET_ID, targetId);
        Fragment fragment = new ServiceCategoryDailyFragment();
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
        return R.layout.fragment_service_category_daily;
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
        viewModel.getListPostServiceByCategoryId().observe(this, postDetails -> {
            showListPostServiceByCategoryId(postDetails);
        });

        viewModel.getError().observe(this, isError -> {
            if (isError != null && isError) {
                showError();
            }
        });
    }

    private void showListPostServiceByCategoryId(List<PostService> postDetails) {
        postDetailsList.clear();
        postDetailsList.addAll(postDetails);

        mAdapter.notifyDataSetChanged();

        closeLoadingScreen();
    }

    //    onRefreshing data ------------
    protected void onRefreshing() {
        if (isErrorData) {
            showLoadingScreen(rootView);
        }
        isRefreshing = true;
        viewModel.loadListPostServiceByCategoryId(token, targetId, 0, 5);
    }

    @Override
    public void configViews() {
        postDetailsList = new ArrayList<>();

//        Setting rv -------
        mAdapter = new PostServiceBARowAdapter(getActivity(), postDetailsList, this);
       settingLinearRecyclerView(rvDaily, mAdapter,  true, true);
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

