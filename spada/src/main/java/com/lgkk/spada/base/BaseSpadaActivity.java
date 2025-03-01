package com.lgkk.spada.base;

import android.os.Bundle;
import android.view.View;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.lgkk.base_project.base.BaseActivity;
import com.lgkk.spada.R;
import com.lgkk.spada.dagger2.DaggerSpadaComponent;
import com.lgkk.spada.dagger2.SpadaComponent;
import com.lgkk.spada.dagger2.SpadaModule;


import butterknife.BindView;


public abstract class BaseSpadaActivity extends BaseActivity {

    @BindView(R.id.mainView)
    View mainView;

    protected SpadaComponent spadaComponent;
    protected FirebaseAnalytics mFirebaseAnalytics;

    public SpadaComponent appComponent() {
        if (spadaComponent == null) {
            spadaComponent = DaggerSpadaComponent.builder()
                    .spadaModule(new SpadaModule(this))
                    .build();
        }
        return spadaComponent;
    }

    protected void initMarginTopToolbar(boolean isMarginTopToolbar) {
        mainView.setFitsSystemWindows(isMarginTopToolbar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        super.onCreate(savedInstanceState);
    }
}
