package com.lgkk.spada.base;

import android.app.Activity;
import android.app.Application;
import androidx.lifecycle.LifecycleObserver;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.crashlytics.android.Crashlytics;
import com.facebook.stetho.Stetho;
import com.lgkk.base_project.litepal.LitePal;
import com.lgkk.base_project.utils.SharedPreferencesUtil;
import com.lgkk.spada.BuildConfig;
import com.lgkk.spada.firebasenotification.Config;
import com.lgkk.spada.screen._main.SplashScreenV2Activity;
import com.lgkk.spada.screen._main.CrashActivity;
import com.zing.zalo.zalosdk.oauth.ZaloSDKApplication;

import java.util.HashSet;
import java.util.Set;

import cat.ereza.customactivityoncrash.config.CaocConfig;
import io.fabric.sdk.android.Fabric;

public class MyApplicationLike extends Application implements LifecycleObserver {
    private static final String TAG = "Tinker.MyApplicationLike";
    private static MyApplicationLike myApplicationLike;

    private Set<Activity> allActivities;
    public static Resources resources;

//    private FirebaseAnalytics analytics;
    public static MyApplicationLike getApplication(Context context) {
        return myApplicationLike;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        resources = getResources();
        myApplicationLike = this;

//        FirebaseApp.initializeApp(this);
//        Bundle params = new Bundle();
//        params.putString(FirebaseAnalytics.Param.ITEM_ID, "id");
//        params.putString(FirebaseAnalytics.Param.ITEM_NAME, "name");
//        params.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
//        analytics = FirebaseAnalytics.getInstance(this);
//        analytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, params);


        if (BuildConfig.DEBUG) {
//            LeakCanary.install(getApplication());
            Stetho.initialize(
                    Stetho.newInitializerBuilder(this)
                            .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                            .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                            .build());
        }

//        if (!BuildConfig.DEBUG) {
//            Fabric.with(this, new Crashlytics());
//        }
        SharedPreferencesUtil.init(this, Config.SHARED_PREF, 0);
        MultiDex.install(this);
        ZaloSDKApplication.wrap(this);
//        Realm.init(this);
//        settingCustomCrashLayout();

        if (BuildConfig.DEBUG) {           // These two lines must be written before init, otherwise these configurations will be invalid in the init process
            ARouter.openLog();     // Print log
            ARouter.openDebug();   // Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)
        }
        ARouter.init(this);

        AndroidNetworking.initialize(this);
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
        }
    }

    public SQLiteDatabase getLitePalDatabase() {
        return LitePal.getDatabase();
    }

    private void settingCustomCrashLayout() {
        CaocConfig.Builder.create()
                .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT) //default: CaocConfig.BACKGROUND_MODE_SHOW_CUSTOM
                .enabled(true)
                .trackActivities(true) //default: false
                .restartActivity(SplashScreenV2Activity.class) //default: null (your app's launch activity)
                .errorActivity(CrashActivity.class) //default: null (default error activity)
                //default: null
                .apply();
    }


    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    /**
     * 移除Activity
     *
     * @param act act
     */
    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    /**
     * 退出应用
     */
    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);

    }



}

