package com.lgkk.base_project.widget.animationstyle;

import android.animation.ObjectAnimator;
import android.util.DisplayMetrics;
import android.view.View;

import com.lgkk.base_project.widget.FlipVerticalSwingEnterDialog.BaseAnimatorSet;

//Animation
public class SlideBottomExit extends BaseAnimatorSet {

    public SlideBottomExit() {
        duration = 300;
    }

    @Override
    public void setAnimation(View view) {
        DisplayMetrics dm = view.getContext().getResources().getDisplayMetrics();
        animatorSet.playTogether(//
                ObjectAnimator.ofFloat(view, "translationY", 0, 250 * dm.density), //
                ObjectAnimator.ofFloat(view, "alpha", 1, 0));
    }
}
