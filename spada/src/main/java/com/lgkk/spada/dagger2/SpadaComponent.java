package com.lgkk.spada.dagger2;

import android.content.Context;


import com.lgkk.spada.screen._account.activity.LoginV2Activity;
import com.lgkk.spada.screen._account.activity.RegisterActivity;
import com.lgkk.spada.screen._account.fragment.LoginFragment;
import com.lgkk.spada.screen._account.fragment.RegisterInfoFragment;
import com.lgkk.spada.screen._account.fragment.RegisterPhoneFragment;
import com.lgkk.spada.screen._main.SplashScreenV2Activity;
import com.lgkk.spada.screen._main.MainActivity;
import com.lgkk.spada.screen._main.WelcomeActivity;
import com.lgkk.spada.screen.home.activity.FilterHospitalListActivity;
import com.lgkk.spada.screen.home.activity.FlashSaleActivity;
import com.lgkk.spada.screen.home.activity.HospitalDetailActivity;
import com.lgkk.spada.screen.home.fragment.FlashSaleByHourFragment;
import com.lgkk.spada.screen.notification.fragment.NotificationFragment;
import com.lgkk.spada.screen.service.activity.CartActivity;
import com.lgkk.spada.screen.service.activity.DoctorInfoActivity;
import com.lgkk.spada.screen.service.activity.FilterServiceListActivity;
import com.lgkk.spada.screen.service.activity.ServiceCategoryActivity;
import com.lgkk.spada.screen.service.activity.ServiceDetailActivity;
import com.lgkk.spada.screen.service.fragment.ServiceCategoryDailyFragment;
import com.lgkk.spada.screen.service.fragment.ServiceCategoryProductFragment;
import com.lgkk.spada.screen.service.fragment.SuggestServiceFragment;
import com.lgkk.spada.screen.service.fragment.SuggestTabFragment;
import com.lgkk.spada.screen.home.fragment.ShopV2Fragment;
import com.lgkk.spada.screen.user.fragment.UserV2Fragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {SpadaModule.class})
public interface SpadaComponent {
    Context getContext();


    //    MVVM Activity  ------------------
    void doInjection(WelcomeActivity welcomeActivity);
    void doInjection(HospitalDetailActivity hospitalDetailActivity);
    void doInjection(FilterHospitalListActivity filterHospitalListActivity);
    void doInjection(FlashSaleActivity flashSaleActivity);
    void doInjection(MainActivity mainActivity);
    void doInjection(ServiceDetailActivity serviceDetailActivity);
    void doInjection(ServiceCategoryActivity serviceCategoryActivity);
    void doInjection(DoctorInfoActivity doctorInfoActivity);
    void doInjection(FilterServiceListActivity doctorInfoActivity);
    void doInjection(SplashScreenV2Activity splashScreenV2Activity);
    void doInjection(LoginV2Activity loginV2Activity);
    void doInjection(RegisterActivity registerActivity);
    void doInjection(CartActivity cartActivity);

    //    MVVM Fragment ----------
    void doInjection(SuggestServiceFragment suggestServiceFragment);
    void doInjection(FlashSaleByHourFragment flashSaleByHourFragment);
    void doInjection(SuggestTabFragment suggestTabFragment);
    void doInjection(ServiceCategoryDailyFragment serviceCategoryDailyFragment);
    void doInjection(ServiceCategoryProductFragment serviceCategoryProductFragment);
    void doInjection(UserV2Fragment userV2Fragment);
    void doInjection(ShopV2Fragment shopV2Fragment);
    void doInjection(NotificationFragment notificationFragment);
    void doInjection(RegisterInfoFragment registerInfoFragment);
    void doInjection(RegisterPhoneFragment registerPhoneFragment);
    void doInjection(LoginFragment loginFragment);
}
