package com.lgkk.base_project.base;//package com.rize.ui.base;

import android.app.Activity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lgkk.base_project.R;
import com.lgkk.base_project.base.listener.OnSkeletonViewClickListener;
import com.lgkk.base_project.liblary.StatusBarUtil;
import com.lgkk.base_project.utils.NetworkUtils;
import com.lgkk.base_project.utils.SharedPreferencesUtil;
import com.lgkk.base_project.utils.TinyDB;
import com.lgkk.base_project.widget.LoadingDialog;
import com.lgkk.base_project.widget.overscroll.HorizontalOverScrollBounceEffectDecorator;
import com.lgkk.base_project.widget.overscroll.adapters.RecyclerViewOverScrollDecorAdapter;
import com.lgkk.base_project.widget.skeleton.Skeleton;
import com.lgkk.base_project.widget.skeleton.SkeletonScreen;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public abstract class BaseActivity extends RxAppCompatActivity implements BaseContract.BaseView, OnSkeletonViewClickListener, LifecycleObserver {

    protected String TAG = getClass().getSimpleName();
    protected int statusBarColor = 0;
    protected View statusBarView = null;
    protected CompositeDisposable mDisposable;
    protected Disposable disposable1, disposable2;
    protected TinyDB tinyDB;
    protected boolean isRefreshing = false;
    protected boolean isErr = false;
    protected boolean isResume = false;
    protected boolean isEndLoading = false;
    protected int mCurrentCounter;
    protected int limit = Constants.MAX_ITEM_LOAD_FIRST_TIME;
    protected int start = 0;
    protected String token;
    protected RxPermissions rxPermissions;
    protected boolean disableInitStatusBar = false;
    protected boolean showTip = false;
    protected SkeletonScreen skeletonScreen;
    protected boolean isErrorData = false;
    Unbinder unbinder;
    String typeApp = "";
    private LoadingDialog dialog;//Dialog loading
    private boolean isClick = false;
    private float downX;
    private float downY;
    private float moveX;
    private float moveY;
    protected long startTime;
    protected long endTime;
    private ServiceConnection serviceConnection;
    protected String baseRequestJson;

    private void trackingAnalytics(int type) {
        switch (type) {
            case 0:
                typeApp = "create";
                break;
            case 1:
                typeApp = "destroy";
                break;
            case 2:
                typeApp = "background";
                break;
        }
        String userIdd = SharedPreferencesUtil.getInstance().getString("userId", "");
        if (userIdd.length() > 0) {
//            if (presenter != null)
//                presenter.postTrackingV2(token, TAG, startTime, Calendar.getInstance().getTimeInMillis(), "android");
//            TrackingUser trackingUser = new TrackingUser(userIdd, Calendar.getInstance().getTimeInMillis(), this.getClass().getSimpleName(), typeApp);
//            String trackingString = JSON.toJSONString(trackingUser);

        }
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private void onAppBackgrounded() {
//        trackingAnalytics(2);
    }


    @Override
    protected void onDestroy() {
        trackingAnalytics(1);
        avoidSemClipBoardManagerError();
        unbinder.unbind();
        if (mDisposable != null) {
            mDisposable.dispose();
        }
        super.onDestroy();
    }


    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    protected void startActivity(Class<? extends AppCompatActivity> activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        token = SharedPreferencesUtil.getInstance().getString("nodeToken");
        baseRequestJson = SharedPreferencesUtil.getInstance().getString(Constants.BASE_REQUEST, "{}");
        startTime = Calendar.getInstance().getTimeInMillis();
        unbinder = ButterKnife.bind(this);
        initStatusBar();
        initDataGetFromIntent(savedInstanceState);
        rxPermissions = new RxPermissions(this);
        tinyDB = new TinyDB(this);

        initDatas();
        configViews();
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
//        bindPlayService();
//        trackingAnalytics(0);
    }


    protected void initDataGetFromIntent(Bundle savedInstanceState) {
    }

    protected void avoidSemClipBoardManagerError() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Build.MANUFACTURER.equals("samsung")) {
                Object systemService = getSystemService(Class.forName("com.samsung.android.content.clipboard.SemClipboardManager"));
                Field context = systemService.getClass().getDeclaredField("context");
                context.setAccessible(true);
                context.set(systemService, getApplicationContext());
            }
        } catch (Exception e) { //ignored }
        }
    }

    protected void initStatusBar() {
        if (!disableInitStatusBar) {
            Window w = getWindow();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Window window = getWindow();
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(getColor(R.color.white));
                StatusBarUtil.setLightMode(this);
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    window.setStatusBarColor(getResources()
                            .getColor(R.color.transparent_20));

                }
            }
        }
    }

//    protected void initMarginTopToolbar(boolean isMarginTopToolbar) {
//        mainView.setFitsSystemWindows(isMarginTopToolbar);
//    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    protected void addDisposable(Disposable d) {
        if (mDisposable == null) {
            mDisposable = new CompositeDisposable();
        }
        mDisposable.add(d);
    }

    protected abstract int getLayoutId();

    protected abstract void initDatas();

    protected abstract void onRefreshing();

    protected abstract void configViews();

    // dialog
    protected LoadingDialog getDialog() {
        if (dialog == null) {
            dialog = LoadingDialog.instance(this);
            dialog.setCancelable(true);
        }
        return dialog;
    }

    protected void hideDialog() {
        if (dialog != null)
            dialog.hide();
    }

    protected void showDialog() {
        getDialog().show();
    }

    protected void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    protected void initSettingGridRv(RecyclerView recyclerView, BaseQuickAdapter adapter, int spanCount, boolean hasFixedSize, boolean disableNested) {
        recyclerView.setHasFixedSize(hasFixedSize);
        recyclerView.setNestedScrollingEnabled(disableNested);
        GridLayoutManager gridManager = new GridLayoutManager(this, spanCount);
        recyclerView.setLayoutManager(gridManager);
        recyclerView.setAdapter(adapter);
    }

    protected void initSettingLinearRv(RecyclerView recyclerView, BaseQuickAdapter adapter, boolean hasFixedSize, boolean disableNested) {
        recyclerView.setHasFixedSize(hasFixedSize);
        recyclerView.setNestedScrollingEnabled(disableNested);
        LinearLayoutManager lnManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lnManager);
        recyclerView.setAdapter(adapter);
    }

    protected void initSettingLinearHorizontalRv(RecyclerView recyclerView, BaseQuickAdapter adapter, boolean hasFixedSize, boolean disableNested) {
        recyclerView.setHasFixedSize(hasFixedSize);
        recyclerView.setNestedScrollingEnabled(disableNested);
        LinearLayoutManager lnManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(lnManager);
        recyclerView.setAdapter(adapter);
    }

    protected void initDebounceSettingLinearHorizontalRv(RecyclerView recyclerView, BaseQuickAdapter adapter, boolean hasFixedSize, boolean disableNested) {
        recyclerView.setHasFixedSize(hasFixedSize);
        recyclerView.setNestedScrollingEnabled(disableNested);
        LinearLayoutManager lnManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(lnManager);
        recyclerView.setAdapter(adapter);
        new HorizontalOverScrollBounceEffectDecorator(new RecyclerViewOverScrollDecorAdapter(recyclerView),
                6f, // Default is 3
                HorizontalOverScrollBounceEffectDecorator.DEFAULT_TOUCH_DRAG_MOVE_RATIO_BCK,
                HorizontalOverScrollBounceEffectDecorator.DEFAULT_DECELERATE_FACTOR);
//        OverScrollDecoratorHelper.setUpOverScroll(rvTitleFlashSale, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);

    }

    protected void hideStatusBar() {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
        if (statusBarView != null) {
            statusBarView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    protected void showStatusBar() {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
        if (statusBarView != null) {
            statusBarView.setBackgroundColor(statusBarColor);
        }
    }

    protected void showTipViewRepeat(String tip, TextView txtTip) {
        showTip = true;
        txtTip.setText(tip);
        if (disposable1 != null) disposable1.dispose();
        if (showTip) {
            txtTip.setVisibility(View.VISIBLE);
            Animation mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                    1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
            mShowAction.setDuration(200);
            txtTip.startAnimation(mShowAction);
            mShowAction.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                public void onAnimationEnd(Animation animation) {
                    disposable1 = Observable.just(0).delay(2000, TimeUnit.MILLISECONDS)
                            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                                    (beans) -> {

                                        Animation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                                                0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                                                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                                                1.0f);
                                        mHiddenAction.setDuration(200);
                                        txtTip.startAnimation(mHiddenAction);
                                        mHiddenAction.setAnimationListener(new Animation.AnimationListener() {
                                            @Override
                                            public void onAnimationStart(Animation animation) {
                                            }

                                            @Override
                                            public void onAnimationEnd(Animation animation) {
                                                showTip = false;
                                                disposable1.dispose();
                                                txtTip.setVisibility(View.GONE);
                                            }

                                            @Override
                                            public void onAnimationRepeat(Animation animation) {

                                            }
                                        });
                                    }
                                    ,
                                    (e) -> {
                                    }
                            );
                    addDisposable(disposable1);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
    }

    protected void showTipViewAndDelayClose(String tip, TextView txtTip) {
        showTip = !showTip;
        txtTip.setText(tip);
        if (disposable2 != null) disposable2.dispose();
        if (showTip) {
            txtTip.setVisibility(View.VISIBLE);
            Animation mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                    1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
            mShowAction.setDuration(200);
            txtTip.startAnimation(mShowAction);
            mShowAction.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    disposable2 = Observable.just(0).delay(2000, TimeUnit.MILLISECONDS)
                            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                                    (beans) -> {
                                        Animation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                                                0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                                                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                                                1.0f);
                                        mHiddenAction.setDuration(200);
                                        txtTip.startAnimation(mHiddenAction);
                                        mHiddenAction.setAnimationListener(new Animation.AnimationListener() {
                                            @Override
                                            public void onAnimationStart(Animation animation) {
                                            }

                                            @Override
                                            public void onAnimationEnd(Animation animation) {
                                                showTip = false;
                                                disposable2.dispose();
                                                txtTip.setVisibility(View.GONE);
                                            }

                                            @Override
                                            public void onAnimationRepeat(Animation animation) {

                                            }
                                        });
                                    }
                                    ,
                                    (e) -> {
                                    }
                            );
                    addDisposable(disposable2);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        } else {
            Animation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                    0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                    1.0f);
            mHiddenAction.setDuration(200);
            txtTip.startAnimation(mHiddenAction);
            mHiddenAction.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    showTip = false;
                    disposable2.dispose();
                    txtTip.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
    }

    public void showLoadingScreen(View rootView) {
        if (skeletonScreen != null) {
            skeletonScreen.hide();
        }
        skeletonScreen = Skeleton.bind(rootView)
                .load(R.layout.common_loading_view)
                .duration(1500)
                .color(R.color.shimmer_color_for_image)
                .show();
    }

    public void closeLoadingScreen() {
        isErrorData = false;
        if (skeletonScreen != null) {
            skeletonScreen.hide();
        }
    }

    public void showErrorScreen(View rootView) {
        if (skeletonScreen != null) {
            skeletonScreen.hide();
        }
        skeletonScreen = Skeleton.bind(rootView)
                .load(R.layout.common_empty_view, this)
                .duration(0)
                .color(R.color.shimmer_color_for_image)
                .show();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();
        if (v != null && !v.getClass().getName().startsWith("android.webkit.")) {
            if (v instanceof EditText) {
                switch (ev.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (!isClick) {
                            downX = ev.getX();
                            downY = ev.getY();
                            isClick = true;
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        moveX = ev.getX();
                        moveY = ev.getY();
                        if (moveX != downX || moveY != downY) {
                            isClick = false;
                        }

                        break;
                    case MotionEvent.ACTION_UP:
                        if (isClick) {
                            int scrcoords[] = new int[2];
                            v.getLocationOnScreen(scrcoords);
                            float x = ev.getRawX() + v.getLeft() - scrcoords[0];
                            float y = ev.getRawY() + v.getTop() - scrcoords[1];
                            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom())
                                hideKeyboard(this);
                        }
                        break;
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    protected void hideKeyboard(Activity activity) {
        if (activity != null && activity.getWindow() != null && activity.getWindow().getDecorView() != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    public void settingLinearRecyclerView(RecyclerView rv, BaseQuickAdapter adapter, boolean isHasFixSize, boolean isEnableNestedScrollView) {
        rv.setHasFixedSize(isHasFixSize);
        rv.setNestedScrollingEnabled(isEnableNestedScrollView);
        LinearLayoutManager lnLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(lnLayoutManager);
        rv.setAdapter(adapter);
    }

    public void settingGridRecyclerView(RecyclerView rv, BaseQuickAdapter adapter, int spanCount, boolean isHasFixSize, boolean isEnableNestedScrollView) {
        rv.setHasFixedSize(isHasFixSize);
        rv.setNestedScrollingEnabled(isEnableNestedScrollView);
        GridLayoutManager gridlayoutManager = new GridLayoutManager(this, spanCount);
        rv.setLayoutManager(gridlayoutManager);
        rv.setAdapter(adapter);
    }

    public void settingLinearRecyclerViewWithoutAnimation(RecyclerView rv, BaseQuickAdapter adapter, int spanCount, boolean isHasFixSize, boolean isEnableNestedScrollView) {
        rv.setHasFixedSize(isHasFixSize);
        rv.setNestedScrollingEnabled(isEnableNestedScrollView);
        rv.setItemAnimator(null);
        LinearLayoutManager gridlayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(gridlayoutManager);
        rv.setAdapter(adapter);
    }

    public void settingStaggedGridRecyclerView(RecyclerView rv, BaseQuickAdapter adapter, int spanCount, boolean isHasFixSize, boolean isEnableNestedScrollView) {
        rv.setHasFixedSize(isHasFixSize);
        rv.setNestedScrollingEnabled(isEnableNestedScrollView);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(spanCount, LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(staggeredGridLayoutManager);
        rv.setAdapter(adapter);
    }

    public boolean isHaveNetWork() {
        return NetworkUtils.isConnected(this);
    }


}
