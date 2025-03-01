package com.lgkk.spada.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.lgkk.base_project.utils.ImageUtils;
import com.lgkk.spada.R;

import java.util.Timer;
import java.util.TimerTask;

public class LoginScrollBackgroundView extends ScrollView {
    private TimerTask timeTask;
    private Timer timer;

    public LoginScrollBackgroundView(Context context) {
        this(context, null);
    }

    public LoginScrollBackgroundView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LoginScrollBackgroundView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.timeTask = new TimerTask() {
            public void run() {
                if (((computeVerticalScrollRange() / 2) + getMeasuredHeight()) - (getScrollY() + getHeight()) <= 3) {
                    scrollTo(0, 0);
                }
                smoothScrollBy(0, 1);
            }
        };
        init(context);
    }

    private void init(Context context) {
        setdata(context);
        this.timer = new Timer();
        this.timer.schedule(this.timeTask, 10, 30);
    }

    private void setdata(Context context) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        addView(linearLayout);
        for (int i = 0; i < 2; i++) {
            ImageView imageView = new ImageView(context);
            imageView.setAdjustViewBounds(true);
            imageView.setImageResource(R.drawable.long_newwelcome_bg);
            linearLayout.addView(imageView);
        }
    }

    public void cancelTimer() {
        Timer timer2 = timer;
        if (timer2 != null) {
            timer2.cancel();
            timer = null;
        }
        TimerTask timerTask = timeTask;
        if (timerTask != null) {
            timerTask.cancel();
            timeTask = null;
        }
    }
}