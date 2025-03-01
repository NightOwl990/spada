package com.lgkk.module_home.dagger2;

import android.content.Context;

import com.lgkk.module_home.screen.TestActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {HomeModule.class})
public interface HomeComponent {

    Context getContext();

    void doInjection(TestActivity testActivity);
}


