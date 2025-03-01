package com.lgkk.base_project.dagger2.component;

import android.app.Activity;

import com.lgkk.base_project.dagger2.module.FragmentModule;
import com.lgkk.base_project.dagger2.scope.FragmentScope;


import dagger.Component;

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * @date 创建时间：2017/4/27 19:30
 * 描述:FragmentComponent
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();
}
