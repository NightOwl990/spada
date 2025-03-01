package com.lgkk.spada.screen._main;

import android.Manifest;
import android.annotation.SuppressLint;
import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.DeviceUtils;
import com.lgkk.base_project.base.BaseRequest;
import com.lgkk.base_project.base.Constants;
import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.base_project.liblary.StatusBarUtil;
import com.lgkk.base_project.utils.RxUtils;
import com.lgkk.base_project.utils.SharedPreferencesUtil;
import com.lgkk.spada.BuildConfig;
import com.lgkk.spada.R;

import com.lgkk.spada.base.BaseSpadaActivity;

import com.lgkk.spada.base.ViewModelFactory;


import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;

public class SplashScreenV2Activity extends BaseSpadaActivity implements OnItemRvClickListener<Object> {
    private final static int REQUEST_PHONE_STATE = 1234;

    @BindView(R.id.rootView)
    LinearLayout rootView;
    @BindView(R.id.imgBackground)
    ImageView imgBackground;
    @BindView(R.id.txtCountDownTime)
    TextView txtCountDownTime;

    private SpadaViewModel viewModel;
    @Inject
    ViewModelFactory viewModelFactory;
    Disposable subscription;
    protected BaseRequest baseRequest;

    private static final String SHOP_ID = "shopId";
    private String shopId;

    public static void startActivity(Context context, String shopId) {
        Intent intent = new Intent(context, SplashScreenV2Activity.class);
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
        return R.layout.activity_splash_screen_v2;
    }

    //    show loading screen ---------
    @Override
    public void initDatas() {
        appComponent().doInjection(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SpadaViewModel.class);
        initMarginTopToolbar(false);
        observableViewModel();

        showLoadingScreen(rootView);
        onRefreshing();
    }

    //    Setting view model ------
    private void observableViewModel() {

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
        setSystemInfo();
        requestReadPhoneState();
        StatusBarUtil.setTransparent(this);
        settingCountDown();
        initWidget();
        closeLoadingScreen();
    }

    private void setSystemInfo() {
        baseRequest = new BaseRequest();
        baseRequest.setModel(DeviceUtils.getModel());
        baseRequest.setApp_name(getString(R.string.app_name));
        baseRequest.setVersion(BuildConfig.VERSION_NAME);
        baseRequest.setPlatform("android");
        baseRequest.setCurrent_city_id(SharedPreferencesUtil.getInstance().getString(Constants.CURRENT_CITY_ID, "worldwide"));
        baseRequest.setLat(SharedPreferencesUtil.getInstance().getString(Constants.CURRENT_LAT, "0.0"));
        baseRequest.setLng(SharedPreferencesUtil.getInstance().getString(Constants.CURRENT_LNG, "0.0"));
        baseRequest.setOs_version(DeviceUtils.getSDKVersionName());
        baseRequest.setManufacturer(DeviceUtils.getManufacturer());
        baseRequest.setAndroid_device_id(DeviceUtils.getAndroidID());
        baseRequest.setDevice_id("");
        baseRequest.setUuid("");
        SharedPreferencesUtil.getInstance().putString(Constants.BASE_REQUEST, JSON.toJSONString(baseRequest));
    }

    private void requestReadPhoneState() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE},REQUEST_PHONE_STATE);
        } else getUID();
    }


    private void getUID() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
//        String m_deviceId = telephonyManager.getDeviceId();
//        String tmSerial = "" + telephonyManager.getSimSerialNumber();
//        UUID deviceUuid = new UUID(DeviceUtils.getAndroidID().hashCode(), tmSerial.hashCode());
//        baseRequest.setUuid(deviceUuid.toString());
//        baseRequest.setDevice_id(m_deviceId);
//        SharedPreferencesUtil.getInstance().putString(Constants.BASE_REQUEST, JSON.toJSONString(baseRequest));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PHONE_STATE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getUID();
                }
                break;
        }
    }

    private void initWidget() {
//        RxView.clicks(rootView)
//                .throttleFirst(3, TimeUnit.SECONDS)//3秒内响应第一次发射数据
//                .compose(bindToLifecycle())
//                .subscribe(object -> redirect());
    }

    public void showCountDown(int count) {
        txtCountDownTime.setText(count + "");

        if (count == 0) {
            redirect();
        }
    }

    @OnClick(R.id.rootView)
    void onBack() {
        redirect();
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

    private void redirect() {
        token = SharedPreferencesUtil.getInstance().getString("nodeToken", "");
        boolean isFirstLoad = SharedPreferencesUtil.getInstance().getBoolean("first", true);

        if (!isFirstLoad && token.length() > 10) {
            Intent intent = new Intent(this, WelcomeActivity.class);
            startActivity(intent);
            finish();
        } else if (token.length() > 10) {
            startActivity(new Intent(SplashScreenV2Activity.this, WelcomeActivity.class));
            finish();
        } else {
            startActivity(new Intent(SplashScreenV2Activity.this, WelcomeActivity.class));
            finish();
        }
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
}


