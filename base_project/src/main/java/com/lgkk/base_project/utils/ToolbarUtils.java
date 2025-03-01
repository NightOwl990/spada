package com.lgkk.base_project.utils;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lgkk.base_project.R;
import com.lgkk.base_project.widget.CircleImageView;
import com.lgkk.base_project.widget.fadetoolbar.ScrollUtils;
import com.lgkk.base_project.widget.roundedimageview.RoundedImageView;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class ToolbarUtils {

    private static void setVisibleView(View view, boolean visible) {
        view.setVisibility(visible ? VISIBLE : GONE);
    }

    public static float calculatorAlpha(int scrollY, int mParallaxStartFadeHeight, int mParallaxEndFadeHeight) {
        return Math.min(1, (float) (scrollY - mParallaxStartFadeHeight) / (mParallaxEndFadeHeight - mParallaxStartFadeHeight));
    }

    public static void settingFadeShopToolbar(Context context, int scrollY, int mParallaxStartFadeHeight, int mParallaxMiddleFadeHeight,
                                              int mParallaxEndFadeHeight, LinearLayout btnLeft,
                                              RelativeLayout btnMiddle, RelativeLayout rlBot, View viewTop,
                                              int drawableRightChange, ImageView imgIconRight, int drawableLeftChange,
                                              ImageView imgLeft, RoundedImageView imgBgMiddle, TextView txtMiddle,
                                              String middleText, int drawableMiddleChange, ImageView imgMiddle, int drawableRight,
                                              int drawableLeft, int textColor, int drawableMiddle) {

        int length45 = context.getResources().getDimensionPixelSize(R.dimen.length_45);
        int length80 = context.getResources().getDimensionPixelSize(R.dimen.length_80);
        int baseColor = context.getResources().getColor(R.color.white);
        int baseColor2 = context.getResources().getColor(R.color.col_f1f1f1);

        float alpha2 = calculatorAlpha(scrollY, mParallaxStartFadeHeight, mParallaxEndFadeHeight);
        float alphaMiddle = calculatorAlpha(scrollY, mParallaxMiddleFadeHeight, mParallaxEndFadeHeight);

        if (scrollY > mParallaxStartFadeHeight) {
//            Scale Left ----------
            float scale = (float) (scrollY - mParallaxStartFadeHeight) / mParallaxEndFadeHeight;
            if (scale > 1.0) scale = 1;
            if (scale < 0) scale = 0;
            btnLeft.setPivotX(0);
            btnLeft.setPivotY(length45 / 2);
            btnLeft.setScaleY(scale);
            btnLeft.setScaleX(scale);

//            margin Search toolbar -------
            float percent = ((float) (scrollY - mParallaxStartFadeHeight) / mParallaxEndFadeHeight) * length80;
            if (percent > length80) percent = length80;
            if (percent < 0) percent = 0;
            ViewGroup.LayoutParams params = btnMiddle.getLayoutParams();
            ((ViewGroup.MarginLayoutParams) params).setMargins((int) percent, 0, 0, 0);
            btnMiddle.setLayoutParams(params);


            rlBot.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha2, baseColor));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                viewTop.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha2, baseColor));
            } else {
                if (alpha2 >= 0.8f) {
                    viewTop.setBackgroundColor(ScrollUtils.getColorWithAlpha(0.8f, baseColor));
                }
            }
            viewTop.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha2, baseColor));

            if (scrollY >= mParallaxMiddleFadeHeight) {
                ImageUtils.loadImageDrawableByGlide(context, drawableRightChange, imgIconRight);
                ImageUtils.loadImageDrawableByGlide(context, drawableLeftChange, imgLeft);
                imgBgMiddle.setBackgroundColor(ScrollUtils.getColorWithAlpha(alphaMiddle, baseColor2));
                txtMiddle.setTextColor(context.getResources().getColor(R.color.text_sub));
                txtMiddle.setText(middleText);
                ImageUtils.loadImageDrawableByGlide(context, drawableMiddleChange, imgMiddle);
            } else {
                ImageUtils.loadImageDrawableByGlide(context, drawableRight, imgIconRight);
                ImageUtils.loadImageDrawableByGlide(context, drawableLeft, imgLeft);
                imgBgMiddle.setBackground(context.getResources().getDrawable(R.drawable.bg_round_transparent_shop));
                txtMiddle.setTextColor(textColor);
                txtMiddle.setText(middleText);
                ImageUtils.loadImageDrawableByGlide(context, drawableMiddle, imgMiddle);
            }
        } else {
            btnLeft.setScaleY(0f);
            btnLeft.setScaleX(0f);

            ViewGroup.LayoutParams params = btnMiddle.getLayoutParams();
            ((ViewGroup.MarginLayoutParams) params).setMargins((int) 0, 0, 0, 0);
            btnMiddle.setLayoutParams(params);

            rlBot.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, baseColor));
            viewTop.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, baseColor));
            imgBgMiddle.setBackground(context.getResources().getDrawable(R.drawable.bg_round_transparent_shop));
            ImageUtils.loadImageDrawableByGlide(context, drawableRight, imgIconRight);
            ImageUtils.loadImageDrawableByGlide(context, drawableLeft, imgLeft);
            txtMiddle.setTextColor(textColor);
            txtMiddle.setText(middleText);
            ImageUtils.loadImageDrawableByGlide(context, drawableMiddle, imgMiddle);

        }
    }


    public static void settingCommonToolbar(Context context, int scrollY,
                                            View viewTop,
                                            ImageView btnLeft,
                                            ImageView btnRight,
                                            TextView txtMiddle,
                                            RelativeLayout rlBot,
                                            View divider,
                                            CircleImageView imgMiddle,
                                            int mParallaxStartFadeHeight,
                                            int mParallaxEndFadeHeight,
                                            int drawableRight,
                                            int drawableRightChange,
                                            int drawableLeft,
                                            int drawableLeftChange,
                                            int drawableMiddle,
                                            int drawableMiddleChange,
                                            String middleText,
                                            int textColor,
                                            boolean textStartVisible,
                                            boolean dividerVisible,
                                            String middleTextChange) {


        int baseColor = context.getResources().getColor(R.color.white);
        float alpha2 = ToolbarUtils.calculatorAlpha(scrollY, mParallaxStartFadeHeight, mParallaxEndFadeHeight);

        if (scrollY > mParallaxStartFadeHeight) {
            rlBot.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha2, baseColor));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                viewTop.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha2, baseColor));
            } else {
                if (alpha2 >= 0.8f) {
                    viewTop.setBackgroundColor(ScrollUtils.getColorWithAlpha(0.8f, baseColor));
                }
            }
            viewTop.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha2, baseColor));

            ImageUtils.loadImageDrawableByGlide(context, drawableRightChange, btnRight);
            ImageUtils.loadImageDrawableByGlide(context, drawableLeftChange, btnLeft);
            txtMiddle.setTextColor(context.getResources().getColor(R.color.text_dark));
            txtMiddle.setText(middleTextChange);

            txtMiddle.setVisibility(VISIBLE);
            setVisibleView(imgMiddle, true);
            ImageUtils.loadImageDrawableByGlide(context, drawableMiddleChange, imgMiddle);
        } else {
            rlBot.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, baseColor));
            viewTop.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, baseColor));
            ImageUtils.loadImageDrawableByGlide(context, drawableRight, btnRight);
            ImageUtils.loadImageDrawableByGlide(context, drawableLeft, btnLeft);
            txtMiddle.setTextColor(textColor);

            txtMiddle.setText(middleText);
            setVisibleView(txtMiddle, textStartVisible);
            setVisibleView(imgMiddle, drawableMiddle != View.NO_ID);
            ImageUtils.loadImageDrawableByGlide(context, drawableMiddle, imgMiddle);

        }

        if (scrollY >= mParallaxEndFadeHeight) {
            setVisibleView(divider, dividerVisible);
        } else {

            divider.setVisibility(GONE);
        }
    }
}
