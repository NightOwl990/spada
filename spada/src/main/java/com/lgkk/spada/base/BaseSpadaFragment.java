package com.lgkk.spada.base;

import android.os.Bundle;
import android.view.View;

import com.lgkk.base_project.base.BaseFragment;
import com.lgkk.spada.dagger2.DaggerSpadaComponent;
import com.lgkk.spada.dagger2.SpadaComponent;
import com.lgkk.spada.dagger2.SpadaModule;



public abstract class BaseSpadaFragment extends BaseFragment {


    protected SpadaComponent spadaComponent;

    public SpadaComponent appComponent() {
        if (spadaComponent == null) {
            spadaComponent = DaggerSpadaComponent.builder()
                    .spadaModule(new SpadaModule(getContext()))
                    .build();
        }
        return spadaComponent;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
