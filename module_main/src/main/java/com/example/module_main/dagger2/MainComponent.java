package com.example.module_main.dagger2;

import android.content.Context;


import com.example.module_main.screen.Main2Activity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MainModule.class})
public interface MainComponent {

    Context getContext();

    void doInjection(Main2Activity main2Activity);
}


