package com.lgkk.spada.screen._main;

import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.base_project.utils.RxUtils;
import com.lgkk.base_project.utils.ScreenUtil;
import com.lgkk.base_project.utils.SharedPreferencesUtil;
import com.lgkk.base_project.widget.SpadaTextView;
import com.lgkk.spada.R;
import com.lgkk.spada.base.BaseSpadaActivity;
import com.lgkk.spada.base.ViewModelFactory;
import com.lgkk.spada.screen._account.activity.RegisterActivity;
import com.lgkk.spada.widget.LoginScrollBackgroundView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;

public class WelcomeActivity extends BaseSpadaActivity implements OnItemRvClickListener<Object> {


    private static final String SHOP_ID = "shopId";
    @BindView(R.id.fakeStatusBar)
    View fakeStatusBar;
    @BindView(R.id.loginScroll)
    LoginScrollBackgroundView loginScroll;
    @BindView(R.id.txtCountDownTime)
    TextView txtCountDownTime;
    @BindView(R.id.rootView)
    LinearLayout rootView;
    @BindView(R.id.btnLogin)
    TextView btnLogin;
    @BindView(R.id.btnRegister)
    TextView btnRegister;
    @BindView(R.id.btnFacebook)
    ImageView btnFacebook;
    @BindView(R.id.btnGoogle)
    ImageView btnGoogle;
    @BindView(R.id.btnZalo)
    ImageView btnZalo;
    @BindView(R.id.other_login_logo)
    LinearLayout otherLoginLogo;
    @BindView(R.id.txtRule1)
    SpadaTextView txtRule1;
    @BindView(R.id.txtRule2)
    SpadaTextView txtRule2;
    @BindView(R.id.check_layout)
    LinearLayout checkLayout;
    private String shopId;
    @Inject
    ViewModelFactory viewModelFactory;
    private SpadaViewModel viewModel;

    public static void startActivity(Context context, String shopId) {
        Intent intent = new Intent(context, WelcomeActivity.class);
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

    @OnClick(R.id.btnRegister)
    public void onRegister() {
        RegisterActivity.startActivity(this, "123");
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }

    //    show loading screen ---------
    @Override
    public void initDatas() {
        appComponent().doInjection(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SpadaViewModel.class);
        initMarginTopToolbar(false);
        observableViewModel();

//        showLoadingScreen(rootView);
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
//            showLoadingScreen(rootView);
        }
        isRefreshing = true;

//        viewModel.loadProduct(token, targetId);
    }

    @Override
    public void configViews() {
        getUniqueIdPhone();
        int statusBarHeight = ScreenUtil.getStatusHeight(this);
        fakeStatusBar.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight));

        txtCountDownTime.setVisibility(View.GONE);
        settingCountDown();


    }

    private void getUniqueIdPhone() {

    }

    @OnClick(R.id.rootView)
    void onRedirect() {
        redirect();
    }


    public void redirect() {

        token = SharedPreferencesUtil.getInstance().getString("nodeToken", "");
        boolean check = SharedPreferencesUtil.getInstance().getBoolean("first", true);

        if (!check && token.length() > 10) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else if (token.length() > 10) {
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            finish();
        } else {
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            finish();
        }
    }


    private void settingCountDown() {
        final int count = 3;
        Disposable subscribe = Flowable.interval(0, 1, TimeUnit.SECONDS)
                .map(aLong -> count - aLong)
                .take(count + 1)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribe(aLong -> showCountDown(aLong.intValue()));

        addDisposable(subscribe);
    }


    public void showCountDown(int count) {
        txtCountDownTime.setText(count + "");
        if (count == 0) {
//            redirect();
        }
    }

    @Override
    protected void onDestroy() {
        if (loginScroll != null) {
            loginScroll.cancelTimer();
        }
        super.onDestroy();
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
}