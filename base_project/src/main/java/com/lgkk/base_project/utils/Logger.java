package com.lgkk.base_project.utils;

import android.util.Log;

public class Logger {

    public static int err(final String TAG, final String message) {
        return android.util.Log.e(TAG, attachThreadId(message));
    }

    public static int err(final String TAG, final String message, Throwable throwable) {
        return android.util.Log.e(TAG, attachThreadId(message), throwable);
    }

    public static int w(final String TAG, final String message) {
        return android.util.Log.w(TAG, attachThreadId(message));
    }

    public static int inf(final String TAG, final String message) {
        return android.util.Log.i(TAG, attachThreadId(message));
    }

    public static int d(final String TAG, final String message) {
        return android.util.Log.d(TAG, attachThreadId(message));
    }

    public static int v(final String TAG, final String message) {
        return Log.v(TAG, attachThreadId(message));
    }

    private static String attachThreadId(String str) {
        return "" + Thread.currentThread().getId() + " " + str;
    }

}
