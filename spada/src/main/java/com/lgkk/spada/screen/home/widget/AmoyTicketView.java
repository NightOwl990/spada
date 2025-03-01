package com.lgkk.spada.screen.home.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.lgkk.base_project.utils.ImageUtils;
import com.lgkk.spada.R;

import com.lgkk.base_project.widget.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class AmoyTicketView extends FrameLayout {
    /* access modifiers changed from: private */
    private ArrayList<BrowseEntity> browseEntities;
    /* access modifiers changed from: private */
    private int distance;
    /* access modifiers changed from: private */
    private int eachWidth;
    /* access modifiers changed from: private */
    private Context mContext;
    /* access modifiers changed from: private */
    private int position;
    private boolean stopAnimator;
    private Disposable subscribe;

    public AmoyTicketView(@NonNull Context context) {
        this(context, null);
    }

    public AmoyTicketView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AmoyTicketView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView(context);
        setFocusable(false);
    }

    //
    /* access modifiers changed from: private */
    private RoundedImageView getImageView() {
        RoundedImageView roundedImageView = new RoundedImageView(getContext());
        roundedImageView.setCornerRadius((float) (eachWidth / 2));
        roundedImageView.setBorderWidth(R.dimen.length_1);
        roundedImageView.setBorderColor(getResources().getColor(R.color.white));
        roundedImageView.setScaleType(RoundedImageView.ScaleType.FIT_XY);
        return roundedImageView;
    }

    /* access modifiers changed from: private */
    private LayoutParams getLayoutParams(int i) {
        LayoutParams layoutParams = new LayoutParams(eachWidth, eachWidth);
        layoutParams.gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;
        layoutParams.setMargins(0, 0, distance * i, 0);
        return layoutParams;
    }

    private void initFirstView() {
        RoundedImageView imageView = getImageView();
        imageView.setScaleX(0.0f);
        imageView.setScaleY(0.0f);
        addView(imageView, getLayoutParams(5));
    }

    private void initView(Context context) {
        mContext = context;
        eachWidth = mContext.getResources().getDimensionPixelSize(R.dimen.length_20);
        distance = eachWidth - mContext.getResources().getDimensionPixelSize(R.dimen.length_5);
        initFirstView();
        for (int i = 5; i >= 0; i--) {
            addView(getImageView(), getLayoutParams(i));
        }
    }

    /* access modifiers changed from: private */
    private void startAnimation() {
        Disposable disposable = subscribe;
        if (disposable != null) {
            disposable.dispose();
            subscribe = null;
        }
        subscribe = Observable.timer(500, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(aLong -> startAnimations());
    }

    /* access modifiers changed from: private */
    private void startAnimations() {
        if (stopAnimator) {
            ArrayList<BrowseEntity> arrayList = browseEntities;
            if (arrayList == null || arrayList.isEmpty()) {
                stopAnimator = false;
                return;
            }
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
            ofFloat.setDuration(600);
            ofFloat.setInterpolator(new LinearInterpolator());
            ofFloat.addUpdateListener(valueAnimator -> {
                float floatValue = (Float) valueAnimator.getAnimatedValue();
                int childCount = getChildCount() - 1;
                float f = 1.0f - floatValue;
                float a2 = ((float) distance) * f;
                for (int i = 0; i < getChildCount(); i++) {
                    ImageView imageView = (ImageView) getChildAt(i);
                    if (i == childCount) {
                        imageView.setScaleX(floatValue);
                        imageView.setScaleY(floatValue);
                    } else if (i == 0) {
                        imageView.setScaleX(f);
                        imageView.setScaleY(f);
                    } else {
                        LayoutParams layoutParams = (LayoutParams) imageView.getLayoutParams();
                        layoutParams.setMargins(0, 0, (int) (((float) (distance * (childCount - i))) - a2), 0);
                        imageView.setLayoutParams(layoutParams);
                    }
                }
            });
            ofFloat.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    removeViewAt(getChildCount() - 1);
                    position = position + 1;
                    if (position >= browseEntities.size()) {
                        position = 0;
                    }
                    RoundedImageView f = getImageView();
                    ImageUtils.loadRoundedImageByGlide(mContext, browseEntities.get(position).getImg(), f);
//                    ImageWorker.loadImage(mContext, browseEntities.get(position).img.trim(), f, R.drawable.default_min_image_drawable);
                    f.setScaleX(0.0f);
                    f.setScaleY(0.0f);
                    addView(f, 0, getLayoutParams(5));
                    startAnimation();
                }

                public void onAnimationStart(Animator animator) {
                    ImageView imageView = (ImageView) getChildAt(getChildCount() - 1);
                    imageView.setPivotX((float) eachWidth);
                    imageView.setPivotY((float) (eachWidth / 2));
                }
            });
            ofFloat.start();
        }
    }

    public void setBrowseEntities(ArrayList<BrowseEntity> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            setVisibility(GONE);
            return;
        }
        setVisibility(VISIBLE);
        int childCount = getChildCount();
        if (childCount != 0 && arrayList.size() >= childCount) {
            browseEntities = arrayList;
            position = childCount - 1;
            int i = 0;
            while (i < childCount) {
                BrowseEntity browseEntity = arrayList.get(i);
                i++;
                ImageUtils.loadRoundedImageByGlide(mContext, browseEntity.getImg(), (RoundedImageView) getChildAt(childCount - i));
//                ImageWorker.loadImage(mContext, browseEntity.img.trim(), (RoundedImageView) getChildAt(childCount - i), R.drawable.default_min_image_drawable);
            }
            startAnimator();
        }
    }

    public void startAnimator() {
        if (!stopAnimator) {
            stopAnimator = true;
            startAnimation();
        }
    }

    public void stopAnimator() {
        stopAnimator = false;
        Disposable disposable = subscribe;
        if (disposable != null) {
            disposable.dispose();
            subscribe = null;
        }
    }
}
