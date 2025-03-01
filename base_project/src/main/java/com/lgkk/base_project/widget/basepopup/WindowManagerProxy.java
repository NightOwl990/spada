package com.lgkk.base_project.widget.basepopup;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.lang.ref.WeakReference;


/**
 * Created by 大灯泡 on 2017/12/25.
 * <p>
 * 代理掉popup的windowmanager，在addView操作，拦截decorView的操作
 */
final class WindowManagerProxy implements WindowManager {
    private static final String TAG = "WindowManagerProxy";
    private static int statusBarHeight;
    private WindowManager mWindowManager;
    private WeakReference<PopupDecorViewProxy> mPopupDecorViewProxy;
    private WeakReference<BasePopupHelper> mPopupHelper;

    public WindowManagerProxy(WindowManager windowManager) {
        mWindowManager = windowManager;
    }

    @Override
    public Display getDefaultDisplay() {
        return mWindowManager == null ? null : mWindowManager.getDefaultDisplay();
    }

    @Override
    public void removeViewImmediate(View view) {
        if (mWindowManager == null || view == null) return;
        checkStatusBarHeight(view.getContext());
        if (isPopupInnerDecorView(view) && getPopupDecorViewProxy() != null) {
            PopupDecorViewProxy popupDecorViewProxy = getPopupDecorViewProxy();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if (!popupDecorViewProxy.isAttachedToWindow()) return;
            }
            mWindowManager.removeViewImmediate(popupDecorViewProxy);
            mPopupDecorViewProxy.clear();
            mPopupDecorViewProxy = null;
        } else {
            mWindowManager.removeViewImmediate(view);
        }
    }

    @Override
    public void addView(View view, ViewGroup.LayoutParams params) {
        if (mWindowManager == null || view == null) return;
        checkStatusBarHeight(view.getContext());
        if (isPopupInnerDecorView(view)) {
            /**
             * 此时的params是WindowManager.LayoutParams，需要留意强转问题
             * popup内部有scrollChangeListener，会有params强转为WindowManager.LayoutParams的情况
             */
            BasePopupHelper helper = getBasePopupHelper();

            applyHelper(params, helper);
            //添加popup主体
            final PopupDecorViewProxy popupDecorViewProxy = PopupDecorViewProxy.create(view.getContext(), helper);
            popupDecorViewProxy.addPopupDecorView(view, (LayoutParams) params);
            mPopupDecorViewProxy = new WeakReference<PopupDecorViewProxy>(popupDecorViewProxy);
            mWindowManager.addView(popupDecorViewProxy, fitLayoutParamsPosition(params));
        } else {
            mWindowManager.addView(view, params);
        }
    }

    private void applyHelper(ViewGroup.LayoutParams params, BasePopupHelper helper) {
        if (params instanceof LayoutParams && helper != null) {
            LayoutParams p = (LayoutParams) params;
            if (!helper.isInterceptTouchEvent()) {
                p.flags |= LayoutParams.FLAG_NOT_TOUCH_MODAL;
                p.flags |= LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
                if (!helper.isClipToScreen()) {
                    p.flags |= LayoutParams.FLAG_LAYOUT_NO_LIMITS;
                }
            }
            if (helper.isFullScreen()) {
                p.flags |= LayoutParams.FLAG_LAYOUT_IN_SCREEN;
                if (helper.isInterceptTouchEvent()) {
                    p.flags |= LayoutParams.FLAG_LAYOUT_NO_LIMITS;
                }
            }
        }
    }

    private ViewGroup.LayoutParams fitLayoutParamsPosition(ViewGroup.LayoutParams params) {
        if (params instanceof LayoutParams) {
            LayoutParams p = (LayoutParams) params;
            BasePopupHelper helper = getBasePopupHelper();
            if (helper != null) {
                if (helper.getShowCount() > 1) {
                    p.type = LayoutParams.TYPE_APPLICATION_SUB_PANEL;
                }
                if (helper.isInterceptTouchEvent()) {
                    //偏移交给PopupDecorViewProxy处理，此处固定为0
                    p.y = 0;
                    p.x = 0;
                } else {
                    if (helper.isShowAsDropDown()) {
                        Point offset = helper.getCachedOffset();
                        p.x += offset.x;
                        p.y += offset.y;
                        Log.d(TAG, "fitLayoutParamsPosition: x = " + p.x + "  y = " + p.y + "  offsetX = " + offset.x + "  offsetY = " + offset.y);
                        //实际内容页在decorViewProxy已经测量
                        p.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    }
                }
            }
            applyHelper(p, helper);
        }
        return params;
    }

    @Override
    public void updateViewLayout(View view, ViewGroup.LayoutParams params) {
        if (mWindowManager == null || view == null) return;
        checkStatusBarHeight(view.getContext());
        if (isPopupInnerDecorView(view) && getPopupDecorViewProxy() != null) {
            PopupDecorViewProxy popupDecorViewProxy = getPopupDecorViewProxy();
            mWindowManager.updateViewLayout(popupDecorViewProxy, fitLayoutParamsPosition(params));
        } else {
            mWindowManager.updateViewLayout(view, params);
        }
    }

    public void updateFocus(boolean focus) {
        if (mWindowManager != null && getPopupDecorViewProxy() != null) {
            PopupDecorViewProxy popupDecorViewProxy = getPopupDecorViewProxy();
            ViewGroup.LayoutParams params = popupDecorViewProxy.getLayoutParams();
            if (params instanceof LayoutParams) {
                if (focus) {
                    ((LayoutParams) params).flags &= ~LayoutParams.FLAG_NOT_FOCUSABLE;
                } else {
                    ((LayoutParams) params).flags |= LayoutParams.FLAG_NOT_FOCUSABLE;
                }
            }
            mWindowManager.updateViewLayout(popupDecorViewProxy, params);
        }
    }

    public void update() {
        if (mWindowManager == null) return;
        if (getPopupDecorViewProxy() != null) {
            getPopupDecorViewProxy().updateLayout();
        }
    }

    @Override
    public void removeView(View view) {
        if (mWindowManager == null || view == null) return;
        checkStatusBarHeight(view.getContext());
        if (isPopupInnerDecorView(view) && getPopupDecorViewProxy() != null) {
            PopupDecorViewProxy popupDecorViewProxy = getPopupDecorViewProxy();
            mWindowManager.removeView(popupDecorViewProxy);
            mPopupDecorViewProxy.clear();
            mPopupDecorViewProxy = null;
        } else {
            mWindowManager.removeView(view);
        }
    }

    public void clear() {
        try {
            removeViewImmediate(mPopupDecorViewProxy.get());
            mPopupDecorViewProxy.clear();
        } catch (Exception e) {
            //no print
        }
    }

    private boolean isPopupInnerDecorView(View v) {
        if (v == null) return false;
        String viewSimpleClassName = v.getClass().getSimpleName();
        return TextUtils.equals(viewSimpleClassName, "PopupDecorView") || TextUtils.equals(viewSimpleClassName, "PopupViewContainer");
    }

    private PopupDecorViewProxy getPopupDecorViewProxy() {
        if (mPopupDecorViewProxy == null) return null;
        return mPopupDecorViewProxy.get();
    }


    private BasePopupHelper getBasePopupHelper() {
        if (mPopupHelper == null) return null;
        return mPopupHelper.get();
    }

    void bindPopupHelper(BasePopupHelper helper) {
        mPopupHelper = new WeakReference<BasePopupHelper>(helper);
    }

    private void checkStatusBarHeight(Context context) {
        if (statusBarHeight != 0 || context == null) return;
        int result = 0;
        //获取状态栏高度的资源id
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        statusBarHeight = result;
    }

}
