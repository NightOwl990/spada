package com.lgkk.base_project.widget.basepopup;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.util.Pair;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.lgkk.base_project.widget.basepopup.blur.PopupBlurOption;
import com.lgkk.base_project.widget.basepopup.interceptor.PopupWindowEventInterceptor;
import com.lgkk.base_project.widget.basepopup.util.InputMethodUtils;
import com.lgkk.base_project.widget.basepopup.util.PopupUiUtils;
import com.lgkk.base_project.widget.basepopup.util.PopupUtils;
import com.lgkk.base_project.widget.basepopup.util.SimpleAnimationUtils;
import com.lgkk.base_project.widget.basepopup.widget.QuickPopup;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public abstract class BasePopupWindow implements BasePopup, PopupWindow.OnDismissListener, PopupTouchController, PopupWindowLocationListener {
    public static final int MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT;
    public static final int WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT;
    private static final String TAG = "BasePopupWindow";
    private static final int MAX_RETRY_SHOW_TIME = 3;
    private BasePopupHelper mHelper;
    private WeakReference<Context> mContext;
    private PopupWindowEventInterceptor mEventInterceptor;

    //元素定义
    private PopupWindowProxy mPopupWindow;
    //popup视图
    private View mContentView;
    private View mDisplayAnimateView;

    private volatile boolean isExitAnimatePlaying = false;

    //重试次数
    private int retryCounter;
    private EditText mAutoShowInputEdittext;

    private GlobalLayoutListenerWrapper mGlobalLayoutListenerWrapper;
    private LinkedViewLayoutChangeListenerWrapper mLinkedViewLayoutChangeListenerWrapper;
    private WeakReference<View> mLinkedViewRef;
    private DelayInitCached mDelayInitCached;
    private Animator.AnimatorListener mAnimatorListener = new AnimatorListenerAdapter() {

        @Override
        public void onAnimationStart(Animator animation) {
            isExitAnimatePlaying = true;
            mHelper.onDismiss(true);
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            mContentView.post(new Runnable() {
                @Override
                public void run() {
                    isExitAnimatePlaying = false;
                    mPopupWindow.callSuperDismiss();
                }
            });

        }

        @Override
        public void onAnimationCancel(Animator animation) {
            isExitAnimatePlaying = false;
        }

    };
    private Animation.AnimationListener mAnimationListener = new SimpleAnimationUtils.AnimationListenerAdapter() {
        @Override
        public void onAnimationStart(Animation animation) {
            isExitAnimatePlaying = true;
            mHelper.onDismiss(true);
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            mContentView.post(new Runnable() {
                @Override
                public void run() {
                    isExitAnimatePlaying = false;
                    mPopupWindow.callSuperDismiss();
                }
            });
        }
    };

    public BasePopupWindow(Context context) {
        this(context, false);
    }

    /**
     * 支持延迟加载的PopupWindow
     *
     * @param context
     * @param delayInit 如果是true，请务必在您初始化后调用{@link #delayInit()}
     */
    public BasePopupWindow(Context context, boolean delayInit) {
        this(context, BasePopupHelper.DEFAULT_WIDTH, BasePopupHelper.DEFAULT_HEIGHT, delayInit);
    }

    public BasePopupWindow(Context context, int width, int height) {
        this(context, width, height, false);
    }

    /**
     * 支持延迟加载的PopupWindow
     *
     * @param context
     * @param width
     * @param height
     * @param delayInit 如果是true，请务必在您初始化后调用{@link #delayInit()}
     */
    public BasePopupWindow(Context context, int width, int height, boolean delayInit) {
        mContext = new WeakReference<Context>(context);
        if (!(this instanceof QuickPopup) && !delayInit) {
            initView(width, height);
        } else {
            mDelayInitCached = new DelayInitCached();
            mDelayInitCached.width = width;
            mDelayInitCached.height = height;
        }
    }

    public static void setDebugLogEnable(boolean printLog) {

    }

    /**
     * 延迟初始化
     */
    public void delayInit() {
        if (mDelayInitCached == null) return;
        initView(mDelayInitCached.width, mDelayInitCached.height);
        mDelayInitCached = null;
    }

    private void initView(int width, int height) {
        mHelper = new BasePopupHelper(this);
        registerListener(mHelper);
        mContentView = onCreateContentView();
        if (mHelper.getParaseFromXmlParams() == null) {
            Log.e(TAG, "为了更准确的适配您的布局，BasePopupWindow建议您使用createPopupById()进行inflate");
        }
        mDisplayAnimateView = onCreateAnimateView();
        if (mDisplayAnimateView == null) {
            mDisplayAnimateView = mContentView;
        }
        setWidth(width);
        setHeight(height);

        if (mHelper.getParaseFromXmlParams() != null) {
            width = mHelper.getParaseFromXmlParams().width;
            height = mHelper.getParaseFromXmlParams().height;
        }

        //默认占满全屏
        mPopupWindow = new PopupWindowProxy(mContentView, width, height, mHelper);
        mPopupWindow.setOnDismissListener(this);
        mPopupWindow.bindPopupHelper(mHelper);
        setAllowDismissWhenTouchOutside(true);
        setPopupAnimationStyle(0);

        mHelper.setPopupViewWidth(width);
        mHelper.setPopupViewHeight(height);

        hookContentViewDismissClick(width, height);
        preMeasurePopupView(width, height);

        //show or dismiss animate
        mHelper.setShowAnimation(onCreateShowAnimation())
                .setShowAnimator(onCreateShowAnimator())
                .setDismissAnimation(onCreateDismissAnimation())
                .setDismissAnimator(onCreateDismissAnimator());
    }


    //------------------------------------------抽象-----------------------------------------------

    private void registerListener(BasePopupHelper helper) {
        helper.registerLocationLisener(this);
    }

    //针对match_parent的popup寻找点击消失区域
    private void hookContentViewDismissClick(int w, int h) {
        if (w != MATCH_PARENT || h != MATCH_PARENT) return;
        if (mContentView != null && !(mContentView instanceof AdapterView) && mContentView instanceof ViewGroup) {
            ViewGroup vp = ((ViewGroup) mContentView);
            final int childCount = vp.getChildCount();
            final List<Pair<WeakReference<View>, Rect>> protectViews = new ArrayList<>(childCount);
            for (int i = 0; i < childCount; i++) {
                View child = vp.getChildAt(i);
                if (child.getVisibility() != View.VISIBLE) continue;
                protectViews.add(Pair.create(new WeakReference<View>(child), new Rect()));
            }
            mContentView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            return isAllowDismissWhenTouchOutside();
                        case MotionEvent.ACTION_UP:
                            if (isAllowDismissWhenTouchOutside()) {
                                v.performClick();
                                int x = (int) event.getX();
                                int y = (int) event.getY();
                                boolean interceptDismiss = false;
                                for (Pair<WeakReference<View>, Rect> protectView : protectViews) {
                                    if (protectView.first == null || protectView.first.get() == null || protectView.second == null) {
                                        continue;
                                    }
                                    View ignoreTarget = protectView.first.get();
                                    Rect bounds = protectView.second;
                                    ignoreTarget.getGlobalVisibleRect(bounds);
                                    if (bounds.contains(x, y)) {
                                        interceptDismiss = true;
                                        break;
                                    }
                                }
                                if (!interceptDismiss) {
                                    dismiss();
                                }
                            }
                            break;
                    }
                    return false;
                }
            });

        }

    }

    private void preMeasurePopupView(int w, int h) {
        if (mContentView != null) {
            boolean breakPreMeasure = mEventInterceptor != null && mEventInterceptor.onPreMeasurePopupView(this, mContentView, w, h);
            if (!breakPreMeasure) {
                int measureWidth = View.MeasureSpec.makeMeasureSpec(w, w == ViewGroup.LayoutParams.WRAP_CONTENT ? View.MeasureSpec.UNSPECIFIED : View.MeasureSpec.EXACTLY);
                int measureHeight = View.MeasureSpec.makeMeasureSpec(h, h == ViewGroup.LayoutParams.WRAP_CONTENT ? View.MeasureSpec.UNSPECIFIED : View.MeasureSpec.EXACTLY);
                mContentView.measure(measureWidth, measureHeight);
            }
            mHelper.setPreMeasureWidth(mContentView.getMeasuredWidth())
                    .setPreMeasureHeight(mContentView.getMeasuredHeight());
            mContentView.setFocusableInTouchMode(true);
        }
    }

    /**
     * <p>
     * 该方法决定您的PopupWindow将会以怎样的动画展示出来，可以返回为 {@code null}
     * </p>
     * <p>
     * 本类提供一些简单的动画方法：
     * <ul>
     * <li>{@link #getDefaultAlphaAnimation()}：得到一个默认进入的渐变动画</li>
     * <li>{@link #getDefaultScaleAnimation()}：得到一个默认的放大缩小动画</li>
     * <li>{@link #getTranslateVerticalAnimation(float, float, int)} ()}：快速获取垂直方向的动画</li>
     * </ul>
     * <p>
     * 如果需要用到属性动画，请覆写{@link #onCreateShowAnimator()}
     *
     * @return 返回显示PopupWindow的动画
     */
    protected Animation onCreateShowAnimation() {
        return null;
    }

    /**
     * <p>
     * 该方法决定您的PopupWindow将会以怎样的动画消失，可以返回为 {@code null}
     * <br>
     * <br>
     * 如果返回不为空，则在返回动画播放结束后触发{@link PopupWindow#dismiss()}
     * </p>
     * <p>
     * 本类提供一些简单的动画方法：
     * <ul>
     * <li>{@link #getDefaultAlphaAnimation(boolean)} ()}：得到一个默认进入的渐变动画</li>
     * <li>{@link #getDefaultScaleAnimation(boolean)} ()}：得到一个默认的放大缩小动画</li>
     * <li>{@link #getTranslateVerticalAnimation(float, float, int)} ()}：快速获取垂直方向的动画</li>
     * </ul>
     * </p>
     * <p>
     * 如果需要用到属性动画，请覆写{@link #onCreateDismissAnimator()} ()}
     *
     * @return 返回PopupWindow消失前的动画
     */
    protected Animation onCreateDismissAnimation() {
        return null;
    }

    /**
     * <p>
     * 该方法决定您的PopupWindow将会以怎样的动画展示出来（返回 {@link Animator}），可以返回为 {@code null}
     * <br>
     * <br>
     * 功能详情请看{@link #onCreateShowAnimation()}
     * </p>
     *
     * @return 返回显示PopupWindow的动画
     */
    protected Animator onCreateShowAnimator() {
        return null;
    }

    /**
     * <p>
     * 通过该方法您可以指定您的PopupWindow显示动画用于哪个View（{@link #onCreateShowAnimation()}/{@link #onCreateShowAnimator()}）
     * <br>
     * <br>
     * 可以返回为空 {@code null}
     * </p>
     *
     * @return 返回指定播放动画的View，返回为空则默认整个PopupWindow
     */
    protected View onCreateAnimateView() {
        return null;
    }

    /**
     * <p>
     * 该方法决定您的PopupWindow将会以怎样的动画消失（返回 {@link Animator}），可以返回为 {@code null}
     * <br>
     * <br>
     * 功能详情请看{@link #onCreateDismissAnimation()} ()}
     * </p>
     */
    protected Animator onCreateDismissAnimator() {
        return null;
    }

    //------------------------------------------showPopup-----------------------------------------------

    /**
     * <p>
     * 当前PopupWindow是否设置了淡入淡出效果
     * </p>
     */
    public boolean isPopupFadeEnable() {
        return mHelper.isPopupFadeEnable();
    }

    /**
     * <p>
     * 当传入true，你的PopupWindow将会淡入显示，淡出消失。
     * <br>
     * 与{@link #onCreateShowAnimation()}/{@link #onCreateDismissAnimation()}不同的是，该方法为Window层级服务，固定Style
     * <br>
     * <ul>
     * <li>{@style ref razerdp.library.R.anim.basepopup_fade_in}</li>
     * <li>{@style ref razerdp.library.R.anim.basepopup_fade_out}</li>
     * </ul>
     * </p>
     *
     * @param isPopupFadeAnimate true for apply anim style
     */
    public BasePopupWindow setPopupFadeEnable(boolean isPopupFadeAnimate) {
        mHelper.setPopupFadeEnable(mPopupWindow, isPopupFadeAnimate);
        return this;
    }

    /**
     * <p>
     * 设置PopupWindow的动画style<strong>针对PopupWindow整体的Window哦</strong>
     * <br>
     * <br>
     * 通常情况下，请使用{@link #onCreateDismissAnimation()} or {@link #onCreateShowAnimator()}
     * </p>
     */
    public BasePopupWindow setPopupAnimationStyle(int animationStyleRes) {
        mPopupWindow.setAnimationStyle(animationStyleRes);
        return this;
    }

    public void showPopupWindow() {
        if (checkPerformShow(null)) {
            mHelper.setShowAsDropDown(false);
            tryToShowPopup(null, false, false);
        }
    }

    /**
     * <p>
     * 传入anchorView的ViewId，方法详情{@link #showPopupWindow(View)}
     * </p>
     *
     * @param anchorViewResid anchorView的ViewId
     */
    public void showPopupWindow(int anchorViewResid) {
        Context context = getContext();
        assert context != null : "context is null";
        if (context instanceof Activity) {
            View v = ((Activity) context).findViewById(anchorViewResid);
            showPopupWindow(v);
        } else {
            Log.e(TAG, "can not get token from context,make sure that context is instance of activity");
        }
    }

    /**
     * <p>
     * 调用这个方法时，将会展示PopupWindow。
     * <br>
     * <br>
     * <h3>本方法在展示PopupWindow时，会跟系统一样，展示在传入的View的底部，如果位置足够，将会跟anchorView的锚点对齐。</h3>
     * <br>
     * <br>
     * 其他方法详情参考{@link #showPopupWindow()}
     * <p>
     * </p>
     *
     * @param anchorView 锚点View，PopupWindow将会显示在其下方
     */
    public void showPopupWindow(View anchorView) {
        if (checkPerformShow(anchorView)) {
            if (anchorView != null) {
                mHelper.setShowAsDropDown(true);
            }
            tryToShowPopup(anchorView, false, false);
        }
    }

    /**
     * <p>
     * 调用这个方法时，将会在指定位置弹出PopupWindow。
     * <br>
     * 其他方法详情参考{@link #showPopupWindow()}
     * <p>
     *
     * @param x 坐标轴x
     * @param y 坐标轴y
     */
    public void showPopupWindow(int x, int y) {
        if (checkPerformShow(null)) {
            mHelper.setShowLocation(x, y);
            mHelper.setShowAsDropDown(true);
            tryToShowPopup(null, true, false);
        }
    }

    /**
     * <p>
     * 啥都不干，单纯的update，简单的说，就是更新你所设置的所有东西~
     * <br>
     * <b>WARN：非常不建议在连续update的情况下使用背景模糊，这会导致较大的性能消耗。<b/>
     */
    public void update() {
        tryToUpdate(null, false);
    }

    /**
     * <p>
     * 参考anchorView更新PopupWindow位置或大小等信息。
     * <br>
     * <b>该方法跟anchorView关联，即您的gravity，offset等会跟随anchorView变化而变化</b>
     *
     * <br>
     * <b>WARN：非常不建议在连续update的情况下使用背景模糊，这会导致较大的性能消耗。<b/>
     *
     * @param anchorView 被参考的anchorView
     */
    public void update(View anchorView) {
        if (!isShowing() || getContentView() == null) return;
        tryToUpdate(anchorView, false);
    }

    /**
     * <p>
     * 在指定位置更新PopupWindow位置或大小等信息。
     *
     * <br>
     * <b>WARN：非常不建议在连续update的情况下使用背景模糊，这会导致较大的性能消耗。<b/>
     *
     * @param x 目标位置x坐标
     * @param y 目标位置y坐标
     */
    public void update(int x, int y) {
        if (!isShowing() || getContentView() == null) return;
        mHelper.setShowLocation(x, y);
        mHelper.setShowAsDropDown(true);
        tryToUpdate(null, true);
    }

    /**
     * <p>
     * 更新PopupWindow的宽高
     * <br>
     * <b>WARN：非常不建议在连续update的情况下使用背景模糊，这会导致较大的性能消耗。<b/>
     * <br>
     * <b>WARN：非常不建议在连续update的情况下使用背景模糊，这会导致较大的性能消耗。<b/>
     * <br>
     *
     * @param width  宽度
     * @param height 高度
     */
    public void update(float width, float height) {
        if (!isShowing() || getContentView() == null) return;
        setWidth((int) width)
                .setHeight((int) height)
                .update();

    }

    /**
     * <p>
     * 在指定位置更新PopupWindow位置或大小等信息。
     * <br>
     *
     * @param x      目标位置x坐标
     * @param y      目标位置y坐标
     * @param width  宽度
     * @param height 高度
     */
    public void update(int x, int y, float width, float height) {
        if (!isShowing() || getContentView() == null) return;
        mHelper.setShowLocation(x, y);
        mHelper.setShowAsDropDown(true);
        setWidth((int) width)
                .setHeight((int) height)
                .tryToUpdate(null, true);
    }

    //------------------------------------------Methods-----------------------------------------------
    private void tryToShowPopup(View v, boolean positionMode, boolean abortAnimate) {
        addListener();
        mHelper.handleShow();
        if (mEventInterceptor != null && mEventInterceptor.onTryToShowPopup(this,
                mPopupWindow,
                v,
                mHelper.getPopupGravity(),
                mHelper.getOffsetX(),
                mHelper.getOffsetY())) {
            return;
        }
        try {
            if (isShowing()) return;
            Point offset = calculateOffset(v, positionMode);
            if (mEventInterceptor != null) {
                mEventInterceptor.onCalculateOffsetResult(this, v, offset, mHelper.getOffsetX(), mHelper.getOffsetY());
            }
            //传递了view
            if (v != null) {
                if (mHelper.isShowAsDropDown()) {
                    mPopupWindow.showAsDropDownProxy(v, offset.x, offset.y, getPopupGravity());
                } else {
                    mPopupWindow.showAtLocationProxy(v, getPopupGravity(), offset.x, offset.y);
                }
            } else {
                //什么都没传递，取顶级view的id
                Context context = getContext();
                assert context != null : "context is null ! please make sure your activity is not be destroyed";
                Activity activity = PopupUtils.scanForActivity(context, 50);
                if (activity == null) {
                    Log.e(TAG, "can not get token from context,make sure that context is instance of activity");
                } else {
                    mPopupWindow.showAtLocationProxy(activity.findViewById(android.R.id.content),
                            Gravity.NO_GRAVITY,
                            offset.x,
                            offset.y);
                }
            }
            mHelper.onShow(mHelper.getShowAnimation() != null || mHelper.getShowAnimator() != null);
            if (mDisplayAnimateView != null && !abortAnimate) {
                if (mHelper.getShowAnimation() != null) {
                    mHelper.getShowAnimation().cancel();
                    mDisplayAnimateView.startAnimation(mHelper.getShowAnimation());
                } else if (mHelper.getShowAnimator() != null) {
                    mHelper.getShowAnimator().start();
                }
            }
            //自动弹出键盘
            if (mHelper.isAutoShowInputMethod() && mAutoShowInputEdittext != null) {
                mAutoShowInputEdittext.requestFocus();
                InputMethodUtils.showInputMethod(mAutoShowInputEdittext, 350);
            }
            retryCounter = 0;
        } catch (Exception e) {
            retryToShowPopup(v, positionMode, abortAnimate);
            e.printStackTrace();
        }
    }

    private void tryToUpdate(View v, boolean positionMode) {
        if (!isShowing() || getContentView() == null) return;
        mHelper.cacheOffset(calculateOffset(v, positionMode));
        mPopupWindow.update();
    }

    private void addListener() {
        addGlobalListener();
        addLinkedLayoutListener();
    }

    private void addGlobalListener() {
        if (mGlobalLayoutListenerWrapper != null && mGlobalLayoutListenerWrapper.isAttached()) {
            return;
        }
        Activity activity = PopupUtils.scanForActivity(getContext(), 50);
        if (activity == null) return;
        View decorView = activity.getWindow() == null ? null : activity.getWindow().getDecorView();
        if (decorView == null) return;
        if (decorView instanceof ViewGroup) {
            decorView = (((ViewGroup) decorView).getChildAt(0));
        }
        mGlobalLayoutListenerWrapper = new GlobalLayoutListenerWrapper(decorView, new OnKeyboardStateChangeListener() {
            @Override
            public void onKeyboardChange(int keyboardHeight, boolean isVisible) {
                mHelper.onKeyboardChange(keyboardHeight, isVisible);
            }
        });
        mGlobalLayoutListenerWrapper.addSelf();
    }

    private void addLinkedLayoutListener() {
        if (mLinkedViewLayoutChangeListenerWrapper != null && mLinkedViewLayoutChangeListenerWrapper.isAdded)
            return;
        mLinkedViewLayoutChangeListenerWrapper = new LinkedViewLayoutChangeListenerWrapper();
        mLinkedViewLayoutChangeListenerWrapper.addSelf();
    }

    private void removeGlobalListener() {
        if (mGlobalLayoutListenerWrapper != null) {
            mGlobalLayoutListenerWrapper.remove();
        }
        mHelper.handleDismiss();
    }

    private void removeLinkedLayoutListener() {
        if (mLinkedViewLayoutChangeListenerWrapper != null) {
            mLinkedViewLayoutChangeListenerWrapper.removeListener();
        }
    }

    /**
     * 用于修复popup无法在onCreate里面show的问题
     */
    private void retryToShowPopup(final View v, final boolean positionMode, final boolean abortAnimate) {
        if (retryCounter > MAX_RETRY_SHOW_TIME) return;
        if (mPopupWindow.callSuperIsShowing()) {
            mPopupWindow.callSuperDismiss();
        }
        Activity act = mPopupWindow.scanForActivity(getContext());
        if (act == null) return;
        boolean available;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            available = !act.isFinishing() && !act.isDestroyed();
        } else {
            available = !act.isFinishing();
        }
        if (available) {
            View rootView = act.getWindow().getDecorView();
            if (rootView == null) return;
            rootView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    retryCounter++;
                    tryToShowPopup(v, positionMode, abortAnimate);
                }
            }, 350);
        }

    }

    /**
     * 计算popupwindow的偏移量
     *
     * @param anchorView
     * @see #showPopupWindow(View)
     */
    private Point calculateOffset(View anchorView, boolean positionMode) {
        Point offset;
        if (mEventInterceptor != null) {
            offset = mEventInterceptor.onCalculateOffset(this, anchorView, mHelper.getOffsetX(), mHelper.getOffsetY());
            if (offset != null) {
                mHelper.cacheOffset(offset);
                return offset;
            }
        }
        offset = mHelper.getTempOffset(mHelper.getOffsetX(), mHelper.getOffsetY());
        mHelper.getAnchorLocation(anchorView);

        if (positionMode) {
            //此时传入的是位置信息，默认以NO_GRAVITY测量
            /**@see BasePopupHelper#setShowLocation(int, int) */
            offset.offset(mHelper.getAnchorX(), mHelper.getAnchorY());
        }

        onCalculateOffsetAdjust(offset, positionMode, anchorView != null);
        mHelper.cacheOffset(offset);
        return offset;

    }

    /**
     * 针对不同的情况进行调整偏移量
     * <p>
     * 针对RecyclerView等非提前测量到的值无效
     *
     * @see PopupDecorViewProxy#layoutWithIntercept(int, int, int, int)
     */
    private void onCalculateOffsetAdjust(Point offset, boolean positionMode, boolean relativeToAnchor) {
        int leftMargin = 0;
        int topMargin = 0;
        int rightMargin = 0;
        int bottomMargin = 0;
        if (mHelper.getParaseFromXmlParams() != null) {
            leftMargin = mHelper.getParaseFromXmlParams().leftMargin;
            topMargin = mHelper.getParaseFromXmlParams().topMargin;
            rightMargin = mHelper.getParaseFromXmlParams().rightMargin;
            bottomMargin = mHelper.getParaseFromXmlParams().bottomMargin;
        }
        //由于showAsDropDown系统已经帮我们定位在view的下方，因此这里的offset我们仅需要做微量偏移
        switch (getPopupGravity() & Gravity.HORIZONTAL_GRAVITY_MASK) {
            case Gravity.LEFT:
            case Gravity.START:
                if (relativeToAnchor) {
                    offset.x += -getWidth() + leftMargin;
                } else {
                    offset.x += leftMargin;
                }
                break;
            case Gravity.RIGHT:
            case Gravity.END:
                if (relativeToAnchor) {
                    offset.x += mHelper.getAnchorViewWidth() + leftMargin;
                } else {
                    offset.x += getScreenWidth() - getWidth() - rightMargin;
                }
                break;
            case Gravity.CENTER_HORIZONTAL:
                if (relativeToAnchor) {
                    offset.x += (mHelper.getAnchorViewWidth() - getWidth()) >> 1;
                } else {
                    offset.x += ((getScreenWidth() - getWidth()) >> 1) + leftMargin - rightMargin;
                }
                break;
            default:
                if (!relativeToAnchor) {
                    offset.x += leftMargin;
                }
                break;
        }

        switch (getPopupGravity() & Gravity.VERTICAL_GRAVITY_MASK) {
            case Gravity.TOP:
                if (relativeToAnchor) {
                    offset.y += -(mHelper.getAnchorHeight() + getHeight()) + topMargin;
                } else {
                    offset.y += topMargin;
                }
                break;
            case Gravity.BOTTOM:
                //系统默认就在下面.
                if (!relativeToAnchor) {
                    offset.y += getScreenHeight() - getHeight() - bottomMargin;
                }
                break;
            case Gravity.CENTER_VERTICAL:
                if (relativeToAnchor) {
                    offset.y += -((getHeight() + mHelper.getAnchorHeight()) >> 1);
                } else {
                    offset.y += ((getScreenHeight() - getHeight()) >> 1) + topMargin - bottomMargin;
                }
                break;
            default:
                if (!relativeToAnchor) {
                    offset.y += topMargin;
                }
                break;
        }


        if (mHelper.isAutoLocatePopup() && !mHelper.isInterceptTouchEvent()) {
            final int offsetY = positionMode ? 0 : offset.y;
            final boolean onTop = (getScreenHeight() - (mHelper.getAnchorY() + offsetY) < getHeight());
            if (onTop) {
                if (positionMode) {
                    offset.y += ((getPopupGravity() & Gravity.VERTICAL_GRAVITY_MASK) == Gravity.CENTER_VERTICAL) ?
                            -getHeight() >> 1 : -getHeight();
                } else {
                    offset.y = -mHelper.getAnchorHeight() - getHeight() - offsetY;
                }
                onAnchorTop();
            } else {
                onAnchorBottom();
            }
        }
    }

    /**
     * <p>
     * PopupWindow是否需要自适应输入法，为输入法弹出让出区域
     * </p>
     *
     * @param needAdjust <ul>
     *                   <li>true for "SOFT_INPUT_ADJUST_RESIZE" mode</li>
     *                   <li>false for "SOFT_INPUT_ADJUST_NOTHING" mode</li>
     *                   </ul>
     *                   <br>
     */
    public BasePopupWindow setAdjustInputMethod(boolean needAdjust) {
        setAdjustInputMethod(needAdjust, WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return this;
    }

    /**
     * <p>
     * PopupWindow是否需要自适应输入法，为输入法弹出让出区域
     * </p>
     *
     * @param needAdjust <ul>
     *                   <li>true for "SOFT_INPUT_ADJUST_RESIZE" mode</li>
     *                   <li>false for "SOFT_INPUT_ADJUST_NOTHING" mode</li>
     *                   </ul>
     * @param flag       The desired mode, see
     *                   {@link android.view.WindowManager.LayoutParams#softInputMode}
     *                   for the full list
     */
    public BasePopupWindow setAdjustInputMethod(boolean needAdjust, int flag) {
        if (needAdjust) {
            mPopupWindow.setSoftInputMode(flag);
            setSoftInputMode(flag);
        } else {
            mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
            setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        }
        return this;
    }

    /**
     * <p>
     * PopupWindow在展示的时候自动打开输入法，在传入参数时请务必传入{@link EditText}
     * </p>
     */
    public BasePopupWindow setAutoShowInputMethod(EditText editText, boolean autoShow) {
        mHelper.setAutoShowInputMethod(mPopupWindow, autoShow);
        mAutoShowInputEdittext = editText;
        return this;
    }

    /**
     * 设置是否适配输入法
     *
     * @param softInputMode The desired mode, see
     *                      {@link android.view.WindowManager.LayoutParams#softInputMode}
     *                      for the full list
     */
    public BasePopupWindow setSoftInputMode(int softInputMode) {
        mHelper.setSoftInputMode(softInputMode);
        return this;
    }

    /**
     * <p>
     * 禁止PopupWindow返回键dismiss
     * </p>
     * <p>
     */
    public BasePopupWindow setBackPressEnable(boolean backPressEnable) {
        mHelper.setBackPressEnable(mPopupWindow, backPressEnable);
        return this;
    }

    /**
     * <p>
     * 这个方法封装了LayoutInflater.from(context).inflate，方便您设置PopupWindow所用的xml
     * </p>
     *
     * @param layoutId reference of layout
     * @return root View of the layout
     */
    public View createPopupById(int layoutId) {
        return mHelper.inflate(getContext(), layoutId);
    }

    /**
     * <p>
     * 还在用View.findViewById么，，，不如试试这款？
     * </p>
     *
     * @param id the ID to search for
     * @return a view with given ID if found, or {@code null} otherwise
     */
    public <T extends View> T findViewById(int id) {
        if (mContentView != null && id != 0) {
            return (T) mContentView.findViewById(id);
        }
        return null;
    }

    /**
     * <p>
     * 允许PopupWindow覆盖屏幕（包含状态栏）
     */
    public BasePopupWindow setPopupWindowFullScreen(boolean isFullScreen) {
        mHelper.setFullScreen(isFullScreen);
        return this;
    }

    /**
     * <p>
     * 设置PopupWindow背景颜色，默认颜色为<strong>#8f000000</strong>
     * </p>
     *
     * @param color 背景颜色
     */
    public BasePopupWindow setBackgroundColor(int color) {
        mHelper.setPopupBackground(new ColorDrawable(color));
        return this;
    }

    /**
     * <p>
     * 设置PopupWindow背景Drawable，默认颜色为<strong>#8f000000</strong>
     * </p>
     *
     * @param drawableIds 背景Drawable id
     */
    public BasePopupWindow setBackground(int drawableIds) {
        if (drawableIds == 0) {
            return setBackground(null);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return setBackground(getContext().getDrawable(drawableIds));
        } else {
            return setBackground(getContext().getResources().getDrawable(drawableIds));
        }
    }

    /**
     * <p>
     * 设置PopupWindow背景Drawable，默认颜色为<strong>#8f000000</strong>
     * </p>
     *
     * @param background 背景Drawable
     */
    public BasePopupWindow setBackground(Drawable background) {
        mHelper.setPopupBackground(background);
        return this;
    }

    /**
     * <p>
     * 获取当前PopupWindow背景
     * </p>
     *
     * @return 背景
     */
    public Drawable getPopupBackground() {
        return mHelper.getPopupBackground();
    }

    /**
     * <p>
     * 设置PopupWindow弹出时是否模糊背景。
     * <br>
     * <br>
     * 在使用模糊背景前，您可以通过{@link #setBlurOption(PopupBlurOption)}传入模糊配置。
     * <br>
     * <br>
     * <strong>本方法默认模糊当前Activity的DecorView</strong>
     * </p>
     *
     * @param blurBackgroundEnable true for blur decorView
     */
    public BasePopupWindow setBlurBackgroundEnable(boolean blurBackgroundEnable) {
        return setBlurBackgroundEnable(blurBackgroundEnable, null);
    }

    //------------------------------------------Getter/Setter-----------------------------------------------

    /**
     * <p>
     * 设置PopupWindow弹出时是否模糊背景。
     * <br>
     * <br>
     * 在使用模糊背景前，您可以通过{@link #setBlurOption(PopupBlurOption)}传入模糊配置。
     * <br>
     * <br>
     * 本方法允许您传入一个初始化监听，您可以在{@link OnBlurOptionInitListener#onCreateBlurOption(PopupBlurOption)}中进行展示前的最后一次修改
     * </p>
     *
     * @param blurBackgroundEnable true for blur decorView
     * @param optionInitListener   初始化回调
     */
    public BasePopupWindow setBlurBackgroundEnable(boolean blurBackgroundEnable, OnBlurOptionInitListener optionInitListener) {
        if (!(getContext() instanceof Activity)) {
            return this;
        }
        PopupBlurOption option = null;
        if (blurBackgroundEnable) {
            option = new PopupBlurOption();
            option.setFullScreen(true)
                    .setBlurInDuration(mHelper.getShowAnimationDuration())
                    .setBlurOutDuration(mHelper.getExitAnimationDuration());
            if (optionInitListener != null) {
                optionInitListener.onCreateBlurOption(option);
            }
            View decorView = ((Activity) getContext()).getWindow().getDecorView();
            if (decorView instanceof ViewGroup) {
                option.setBlurView(((ViewGroup) decorView).getChildAt(0));
            } else {
                option.setBlurView(decorView);
            }
        }

        return setBlurOption(option);
    }

    /**
     * <p>
     * 应用模糊配置，更多详情请参考{@link #setBlurBackgroundEnable(boolean)}或者{@link #setBlurBackgroundEnable(boolean, OnBlurOptionInitListener)}
     * </p>
     *
     * @param option 模糊配置
     */
    public BasePopupWindow setBlurOption(PopupBlurOption option) {
        mHelper.applyBlur(option);
        return this;
    }

    /**
     * 这个方法用于简化您为View设置OnClickListener事件，多个View将会使用同一个点击事件
     */
    protected void setViewClickListener(View.OnClickListener listener, View... views) {
        for (View view : views) {
            if (view != null && listener != null) {
                view.setOnClickListener(listener);
            }
        }
    }

    /**
     * PopupWindow是否处于展示状态
     */
    public boolean isShowing() {
        return mPopupWindow.isShowing();
    }

    public OnDismissListener getOnDismissListener() {
        return mHelper.getOnDismissListener();
    }

    /**
     * <p>
     * 设置dismiss监听
     * </p>
     *
     * @param onDismissListener 监听器
     */
    public BasePopupWindow setOnDismissListener(OnDismissListener onDismissListener) {
        mHelper.setOnDismissListener(onDismissListener);
        return this;
    }

    public OnBeforeShowCallback getOnBeforeShowCallback() {
        return mHelper.getOnBeforeShowCallback();
    }

    /**
     * <p>
     * 当您设置了{@link OnBeforeShowCallback}监听之后，在您调用｛
     * <ul>
     * <li>{@link #showPopupWindow()}</li>
     * <li>{@link #showPopupWindow(int)}</li>
     * <li>{@link #showPopupWindow(View)}</li>
     * </ul>｝
     * <br>
     * 任意一个方法，在show之前回回调到该监听器。
     * <br>
     * </p>
     *
     * @param mOnBeforeShowCallback
     * @return
     * @see OnBeforeShowCallback#onBeforeShow(View, View, boolean)
     */
    public BasePopupWindow setOnBeforeShowCallback(OnBeforeShowCallback mOnBeforeShowCallback) {
        mHelper.setOnBeforeShowCallback(mOnBeforeShowCallback);
        return this;
    }

    public Animation getShowAnimation() {
        return mHelper.getShowAnimation();
    }

    /**
     * <p>
     * 设置展示PopupWindow的动画，详情参考{@link #onCreateShowAnimation()}
     * </p>
     *
     * @param showAnimation 展示动画
     */
    public BasePopupWindow setShowAnimation(Animation showAnimation) {
        mHelper.setShowAnimation(showAnimation);
        return this;
    }

    public Animator getShowAnimator() {
        return mHelper.getShowAnimator();
    }

    /**
     * <p>
     * 设置展示PopupWindow的动画，详情参考{@link #onCreateShowAnimator()}
     * </p>
     *
     * @param showAnimator 展示动画
     */
    public BasePopupWindow setShowAnimator(Animator showAnimator) {
        mHelper.setShowAnimator(showAnimator);
        return this;
    }

    public Animation getDismissAnimation() {
        return mHelper.getDismissAnimation();
    }

    /**
     * <p>
     * 设置退出PopupWindow的动画，详情参考{@link #onCreateDismissAnimation()}
     * </p>
     *
     * @param dismissAnimation 退出动画
     */
    public BasePopupWindow setDismissAnimation(Animation dismissAnimation) {
        mHelper.setDismissAnimation(dismissAnimation);
        return this;
    }

    public Animator getDismissAnimator() {
        return mHelper.getDismissAnimator();
    }

    /**
     * <p>
     * 设置退出PopupWindow的动画，详情参考{@link #onCreateDismissAnimator()}
     * </p>
     *
     * @param dismissAnimator 退出动画
     */
    public BasePopupWindow setDismissAnimator(Animator dismissAnimator) {
        mHelper.setDismissAnimator(dismissAnimator);
        return this;
    }

    /**
     * <p>
     * 获取context，请留意是否为空{@code null}
     * </p>
     *
     * @return 返回对应的context。如果为空，则返回{@code null}
     */
    public Context getContext() {
        return mContext == null ? null : mContext.get();
    }

    /**
     * <p>
     * 获取PopupWindow的根布局
     * </p>
     *
     * @see #onCreateContentView()，该布局在这里初始化。
     */
    public View getContentView() {
        return mContentView;
    }

    /**
     * <p>
     * 获取PopupWindow执行动画的View
     * </p>
     * <br>
     * 如果{@link #onCreateAnimateView()}返回为空，则返回contentView（{@link #onCreateContentView()}）
     */
    public View getDisplayAnimateView() {
        return mDisplayAnimateView;
    }

    /**
     * 获取PopupWindow实例
     *
     * @return
     */
    public PopupWindow getPopupWindow() {
        return mPopupWindow;
    }

    public int getOffsetX() {
        return mHelper.getOffsetX();
    }

    /**
     * 设定x位置的偏移量(中心点在popup的左上角)
     * <p>
     *
     * @param offsetX
     */
    public BasePopupWindow setOffsetX(int offsetX) {
        mHelper.setOffsetX(offsetX);
        return this;
    }

    public int getOffsetY() {
        return mHelper.getOffsetY();
    }

    /**
     * 设定y位置的偏移量(中心点在popup的左上角)
     *
     * @param offsetY
     */
    public BasePopupWindow setOffsetY(int offsetY) {
        mHelper.setOffsetY(offsetY);
        return this;
    }

    public int getPopupGravity() {
        return mHelper.getPopupGravity();
    }

    /**
     * <p>
     * 设置参考点 {@link Gravity}
     * <br>
     * <ul>
     * <li> 不跟anchorView联系的情况下，gravity意味着在整个view中的方位{@link #showPopupWindow()}</li>
     * <li> 如果跟anchorView联系，gravity意味着以anchorView为中心的方位{@link #showPopupWindow(View)}</li>
     * </ul>
     *
     * @param popupGravity
     */
    public BasePopupWindow setPopupGravity(int popupGravity) {
        mHelper.setPopupGravity(popupGravity);
        return this;
    }

    public boolean isAutoLocatePopup() {
        return mHelper.isAutoLocatePopup();
    }

    /**
     * <p>
     * 是否自动设置PopupWindow位置
     * <br>
     * <br>
     * 如果当前屏幕不足以完整显示您的PopupWindow，则PopupWindow会自行布置在其镜像位置。
     * <br>
     * <br>
     * <pre>
     * 比如当前PopupWindow显示在某个View的下方，而屏幕下方不够位置展示完整改PopupWindow，
     * 当本设置为true，PopupWindow将会显示在原来的View的上方以满足完整显示PopupWindow的情况。
     * </pre>
     * <br>
     * <br>
     * <strong>如果您配置了{@link #setOffsetY(int)}，则对应的偏移量也是在其适配后的位置生效</strong>
     * </p>
     *
     * @param isAutoLocatePopup 是否自适配
     */
    public BasePopupWindow setAutoLocatePopup(boolean isAutoLocatePopup) {
        mHelper.setAutoLocatePopup(isAutoLocatePopup);
        return this;
    }

    /**
     * <p>
     * 获取PoupWindow的高度。
     * <br>
     * <br>
     * 当PopupWindow没show出来的时候高度会是0，此时则返回pre measure的高度，不一定精准
     * </p>
     *
     * @see #preMeasurePopupView(int, int)
     */
    public int getHeight() {
        if (mContentView == null) return mHelper.getPreMeasureHeight();
        return mContentView.getHeight() <= 0 ? mHelper.getPreMeasureHeight() : mContentView.getHeight();
    }

    public BasePopupWindow setHeight(int height) {
        mHelper.setPopupViewHeight(height);
        return this;
    }

    /**
     * <p>
     * 获取PoupWindow的宽度。
     * <br>
     * <br>
     * 当popupwindow没show出来的时候高度会是0，此时则返回pre measure的宽度，不一定精准
     * </p>
     *
     * @see #preMeasurePopupView(int, int)
     */
    public int getWidth() {
        if (mContentView == null) return mHelper.getPreMeasureWidth();
        return mContentView.getWidth() <= 0 ? mHelper.getPreMeasureWidth() : mContentView.getWidth();
    }

    public BasePopupWindow setWidth(int width) {
        mHelper.setPopupViewWidth(width);
        return this;
    }

    /**
     * 当{@link #setAllowInterceptTouchEvent(boolean)}为true时，该参数决定popupWindow是否被限制在绘制边界
     * <p>
     * <br>
     * <ul>
     * <li>true：PopupWindow将会被限制边界，其动画不可突破其边界</li>
     * <li>false：PopupWindow将不会被限制绘制边界，其动画可突破其边界</li>
     * </ul>
     *
     * @param clipChildren 默认为true
     */
    public BasePopupWindow setClipChildren(boolean clipChildren) {
        mHelper.setClipChildren(clipChildren);
        return this;
    }

    /**
     * 该方法用于指定PopupWindow是否可以突破屏幕
     * <p>
     * <br>
     * <ul>
     * <li>true：PopupWindow并不能突破屏幕，如果其高宽超出屏幕高宽，则会自动进行位移</li>
     * <li>false：PopupWindow可以突破屏幕</li>
     * </ul>
     * <p>
     * 如果contentView的宽高大于屏幕宽高，因自动调整，可能会导致{@link #setAutoLocatePopup(boolean)}失效
     *
     * @param clipToScreen 默认为true
     */
    public BasePopupWindow setClipToScreen(boolean clipToScreen) {
        mHelper.setClipToScreen(clipToScreen);
        return this;
    }

    public boolean isAllowDismissWhenTouchOutside() {
        return mHelper.isDismissWhenTouchOutside();
    }

    /**
     * <p>
     * 是否允许点击PopupWindow外部时触发dismiss
     * </p>
     * <br>
     * dismiss popup when touch outside from popup
     *
     * @param dismissWhenTouchOutside true for allow
     */
    public BasePopupWindow setAllowDismissWhenTouchOutside(boolean dismissWhenTouchOutside) {
        mHelper.setDismissWhenTouchOutside(mPopupWindow, dismissWhenTouchOutside);
        return this;
    }

    public boolean isAllowInterceptTouchEvent() {
        return mHelper.isInterceptTouchEvent();
    }

    /**
     * <p>
     * 是否允许点击PopupWindow拦截事件。
     * <br>
     * <br>
     * 如果允许拦截事件，则PopupWindow外部无法响应事件。
     * </p>
     *
     * @param touchable <ul>
     *                  <li>ture:PopupWindow拦截事件</li>
     *                  <li>false：不拦截事件</li>
     *                  </ul>
     */
    public BasePopupWindow setAllowInterceptTouchEvent(boolean touchable) {
        mHelper.setInterceptTouchEvent(mPopupWindow, touchable);
        return this;
    }

    public boolean isAlignMaskToPopup() {
        return mHelper.isAlignBackground();
    }

    //------------------------------------------状态控制-----------------------------------------------

    /**
     * <p>
     * 设置PopupWindow的背景是否对齐到PopupWindow。
     * <br>
     * <br>
     * 默认情况下，PopupWindow背景都是铺满整个屏幕的。
     * 但在某些情况下您可能在PopupWindow之上不需要展示背景，这时候您可以调用这个方法来强制Background对齐到PopupWindow的顶部。
     * </p>
     *
     * @param isAlignBackground 是否对齐背景
     */
    public BasePopupWindow setAlignBackground(boolean isAlignBackground) {
        mHelper.setAlignBackgound(isAlignBackground);
        return this;
    }

    /**
     * <p>
     * 设置PopupWindow的背景对齐PopupWindow的方式，请传入{@link Gravity}中的值
     * <br>
     *
     * @param gravity 请传入{@link Gravity}中的值，传入{@link Gravity#NO_GRAVITY}则意味着不对齐
     */
    public BasePopupWindow setAlignBackgroundGravity(int gravity) {
        mHelper.setAlignBackgroundGravity(gravity);
        return this;
    }

    /**
     * <p>
     * 允许PopupWindow跟某个anchorView关联，其位置，可视性将会跟anchorView同步</>
     * <br>
     * <b>WARN：非常不建议在anchorView频繁变化的情况下使用背景模糊，这会导致较大的性能消耗。</b>
     */
    public BasePopupWindow linkTo(View anchorView) {
        if (anchorView == null) {
            if (mLinkedViewLayoutChangeListenerWrapper != null) {
                mLinkedViewLayoutChangeListenerWrapper.removeListener();
                mLinkedViewLayoutChangeListenerWrapper = null;
            }
            if (mLinkedViewRef != null) {
                mLinkedViewRef.clear();
                mLinkedViewRef = null;
                return this;
            }
        }

        mLinkedViewRef = new WeakReference<>(anchorView);
        return this;
    }

    /**
     * 添加BasePopupWindow事件拦截器
     *
     * @param eventInterceptor
     */
    public <P extends BasePopupWindow> BasePopupWindow setEventInterceptor(PopupWindowEventInterceptor<P> eventInterceptor) {
        mEventInterceptor = eventInterceptor;
        mHelper.setEventInterceptor(eventInterceptor);
        return this;
    }

    /**
     * 取消一个PopupWindow，如果有退出动画，PopupWindow的消失将会在动画结束后执行
     */
    public void dismiss() {
        dismiss(true);
    }

    /**
     * 取消一个PopupWindow，如果有退出动画，PopupWindow的消失将会在动画结束后执行
     *
     * @param animateDismiss 传入为true，则执行退出动画后dismiss（如果有的话）
     */
    public void dismiss(boolean animateDismiss) {
        if (animateDismiss) {
            try {
                if (mAutoShowInputEdittext != null && mHelper.isAutoShowInputMethod()) {
                    InputMethodUtils.close(mAutoShowInputEdittext);
                }
                mPopupWindow.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            dismissWithOutAnimate();
        }
        removeListener();
    }

    private void removeListener() {
        removeGlobalListener();
        removeLinkedLayoutListener();
    }

    @Override
    public boolean onBeforeDismiss() {
        return checkPerformDismiss();
    }

    @Override
    public boolean callDismissAtOnce() {
        boolean hasAnimate = false;
        if (mHelper.getDismissAnimation() != null && mDisplayAnimateView != null) {
            if (!isExitAnimatePlaying) {
                mHelper.getDismissAnimation().setAnimationListener(mAnimationListener);
                mHelper.getDismissAnimation().cancel();
                mDisplayAnimateView.startAnimation(mHelper.getDismissAnimation());
                hasAnimate = true;
            }
        } else if (mHelper.getDismissAnimator() != null) {
            if (!isExitAnimatePlaying) {
                mHelper.getDismissAnimator().removeListener(mAnimatorListener);
                mHelper.getDismissAnimator().addListener(mAnimatorListener);
                mHelper.getDismissAnimator().start();
                hasAnimate = true;
            }
        }
        if (!hasAnimate) {
            mHelper.onDismiss(false);
        }
        //如果有动画，则不立刻执行dismiss
        return !hasAnimate;
    }

    /**
     * 直接消掉PopupWindow而不需要动画
     */
    public void dismissWithOutAnimate() {
        if (!checkPerformDismiss()) return;
        if (mHelper.getDismissAnimation() != null && mDisplayAnimateView != null) {
            mHelper.getDismissAnimation().cancel();
        }
        if (mHelper.getDismissAnimator() != null) {
            mHelper.getDismissAnimator().removeAllListeners();
        }
        if (mAutoShowInputEdittext != null && mHelper.isAutoShowInputMethod()) {
            InputMethodUtils.close(mAutoShowInputEdittext);
        }
        mPopupWindow.callSuperDismiss();
        mHelper.onDismiss(false);
        removeListener();
    }

    private boolean checkPerformDismiss() {
        boolean callDismiss = true;
        if (mHelper.getOnDismissListener() != null) {
            callDismiss = mHelper.getOnDismissListener().onBeforeDismiss();
        }
        return callDismiss && !isExitAnimatePlaying;
    }

    private boolean checkPerformShow(View v) {
        boolean result = true;
        if (mHelper.getOnBeforeShowCallback() != null) {
            result = mHelper.getOnBeforeShowCallback().onBeforeShow(mContentView, v,
                    mHelper.getShowAnimation() != null || mHelper.getShowAnimator() != null);
        }
        return result;
    }

    /**
     * 捕捉keyevent
     *
     * @param event
     * @return true意味着你已经处理消耗了事件，后续不再传递
     */
    @Override
    public boolean onDispatchKeyEvent(KeyEvent event) {
        return false;
    }

    /**
     * 捕捉interceptTouchEvent
     *
     * @param event
     * @return true意味着你已经处理消耗了事件，后续不再传递
     */
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return false;
    }

    //------------------------------------------Animate-----------------------------------------------

    /**
     * 捕捉touchevent
     *
     * @param event
     * @return true意味着你已经处理消耗了事件，后续不再传递
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    /**
     * 捕捉返回键事件
     *
     * @return true意味着你已经处理消耗了事件，后续不再传递
     */
    @Override
    public boolean onBackPressed() {
        if (mHelper.isBackPressEnable()) {
            dismiss();
            return true;
        }
        return false;
    }

    /**
     * PopupWindow外的事件点击回调，请注意您的PopupWindow大小
     *
     * @return true意味着你已经处理消耗了事件，后续不再传递
     */
    @Override
    public boolean onOutSideTouch() {
        boolean result = false;
        if (mHelper.isDismissWhenTouchOutside()) {
            dismiss();
            result = true;
        } else if (mHelper.isInterceptTouchEvent()) {
            result = true;
        }
        return result;
    }

    /**
     * 生成TranslateAnimation
     *
     * @param durationMillis 动画显示时间
     * @param start          初始百分比
     * @param end            结束百分比
     */
    protected Animation getTranslateVerticalAnimation(int start, int end, int durationMillis) {
        return SimpleAnimationUtils.getTranslateVerticalAnimation(start, end, durationMillis);
    }

    /**
     * 生成TranslateAnimation（相对于parent）
     *
     * @param durationMillis 动画显示时间
     * @param start          初始百分比(0f~1f)
     * @param end            结束百分比(0f~1f)
     */
    protected Animation getTranslateVerticalAnimation(float start, float end, int durationMillis) {
        return SimpleAnimationUtils.getTranslateVerticalAnimation(start, end, durationMillis);
    }

    /**
     * 生成ScaleAnimation
     * <p>
     * time=300
     */
    protected Animation getScaleAnimation(float fromX,
                                          float toX,
                                          float fromY,
                                          float toY,
                                          int pivotXType,
                                          float pivotXValue,
                                          int pivotYType,
                                          float pivotYValue) {
        return SimpleAnimationUtils.getScaleAnimation(fromX, toX, fromY, toY, pivotXType, pivotXValue, pivotYType, pivotYValue);
    }

    /**
     * 生成自定义ScaleAnimation
     */
    protected Animation getDefaultScaleAnimation() {
        return getDefaultScaleAnimation(true);
    }

    /**
     * 生成自定义ScaleAnimation
     *
     * @param in true for scale in
     */
    protected Animation getDefaultScaleAnimation(boolean in) {
        return SimpleAnimationUtils.getDefaultScaleAnimation(in);
    }

    /**
     * 生成默认的AlphaAnimation
     */
    protected Animation getDefaultAlphaAnimation() {
        return getDefaultAlphaAnimation(true);
    }

    /**
     * 生成默认的AlphaAnimation
     *
     * @param in true for alpha in
     */
    protected Animation getDefaultAlphaAnimation(boolean in) {
        return SimpleAnimationUtils.getDefaultAlphaAnimation(in);
    }

    /**
     * 从下方滑动上来
     */
    protected AnimatorSet getDefaultSlideFromBottomAnimationSet() {
        return SimpleAnimationUtils.getDefaultSlideFromBottomAnimationSet(mDisplayAnimateView);
    }

    /**
     * 获取屏幕高度(px)
     */
    public int getScreenHeight() {
        return PopupUiUtils.getScreenHeightCompat(getContext());
    }


    //------------------------------------------callback-----------------------------------------------

    /**
     * 获取屏幕宽度(px)
     */
    public int getScreenWidth() {
        return PopupUiUtils.getScreenWidthCompat(getContext());
    }

    /**
     * 在anchorView上方显示，autoLocatePopup为true时适用
     */
    @Override
    public void onAnchorTop() {

    }

    /**
     * 在anchorView下方显示，autoLocatePopup为true时适用
     */
    @Override
    public void onAnchorBottom() {

    }

    /**
     * 在anchorView上方显示，autoLocatePopup为true时适用
     *
     * @param mPopupView {@link #onCreateContentView()}返回的View
     * @param anchorView {@link #showPopupWindow(View)}传入的View
     * @see #onAnchorTop()
     * @deprecated 因为contentView和anchorView应由用户自行保存决定，此处不再返回
     */
    @Deprecated
    public void onAnchorTop(View mPopupView, View anchorView) {

    }

    /**
     * 在anchorView下方显示，autoLocatePopup为true时适用
     *
     * @param mPopupView {@link #onCreateContentView()}返回的View
     * @param anchorView {@link #showPopupWindow(View)}传入的View
     * @see #onAnchorBottom()
     * @deprecated 因为contentView和anchorView应由用户自行保存决定，此处不再返回
     */
    @Deprecated
    public void onAnchorBottom(View mPopupView, View anchorView) {

    }


    //------------------------------------------tools-----------------------------------------------

    @Override
    public void onDismiss() {
        if (mHelper.getOnDismissListener() != null) {
            mHelper.getOnDismissListener().onDismiss();
        }
        isExitAnimatePlaying = false;
    }

    protected float dipToPx(float dip) {
        if (getContext() == null) return dip;
        return dip * getContext().getResources().getDisplayMetrics().density + 0.5f;
    }

    //------------------------------------------Interface-----------------------------------------------
    public interface OnBeforeShowCallback {
        /**
         * <p>
         * 在PopupWindow展示出来之前，如果您设置好了该监听器{@link #setOnBeforeShowCallback(OnBeforeShowCallback)}
         * 那么show之前将会回调到本方法，在这里您可以进一步决定是否可以展示PopupWindow
         * </p>
         *
         * @param contentView    PopupWindow的ContentView
         * @param anchorView     锚点View
         * @param hasShowAnimate 是否有showAnimation
         * @return <ul>
         * <li>【true】：允许展示PopupWindow</li>
         * <li>【false】：不允许展示PopupWindow</li>
         * </ul>
         */
        boolean onBeforeShow(View contentView, View anchorView, boolean hasShowAnimate);


    }

    public interface OnBlurOptionInitListener {
        void onCreateBlurOption(PopupBlurOption option);
    }

    interface OnKeyboardStateChangeListener {
        void onKeyboardChange(int keyboardHeight, boolean isVisible);
    }

    public static abstract class OnDismissListener implements PopupWindow.OnDismissListener {
        /**
         * <p>
         * 在PopupWindow消失之前，如果您设置好了该监听器{@link #setOnDismissListener(OnDismissListener)}
         * 那么dismiss之前将会回调到本方法，在这里您可以进一步决定是否可以继续取消PopupWindow
         * </p>
         *
         * @return <ul>
         * <li>【true】：继续取消PopupWindow</li>
         * <li>【false】：不允许取消PopupWindow</li>
         * </ul>
         */
        public boolean onBeforeDismiss() {
            return true;
        }
    }

    //------------------------------------------InnerClass-----------------------------------------------
    private static class GlobalLayoutListenerWrapper implements ViewTreeObserver.OnGlobalLayoutListener {

        int preKeyboardHeight = -1;
        Rect rect = new Rect();
        boolean preVisible = false;
        private WeakReference<View> target;
        private OnKeyboardStateChangeListener mListener;
        private volatile boolean isAttached;

        public GlobalLayoutListenerWrapper(View target, OnKeyboardStateChangeListener listener) {
            this.target = new WeakReference<>(target);
            mListener = listener;
            isAttached = false;
        }

        public boolean isAttached() {
            return isAttached;
        }

        public void addSelf() {
            if (getTarget() != null && !isAttached) {
                getTarget().getViewTreeObserver().addOnGlobalLayoutListener(this);
                isAttached = true;
            }
        }

        public void remove() {
            if (getTarget() != null && isAttached) {
                getTarget().getViewTreeObserver().removeOnGlobalLayoutListener(this);
                isAttached = false;
            }
        }

        View getTarget() {
            return target == null ? null : target.get();
        }

        @Override
        public void onGlobalLayout() {
            View mTarget = getTarget();
            if (mTarget == null) return;
            rect.setEmpty();
            mTarget.getWindowVisibleDisplayFrame(rect);
            int displayHeight = rect.height();
            int windowHeight = mTarget.getHeight();
            int keyboardHeight = mTarget.getBottom() - rect.bottom;
            if (preKeyboardHeight != keyboardHeight) {
                //判定可见区域与原来的window区域占比是否小于0.75,小于意味着键盘弹出来了。
                boolean isVisible = (displayHeight * 1.0f / windowHeight * 1.0f) < 0.75f;
                if (isVisible != preVisible) {
                    if (mListener != null) {
                        mListener.onKeyboardChange(keyboardHeight, isVisible);
                    }
                    preVisible = isVisible;
                }
            }
            preKeyboardHeight = keyboardHeight;
        }
    }

    private class LinkedViewLayoutChangeListenerWrapper implements ViewTreeObserver.OnPreDrawListener {

        Rect lastLocationRect = new Rect();
        Rect newLocationRect = new Rect();
        private boolean isAdded;
        private float lastX, lastY;
        private int lastWidth, lastHeight, lastVisible;
        private boolean lastShowState, hasChange;

        void addSelf() {
            if (mLinkedViewRef == null || mLinkedViewRef.get() == null || isAdded) return;
            View target = mLinkedViewRef.get();
            target.getGlobalVisibleRect(lastLocationRect);
            refreshViewParams();
            target.getViewTreeObserver().addOnPreDrawListener(this);
            isAdded = true;
        }

        void removeListener() {
            if (mLinkedViewRef == null || mLinkedViewRef.get() == null || !isAdded) return;
            mLinkedViewRef.get().getViewTreeObserver().removeOnPreDrawListener(this);
            isAdded = false;
        }

        void refreshViewParams() {
            if (mLinkedViewRef == null || mLinkedViewRef.get() == null) return;
            View target = mLinkedViewRef.get();

            //之所以不直接用getGlobalVisibleRect，是因为getGlobalVisibleRect需要不断的找到parent然后获取位置，因此先比较自身属性，然后进行二次验证
            float curX = target.getX();
            float curY = target.getY();
            int curWidth = target.getWidth();
            int curHeight = target.getHeight();
            int curVisible = target.getVisibility();
            boolean isShow = target.isShown();

            hasChange = (curX != lastX ||
                    curY != lastY ||
                    curWidth != lastWidth ||
                    curHeight != lastHeight ||
                    curVisible != lastVisible) && isAdded;
            if (!hasChange) {
                //不排除是recyclerview中那样子的情况，因此这里进行二次验证，获取view在屏幕中的位置
                target.getGlobalVisibleRect(newLocationRect);
                if (!newLocationRect.equals(lastLocationRect)) {
                    lastLocationRect.set(newLocationRect);
                    //处理可能的在recyclerview回收的事情
                    if (!handleShowChange(target, lastShowState, isShow)) {
                        hasChange = true;
                    }
                }
            }

            lastX = curX;
            lastY = curY;
            lastWidth = curWidth;
            lastHeight = curHeight;
            lastVisible = curVisible;
            lastShowState = isShow;
        }

        private boolean handleShowChange(View target, boolean lastShowState, boolean isShow) {
            if (lastShowState && !isShow) {
                if (isShowing()) {
                    dismiss(false);
                    return true;
                }
            } else if (!lastShowState && isShow) {
                if (!isShowing()) {
                    tryToShowPopup(target, false, true);
                    return true;
                }
            }
            return false;
        }


        @Override
        public boolean onPreDraw() {
            if (mLinkedViewRef == null || mLinkedViewRef.get() == null) return true;
            refreshViewParams();
            if (hasChange) {
                update(mLinkedViewRef.get());
            }
            return true;
        }
    }

    private class DelayInitCached {
        int width;
        int height;
    }

}

