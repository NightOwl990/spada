package com.lgkk.base_project.dagger2.component;

import android.content.Context;

import com.lgkk.base_project.dagger2.module.ApiModule;
import com.lgkk.base_project.dagger2.module.AppModule;


import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponent {
    Context getContext();

    //    MVVM Activity  ------------------

}
