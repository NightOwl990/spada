package com.lgkk.base_project.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;

import com.blankj.utilcode.util.Utils;


public class SizeUtils {
    public interface onGetSizeListener {
        void onGetSize(View view);
    }

    private SizeUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static float applyDimension(int i, float f, DisplayMetrics displayMetrics) {
        float f2;
        switch (i) {
            case 0:
                return f;
            case 1:
                f2 = displayMetrics.density;
                break;
            case 2:
                f2 = displayMetrics.scaledDensity;
                break;
            case 3:
                f *= displayMetrics.xdpi;
                f2 = 0.013888889f;
                break;
            case 4:
                f2 = displayMetrics.xdpi;
                break;
            case 5:
                f *= displayMetrics.xdpi;
                f2 = 0.03937008f;
                break;
            default:
                return 0.0f;
        }
        return f * f2;
    }

    public static int dp2px(float f) {
        return (int) ((f * Utils.getApp().getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int dp2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static void forceGetViewSize(final View view, final onGetSizeListener ongetsizelistener) {
        view.post(new Runnable() {
            public void run() {
                onGetSizeListener ongetsizelistener = null;
                if (ongetsizelistener != null) {
                    ongetsizelistener.onGetSize(view);
                }
            }
        });
    }


    public static int px2dp(float f) {
        return (int) ((f / Utils.getApp().getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2dp(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2sp(float f) {
        return (int) ((f / Utils.getApp().getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static int sp2px(float f) {
        return (int) ((f * Utils.getApp().getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static int sp2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

}
