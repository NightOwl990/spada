package com.lgkk.base_project.widget.verticalbanner.core;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Handler;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.lgkk.base_project.R;
import com.lgkk.base_project.widget.verticalbanner.callback.BindViewCallBack;
import com.lgkk.base_project.widget.verticalbanner.callback.CreateViewCallBack;
import com.lgkk.base_project.widget.verticalbanner.callback.OnClickBannerListener;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseBanner<T extends BaseBanner> extends FrameLayout {
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    private List<Object> mData;
    protected Handler mHandler;
    protected int mCurrentItem;

    protected int interval = 5 * 1000;//间隔-毫秒
    protected boolean isAutoPlay = true;//自动播放
    protected boolean isVertical = false;//纵向滚动
    protected boolean isLoop = true;//是否循环滚动
    protected boolean isScroll = true;//是否可以手动滚动
    protected int mineLoopLimitItem = 1;//最小循环滚动条数


    protected IndicatorAble mIndicatorAble;
    protected OnClickBannerListener onClickBannerListener;
    protected CreateViewCallBack createViewCallBack;
    protected BindViewCallBack bindViewCallBack;
    protected boolean defBannerView = true;

    protected void init(Context context, AttributeSet attrs, int defStyleAttr) {
        dealAttrs(context, attrs);
    }

    public T setOnClickBannerListener(OnClickBannerListener onClickBannerListener) {
        this.onClickBannerListener = onClickBannerListener;
        return (T) this;
    }

    public T createView(CreateViewCallBack listener) {
        defBannerView = false;
        this.createViewCallBack = listener;
        return (T) this;
    }

    public T bindView(BindViewCallBack bindViewCallBack) {
        this.bindViewCallBack = bindViewCallBack;
        return (T) this;
    }

    public List<Object> getBannerData() {
        if (mData == null) {
            mData = new ArrayList<Object>();
        }
        return mData;
    }

    public abstract int getCurrentItem();

    public abstract void setCurrentItem(int index);

    /**
     * 设置指示器
     *
     * @param mIndicatorAble
     * @return
     */
    public T setIndicatorable(IndicatorAble mIndicatorAble) {
        this.mIndicatorAble = mIndicatorAble;
        return (T) this;
    }

    public abstract T setOrientation(int orientation);

    public abstract T setLoop(boolean loop);

    private void dealAttrs(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.YmexBanner);
        interval = typedArray.getInt(R.styleable.YmexBanner_banner_interval, interval);
        mineLoopLimitItem = typedArray.getInt(R.styleable.YmexBanner_banner_min_loop, 1);
        isAutoPlay = typedArray.getBoolean(R.styleable.YmexBanner_banner_auto_play, isAutoPlay);
        isLoop = typedArray.getBoolean(R.styleable.YmexBanner_banner_loop, isLoop);
        isVertical = (typedArray.getInt(R.styleable.YmexBanner_banner_orientation, 0) == VERTICAL);
        typedArray.recycle();
    }


    public abstract <D extends Object> void execute(List<D> datas);

    protected abstract int positionIndex(int postion);

    public abstract void startAutoPlay();

    public abstract void stopAutoPlay();

    public BaseBanner(@NonNull Context context) {
        super(context);
    }

    public BaseBanner(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseBanner(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BaseBanner(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view instanceof IndicatorAble) {
                setIndicatorable((IndicatorAble) view);
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (isAutoPlay) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN://0
                    stopAutoPlay();
                    break;
                case MotionEvent.ACTION_CANCEL://3
                case MotionEvent.ACTION_UP://1
                case MotionEvent.ACTION_OUTSIDE://4
                    startAutoPlay();
                    break;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAutoPlay();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopAutoPlay();
    }

    /**
     * 当视图不可见时不滚动
     *
     * @param visibility visibility
     */
    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        if (visibility == GONE || visibility == INVISIBLE) {
            stopAutoPlay();
        } else if (visibility == VISIBLE) {
            startAutoPlay();
        }
        super.onWindowVisibilityChanged(visibility);
    }
}
