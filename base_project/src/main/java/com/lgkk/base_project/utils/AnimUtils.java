package com.lgkk.base_project.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;

import com.lgkk.base_project.R;

public class AnimUtils {

    public static void fadeOut(Context context, View viewAnim) {
        Animation fadeOutAnimation = android.view.animation.AnimationUtils.loadAnimation(context, R.anim.fade_out);
        viewAnim.startAnimation(fadeOutAnimation);
        fadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                viewAnim.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }


    public static void fadeOutInvisible(Context context, View viewAnim) {
        Animation fadeOutAnimation = android.view.animation.AnimationUtils.loadAnimation(context, R.anim.fade_out);
        viewAnim.startAnimation(fadeOutAnimation);
        fadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                viewAnim.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    public static void fadeIn(Context context, View viewAnim) {
        Animation fadeInAnimation = android.view.animation.AnimationUtils.loadAnimation(context, R.anim.fade_in);
        viewAnim.startAnimation(fadeInAnimation);
        fadeInAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                viewAnim.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    public static void fadeIn(Context context, View viewAnim, int duration) {
        Animation fadeInAnimation = android.view.animation.AnimationUtils.loadAnimation(context, R.anim.fade_in);
        fadeInAnimation.setDuration(duration);
        viewAnim.startAnimation(fadeInAnimation);
        fadeInAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                viewAnim.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    public static void zoomView(View iv_img, boolean zoomAnim, int duration) {
        if (zoomAnim) {
            AnimatorSet set = new AnimatorSet();
            set.playTogether(
                    ObjectAnimator.ofFloat(iv_img, "scaleX", 1f, 1.12f),
                    ObjectAnimator.ofFloat(iv_img, "scaleY", 1f, 1.12f)
            );
            set.setDuration(duration);
            set.start();
        }
    }

    public static void disZoomView(View iv_img, boolean zoomAnim, int duration) {
        if (zoomAnim) {
            AnimatorSet set = new AnimatorSet();
            set.playTogether(
                    ObjectAnimator.ofFloat(iv_img, "scaleX", 1.12f, 1f),
                    ObjectAnimator.ofFloat(iv_img, "scaleY", 1.12f, 1f)
            );
            set.setDuration(duration);
            set.start();
        }
    }
}
