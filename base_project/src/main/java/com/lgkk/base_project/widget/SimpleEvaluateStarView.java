package com.lgkk.base_project.widget;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lgkk.base_project.R;
import com.lgkk.base_project.utils.ImageUtils;

import java.math.BigDecimal;

public class SimpleEvaluateStarView extends LinearLayout {
    private ImageView img_star0;
    private ImageView img_star1;
    private ImageView img_star2;
    private ImageView img_star3;
    private ImageView img_star4;
    private Context mContext;

    public SimpleEvaluateStarView(Context context) {
        this(context, null);
        this.mContext = context;
    }

    public SimpleEvaluateStarView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SimpleEvaluateStarView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
        initView(context);
    }

    @RequiresApi(api = 21)
    public SimpleEvaluateStarView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        initView(context);
    }

    private void initView(Context context) {
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        img_star0 = new ImageView(context);
        img_star0.setLayoutParams(layoutParams);
        img_star1 = new ImageView(context);
        img_star1.setLayoutParams(layoutParams);
        img_star2 = new ImageView(context);
        img_star2.setLayoutParams(layoutParams);
        img_star3 = new ImageView(context);
        img_star3.setLayoutParams(layoutParams);
        img_star4 = new ImageView(context);
        img_star4.setLayoutParams(layoutParams);
        addView(img_star0);
        addView(img_star1);
        addView(img_star2);
        addView(img_star3);
        addView(img_star4);
    }

    private void setLastStarNum(ImageView imageView, float f) {
        int i = R.drawable.evaluate_star_0;
        if (0.0f == f) {
            i = R.drawable.evaluate_star_0;
        } else if (0.1f == f) {
            i = R.drawable.evaluate_star_1;
        } else if (0.2f == f) {
            i = R.drawable.evaluate_star_2;
        } else if (0.3f == f) {
            i = R.drawable.evaluate_star_3;
        } else if (0.4f == f) {
            i = R.drawable.evaluate_star_4;
        } else if (0.5f == f) {
            i = R.drawable.evaluate_star_5;
        } else if (0.6f == f) {
            i = R.drawable.evaluate_star_6;
        } else if (0.7f == f) {
            i = R.drawable.evaluate_star_7;
        } else if (0.8f == f) {
            i = R.drawable.evaluate_star_8;
        } else if (0.9f == f) {
            i = R.drawable.evaluate_star_9;
        }
        ImageUtils.loadImageDrawableByGlide(mContext, i, imageView);
//        imageView.setImageResource(i);
    }

    private void setStarNum(float f) {
        ImageView imageView;
        int i = (((int) f) * 10) / 10;
        float floatValue = new BigDecimal(Float.toString(f)).subtract(new BigDecimal(Float.toString((float) i))).floatValue();
        if (i < 1) {
            ImageUtils.loadImageDrawableByGlide(mContext, R.drawable.evaluate_star_0, img_star0);
            ImageUtils.loadImageDrawableByGlide(mContext, R.drawable.evaluate_star_0, img_star1);
            ImageUtils.loadImageDrawableByGlide(mContext, R.drawable.evaluate_star_0, img_star2);
            ImageUtils.loadImageDrawableByGlide(mContext, R.drawable.evaluate_star_0, img_star3);
            ImageUtils.loadImageDrawableByGlide(mContext, R.drawable.evaluate_star_0, img_star4);
//            img_star0.setImageResource(R.drawable.evaluate_star_0);
//            img_star1.setImageResource(R.drawable.evaluate_star_0);
//            img_star2.setImageResource(R.drawable.evaluate_star_0);
//            img_star3.setImageResource(R.drawable.evaluate_star_0);
//            img_star4.setImageResource(R.drawable.evaluate_star_0);
            imageView = img_star0;
        } else if (i > 4) {
            ImageUtils.loadImageDrawableByGlide(mContext, R.drawable.evaluate_star_10, img_star0);
            ImageUtils.loadImageDrawableByGlide(mContext, R.drawable.evaluate_star_10, img_star1);
            ImageUtils.loadImageDrawableByGlide(mContext, R.drawable.evaluate_star_10, img_star2);
            ImageUtils.loadImageDrawableByGlide(mContext, R.drawable.evaluate_star_10, img_star3);
            ImageUtils.loadImageDrawableByGlide(mContext, R.drawable.evaluate_star_10, img_star4);
//            img_star0.setImageResource(R.drawable.evaluate_star_10);
//            img_star1.setImageResource(R.drawable.evaluate_star_10);
//            img_star2.setImageResource(R.drawable.evaluate_star_10);
//            img_star3.setImageResource(R.drawable.evaluate_star_10);
//            img_star4.setImageResource(R.drawable.evaluate_star_10);
            return;
        } else if (1 == i) {
            ImageUtils.loadImageDrawableByGlide(mContext, R.drawable.evaluate_star_10, img_star0);
            ImageUtils.loadImageDrawableByGlide(mContext, R.drawable.evaluate_star_0, img_star1);
            ImageUtils.loadImageDrawableByGlide(mContext, R.drawable.evaluate_star_0, img_star2);
            ImageUtils.loadImageDrawableByGlide(mContext, R.drawable.evaluate_star_0, img_star3);
            ImageUtils.loadImageDrawableByGlide(mContext, R.drawable.evaluate_star_0, img_star4);
//            img_star0.setImageResource(R.drawable.evaluate_star_10);
//            img_star1.setImageResource(R.drawable.evaluate_star_0);
//            img_star2.setImageResource(R.drawable.evaluate_star_0);
//            img_star3.setImageResource(R.drawable.evaluate_star_0);
//            img_star4.setImageResource(R.drawable.evaluate_star_0);
            imageView = img_star1;
        } else if (2 == i) {
            ImageUtils.loadImageDrawableByGlide(mContext, R.drawable.evaluate_star_10, img_star0);
            ImageUtils.loadImageDrawableByGlide(mContext, R.drawable.evaluate_star_10, img_star1);
            ImageUtils.loadImageDrawableByGlide(mContext, R.drawable.evaluate_star_0, img_star2);
            ImageUtils.loadImageDrawableByGlide(mContext, R.drawable.evaluate_star_0, img_star3);
            ImageUtils.loadImageDrawableByGlide(mContext, R.drawable.evaluate_star_0, img_star4);
//            img_star0.setImageResource(R.drawable.evaluate_star_10);
//            img_star1.setImageResource(R.drawable.evaluate_star_10);
//            img_star2.setImageResource(R.drawable.evaluate_star_0);
//            img_star3.setImageResource(R.drawable.evaluate_star_0);
//            img_star4.setImageResource(R.drawable.evaluate_star_0);
            imageView = img_star2;
        } else if (3 == i) {
            ImageUtils.loadImageDrawableByGlide(mContext, R.drawable.evaluate_star_10, img_star0);
            ImageUtils.loadImageDrawableByGlide(mContext, R.drawable.evaluate_star_10, img_star1);
            ImageUtils.loadImageDrawableByGlide(mContext, R.drawable.evaluate_star_10, img_star2);
            ImageUtils.loadImageDrawableByGlide(mContext, R.drawable.evaluate_star_0, img_star3);
            ImageUtils.loadImageDrawableByGlide(mContext, R.drawable.evaluate_star_0, img_star4);
//            img_star0.setImageResource(R.drawable.evaluate_star_10);
//            img_star1.setImageResource(R.drawable.evaluate_star_10);
//            img_star2.setImageResource(R.drawable.evaluate_star_10);
//            img_star3.setImageResource(R.drawable.evaluate_star_0);
//            img_star4.setImageResource(R.drawable.evaluate_star_0);
            imageView = img_star3;
        } else if (4 == i) {
            ImageUtils.loadImageDrawableByGlide(mContext, R.drawable.evaluate_star_10, img_star0);
            ImageUtils.loadImageDrawableByGlide(mContext, R.drawable.evaluate_star_10, img_star1);
            ImageUtils.loadImageDrawableByGlide(mContext, R.drawable.evaluate_star_10, img_star2);
            ImageUtils.loadImageDrawableByGlide(mContext, R.drawable.evaluate_star_10, img_star3);
            ImageUtils.loadImageDrawableByGlide(mContext, R.drawable.evaluate_star_0, img_star4);
//            img_star0.setImageResource(R.drawable.evaluate_star_10);
//            img_star1.setImageResource(R.drawable.evaluate_star_10);
//            img_star2.setImageResource(R.drawable.evaluate_star_10);
//            img_star3.setImageResource(R.drawable.evaluate_star_10);
//            img_star4.setImageResource(R.drawable.evaluate_star_0);
            imageView = img_star4;
        } else {
            return;
        }
        setLastStarNum(imageView, floatValue);
    }

    private void setStartWidgets(int i, ImageView imageView) {
        LayoutParams layoutParams = (LayoutParams) imageView.getLayoutParams();
        layoutParams.width = i;
        layoutParams.height = i;
        imageView.setLayoutParams(layoutParams);
    }

    public void setScore(float f) {
        setStarNum(f);
    }

    public void setStartWidget(int i) {
        setStartWidgets(i, img_star0);
        setStartWidgets(i, img_star1);
        setStartWidgets(i, img_star2);
        setStartWidgets(i, img_star3);
        setStartWidgets(i, img_star4);
    }
}
