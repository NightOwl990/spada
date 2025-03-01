package com.lgkk.spada.firebasenotification;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.util.Log;

/**
 * Created by Ravi Tamada on 08/08/16.
 * www.androidhive.info
 */
//public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
//    private static final String TAG = MyFirebaseInstanceIDService.class.getSimpleName();
//
//    @Override
//    public void onTokenRefresh() {
//        super.onTokenRefresh();
//        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
////        String refreshedToken = "fdHIRYdgB6c:APA91bGlBiVzG5lWeAlalzEM7AjpDdJrmk9bySr6dl9PrH0DR38xrIA0OxSojxzThmHbvOHtLEvNmsHH21DIIjqg3HLc_Meh_997oLM-cRCmKCfmZHz4Kd_FuiwHkNBCCIG6LLhQn1tK";
//        // Saving reg id to shared preferences
////        String refreshedToken =   SharedPreferencesUtil.getToken(this);
//        Log.e(TAG, "Firebase reg id2: " + refreshedToken);
//        storeRegIdInPref(refreshedToken);
//
//        // sending reg id to your server
//        sendRegistrationToServer(refreshedToken);
//
//        // Notify UI that registration has completed, so the progress indicator can be hidden.
//        Intent registrationComplete = new Intent(Config.REGISTRATION_COMPLETE);
//        registrationComplete.putExtra("token", refreshedToken);
//        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
//    }
//
//    private void sendRegistrationToServer(final String token) {
//        // sending gcm token to server
//        Log.e(TAG, "sendRegistrationToServer: " + token);
//    }
//
//    private void storeRegIdInPref(String token) {
//        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
//        SharedPreferences.Editor editor = pref.edit();
//        editor.putString("regId", token);
//        editor.commit();
//    }
//}

