package com.lgkk.module_home.base;

import android.os.Bundle;
import android.view.View;

import com.lgkk.base_project.base.BaseFragment;
import com.lgkk.module_home.dagger2.DaggerHomeComponent;
import com.lgkk.module_home.dagger2.HomeComponent;
import com.lgkk.module_home.dagger2.HomeModule;


public abstract class BaseHomeSpadaFragment extends BaseFragment {

    protected HomeComponent homeComponent;

    public HomeComponent homeComponent() {
        if (homeComponent == null) {
            homeComponent = DaggerHomeComponent.builder()
                    .homeModule(new HomeModule(getContext()))
                    .build();
        }
        return homeComponent;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
