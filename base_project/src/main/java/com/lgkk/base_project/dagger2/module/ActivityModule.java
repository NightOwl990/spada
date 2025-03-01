package com.lgkk.base_project.dagger2.module;

import android.app.Activity;
import androidx.fragment.app.Fragment;

import com.lgkk.base_project.dagger2.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private Activity mActivity;

    private Fragment mFragment;

    public ActivityModule(Fragment fragment) {
        this.mFragment = fragment;
    }

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return mActivity;
    }
}
