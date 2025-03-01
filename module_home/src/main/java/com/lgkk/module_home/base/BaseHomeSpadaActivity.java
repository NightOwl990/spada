package com.lgkk.module_home.base;

import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.lgkk.base_project.base.BaseActivity;

import com.lgkk.module_home.dagger2.DaggerHomeComponent;
import com.lgkk.module_home.dagger2.HomeComponent;
import com.lgkk.module_home.dagger2.HomeModule;


public abstract class BaseHomeSpadaActivity extends BaseActivity {


    protected HomeComponent homeComponent;
    protected FirebaseAnalytics mFirebaseAnalytics;

    public HomeComponent homeComponent() {
        if (homeComponent == null) {
            homeComponent = DaggerHomeComponent.builder()
                    .homeModule(new HomeModule(this))
                    .build();
        }
        return homeComponent;
    }

    protected void initMarginTopToolbar(boolean isMarginTopToolbar) {
//        mainView.setFitsSystemWindows(isMarginTopToolbar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        super.onCreate(savedInstanceState);
    }
}
