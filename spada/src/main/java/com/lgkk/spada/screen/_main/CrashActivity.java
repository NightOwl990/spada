package com.lgkk.spada.screen._main;


import android.view.View;
import android.widget.Button;

import com.lgkk.spada.R;
import com.lgkk.spada.base.BaseSpadaActivity;

import butterknife.BindView;
import butterknife.OnClick;
import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import cat.ereza.customactivityoncrash.config.CaocConfig;

public class CrashActivity extends BaseSpadaActivity {


    @BindView(R.id.btnRestart)
    Button btnRestart;
    private CaocConfig config;

    @Override
    public int getLayoutId() {
        return R.layout.activity_crash;
    }


    @OnClick(R.id.btnRestart)
    public void onRestartApp() {
        CustomActivityOnCrash.restartApplication(CrashActivity.this, config);
    }

    //    show loading screen ---------
    @Override
    public void initDatas() {

    }

    //    onRefreshing data ------------

    @Override
    protected void onRefreshing() {

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
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void configViews() {
        initMarginTopToolbar(true);
        config = CustomActivityOnCrash.getConfigFromIntent(getIntent());

    }

}

