package com.lgkk.base_project.widget;

import android.content.Context;
import android.graphics.Typeface;
import androidx.annotation.Nullable;
import android.util.AttributeSet;

import com.lgkk.base_project.utils.FontCache;

public class SpadaTextView extends androidx.appcompat.widget.AppCompatTextView {

    private static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";

    public SpadaTextView(Context context) {
        super(context);
        applyCustomFont(context, null);
    }

    public SpadaTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context, attrs);
    }

    public SpadaTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context, attrs);
    }

    private void applyCustomFont(Context context, AttributeSet attrs) {
        int textStyle = attrs.getAttributeIntValue(ANDROID_SCHEMA, "textStyle", Typeface.NORMAL);
        Typeface customFont = FontCache.selectTypeface(context, textStyle);
        setTypeface(customFont);
    }
}

