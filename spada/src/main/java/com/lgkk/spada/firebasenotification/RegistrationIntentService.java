//package com.lgkk.spada.firebasenotification;
//
//import android.app.IntentService;
//import android.content.Intent;
//import android.util.Log;
//
//import com.lgkk.base_project.utils.SharedPreferencesUtil;
//
//public class RegistrationIntentService extends IntentService {
//
//    private static final String TAG = "RegIntentService";
//
//
//    public RegistrationIntentService() {
//        super(TAG);
//    }
//
//    @Override
//    protected void onHandleIntent(Intent intent) {
//        String token = FirebaseInstanceId.getInstance().getToken();
//        Log.d(TAG, "FCM Registration Token: " + token);
//        SharedPreferencesUtil.getInstance().putString("device_token", token);
//    }
//}