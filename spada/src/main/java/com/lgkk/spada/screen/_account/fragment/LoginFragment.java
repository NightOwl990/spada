package com.lgkk.spada.screen._account.fragment;


import androidx.lifecycle.ViewModelProviders;
import androidx.fragment.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;

import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.spada.R;
import com.lgkk.spada.base.BaseSpadaFragment;
import com.lgkk.spada.base.ViewModelFactory;
import com.lgkk.spada.screen._account.viewmodel.LoginViewModel;
import com.lgkk.spada.screen._account.activity.RegisterActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseSpadaFragment implements OnItemRvClickListener<Object> {

    @BindView(R.id.rootView)
    LinearLayout rootView;

    @Inject
    ViewModelFactory viewModelFactory;
    private LoginViewModel viewModel;


    private String statusType;
    private static final String STATUS_TYPE = "status";
    @Override
    public int getLayoutResId() {
        return R.layout.fragment_login;
    }


    @Override
    public void attachView() {

    }

    //    show loading screen ---------
    @Override
    public void initDatas() {
        appComponent().doInjection(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);
        observableViewModel();

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
    protected void onRefreshing() {
        if (isErrorData) {
            showLoadingScreen(rootView);
        }
        isRefreshing = true;

//        viewModel.loadProduct(token, targetId);
    }

    @Override
    public void configViews() {
        closeLoadingScreen();

    }

    @Override
    public void onItemRvClick(View view, Object item, int adapterPosition) {

    }

    @OnClick(R.id.btnRegister)
    void onBack() {
        RegisterActivity.startActivity(getContext(),"123");
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


