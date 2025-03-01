package com.lgkk.base_project.widget.animationstyle;

import android.animation.ObjectAnimator;
import android.view.View;

import com.lgkk.base_project.widget.FlipVerticalSwingEnterDialog.BaseAnimatorSet;

public class FlipVerticalSwingEnter extends BaseAnimatorSet {
    public FlipVerticalSwingEnter() {
        duration = 500;
    }

    @Override
    public void setAnimation(View view) {
        animatorSet.playTogether(//
                ObjectAnimator.ofFloat(view, "rotationX", 90, -10, 10, 0),//
                ObjectAnimator.ofFloat(view, "alpha", 0.25f, 0.5f, 0.75f, 1));
    }
}
