package com.lgkk.base_project.dagger2.component;

import android.app.Activity;

import com.lgkk.base_project.dagger2.module.ActivityModule;

import com.lgkk.base_project.dagger2.scope.ActivityScope;



import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

//    MVP -------------
    Activity getActivity();

}
