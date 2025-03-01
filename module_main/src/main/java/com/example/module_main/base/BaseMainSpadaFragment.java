package com.example.module_main.base;

import android.os.Bundle;
import android.view.View;

import com.example.module_main.dagger2.DaggerMainComponent;
import com.example.module_main.dagger2.MainComponent;
import com.example.module_main.dagger2.MainModule;
import com.lgkk.base_project.base.BaseFragment;


public abstract class BaseMainSpadaFragment extends BaseFragment {

    protected MainComponent mainComponent;

    public MainComponent appComponent() {
        if (mainComponent == null) {
            mainComponent = DaggerMainComponent.builder()
                    .mainModule(new MainModule(getContext()))
                    .build();
        }
        return mainComponent;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
