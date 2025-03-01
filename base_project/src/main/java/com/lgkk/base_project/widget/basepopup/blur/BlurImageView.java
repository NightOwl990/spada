package com.lgkk.base_project.widget.basepopup.blur;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.lgkk.base_project.widget.basepopup.blur.thread.ThreadPoolManager;

import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;



/**
 * Created by 大灯泡 on 2017/12/27.
 * <p>
 * 模糊imageview
 */
public class BlurImageView extends androidx.appcompat.widget.AppCompatImageView {
    private static final String TAG = "BlurImageView";

    private static final long BLUR_TASK_WAIT_TIMEOUT = 1000;//图片模糊超时1秒
    private volatile boolean abortBlur = false;
    private WeakReference<PopupBlurOption> mBlurOption;
    private AtomicBoolean blurFinish = new AtomicBoolean(false);
    private volatile boolean isAnimating = false;
    private AtomicBoolean waitForBlurTask = new AtomicBoolean(false);
    private long startDuration;
    private long startTime;


    public BlurImageView(Context context) {
        this(context, null);
    }

    public BlurImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BlurImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setFocusable(false);
        setFocusableInTouchMode(false);
        setScaleType(ScaleType.MATRIX);
        setAlpha(0f);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(null);
        } else {
            setBackgroundDrawable(null);
        }
    }

    public void applyBlurOption(PopupBlurOption option) {
        applyBlurOption(option, false);
    }

    private void applyBlurOption(PopupBlurOption option, boolean isOnUpdate) {
        if (option == null) return;
        mBlurOption = new WeakReference<PopupBlurOption>(option);
        View anchorView = option.getBlurView();
        if (anchorView == null) {
            return;
        }
        //因为考虑到实时更新位置（包括模糊也要实时）的原因，因此强制更新时模糊操作在主线程完成。
        if (option.isBlurAsync() && !isOnUpdate) {
            startBlurTask(anchorView);
        } else {
            try {

                if (!BlurHelper.renderScriptSupported()) {
                  }
                setImageBitmapOnUiThread(BlurHelper.blur(getContext(), anchorView, option.getBlurPreScaleRatio(), option.getBlurRadius(), option.isFullScreen()), isOnUpdate);
            } catch (Exception e) {
                   e.printStackTrace();
            }
        }
    }

    PopupBlurOption getOption() {
        if (mBlurOption == null) return null;
        return mBlurOption.get();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        abortBlur = true;
    }

    public void update() {
        if (getOption() != null) {
            applyBlurOption(getOption(), true);
        }
    }

    /**
     * alpha进场动画
     *
     * @param duration
     */
    public void start(long duration) {
        startDuration = duration;
        if (!blurFinish.get() || waitForBlurTask.get()) {
            startTime = System.currentTimeMillis();
            waitForBlurTask.compareAndSet(false, true);
            return;
        }
        if (isAnimating) return;
         isAnimating = true;
        if (duration > 0) {
            animate()
                    .alpha(1f)
                    .setDuration(duration)
                    .setInterpolator(new DecelerateInterpolator())
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            isAnimating = false;
                            animate().setListener(null);
                        }
                    })
                    .start();
        } else if (duration == -2) {
            animate()
                    .alpha(1f)
                    .setDuration(getOption() == null ? 500 : getOption().getBlurInDuration())
                    .setInterpolator(new DecelerateInterpolator())
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            isAnimating = false;
                            animate().setListener(null);
                        }
                    })
                    .start();
        } else {
            setAlpha(1f);
        }
    }

    /**
     * alpha退场动画
     */
    public void dismiss(long duration) {
        isAnimating = false;
       if (duration > 0) {
            animate()
                    .alpha(0f)
                    .setDuration(duration)
                    .setInterpolator(new DecelerateInterpolator())
                    .start();
        } else if (duration == -2) {
            animate()
                    .alpha(0f)
                    .setDuration(getOption() == null ? 500 : getOption().getBlurOutDuration())
                    .setInterpolator(new DecelerateInterpolator())
                    .start();
        } else {
            setAlpha(0f);
        }
    }

    /**
     * 子线程模糊
     *
     * @param anchorView
     */
    private void startBlurTask(View anchorView) {
        ThreadPoolManager.execute(new CreateBlurBitmapRunnable(BlurHelper.getViewBitmap(anchorView, getOption().isFullScreen())));
    }

    /**
     * 判断是否处于主线程，并进行设置bitmap
     *
     * @param blurBitmap
     */
    private void setImageBitmapOnUiThread(final Bitmap blurBitmap, final boolean isOnUpdate) {
        if (isUiThread()) {
            handleSetImageBitmap(blurBitmap, isOnUpdate);
        } else {
            post(new Runnable() {
                @Override
                public void run() {
                    handleSetImageBitmap(blurBitmap, isOnUpdate);
                }
            });
        }
    }

    /**
     * 设置bitmap，并进行后续处理（此方法必定运行在主线程）
     *
     * @param bitmap
     */
    private void handleSetImageBitmap(Bitmap bitmap, boolean isOnUpdate) {
        if (bitmap != null) {
         }
        setAlpha(isOnUpdate ? 1f : 0f);
        setImageBitmap(bitmap);
        if (getOption() != null) {
            PopupBlurOption option = getOption();
            if (!option.isFullScreen()) {
                //非全屏的话，则需要将bitmap变化到对应位置
                View anchorView = option.getBlurView();
                if (anchorView == null) return;
                Rect rect = new Rect();
                anchorView.getGlobalVisibleRect(rect);
                Matrix matrix = getImageMatrix();
                matrix.setTranslate(rect.left, rect.top);
                setImageMatrix(matrix);
            }
        }
        blurFinish.compareAndSet(false, true);
        if (waitForBlurTask.get()) {
            if (System.currentTimeMillis() - startTime >= BLUR_TASK_WAIT_TIMEOUT) {
               waitForBlurTask.set(false);
            } else {
                waitForBlurTask.compareAndSet(true, false);
                start(startDuration);
            }
        }
    }

    private boolean isUiThread() {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }

    public void destroy() {
        setImageBitmap(null);
        abortBlur = true;
        if (mBlurOption != null) {
            mBlurOption.clear();
            mBlurOption = null;
        }
        blurFinish.set(false);
        isAnimating = false;
        startDuration = 0;
    }

    class CreateBlurBitmapRunnable implements Runnable {

        private Bitmap bitmap;

        CreateBlurBitmapRunnable(Bitmap bitmap) {
            this.bitmap = bitmap;
        }

        @Override
        public void run() {
            if (abortBlur || getOption() == null) {
                return;
            }
            setImageBitmapOnUiThread(BlurHelper.blur(getContext(), bitmap, getOption().getBlurPreScaleRatio(), getOption().getBlurRadius()), false);
        }
    }
}
