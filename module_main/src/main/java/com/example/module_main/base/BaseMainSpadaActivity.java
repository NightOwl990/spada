package com.example.module_main.base;

import android.os.Bundle;

import com.example.module_main.dagger2.DaggerMainComponent;
import com.example.module_main.dagger2.MainComponent;
import com.example.module_main.dagger2.MainModule;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.lgkk.base_project.base.BaseActivity;


public abstract class BaseMainSpadaActivity extends BaseActivity {


    protected MainComponent mainComponent;
    protected FirebaseAnalytics mFirebaseAnalytics;

    public MainComponent appComponent() {
        if (mainComponent == null) {
            mainComponent = DaggerMainComponent.builder()
                    .mainModule(new MainModule(this))
                    .build();
        }
        return mainComponent;
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
