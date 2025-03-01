package com.lgkk.base_project.widget.animationstyle;

import android.animation.ObjectAnimator;
import android.view.View;

import com.lgkk.base_project.widget.FlipVerticalSwingEnterDialog.BaseAnimatorSet;

public class FadeExit extends BaseAnimatorSet {

    public FadeExit() {
        duration = 200;
    }

    @Override
    public void setAnimation(View view) {
        animatorSet.playTogether(//
                ObjectAnimator.ofFloat(view, "alpha", 1, 0).setDuration(duration));
    }
}
