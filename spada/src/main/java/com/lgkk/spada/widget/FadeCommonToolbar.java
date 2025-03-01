package com.lgkk.spada.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;

import com.lgkk.base_project.base.listener.OnFadeCommonToolbarListener;
import com.lgkk.base_project.utils.ImageUtils;
import com.lgkk.base_project.utils.ScreenUtil;
import com.lgkk.base_project.utils.ToolbarUtils;
import com.lgkk.base_project.widget.CircleImageView;
import com.lgkk.base_project.widget.fadetoolbar.ScrollUtils;
import com.lgkk.spada.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FadeCommonToolbar extends RelativeLayout implements View.OnClickListener {
    public static final int TYPE_LEFT_CLICK = 0;
    public static final int TYPE_RIGHT_CLICK = 1;
    public static final int TYPE_MIDDLE_CLICK = 2;
    public static final int TYPE_TOOLBAR_CLICK = 3;
    @BindView(R.id.viewTop)
    View viewTop;
    @BindView(R.id.btnLeft)
    ImageView btnLeft;
    @BindView(R.id.btnRight)
    ImageView btnRight;
    @BindView(R.id.btnSubRight)
    ImageView btnSubRight;
    @BindView(R.id.txtMiddle)
    TextView txtMiddle;
    @BindView(R.id.rlBot)
    RelativeLayout rlBot;
    @BindView(R.id.divider)
    View divider;
    @BindView(R.id.toolBar)
    RelativeLayout toolBar;
    @BindView(R.id.imgMiddle)
    CircleImageView imgMiddle;
    @BindView(R.id.btnMiddle)
    LinearLayout btnMiddle;

    private int mParallaxStartFadeHeight;
    private int mParallaxEndFadeHeight;
    private float alphaMain;
    private int drawableRight;
    private int drawableRightChange;
    private int drawableSubRight;
    private int drawableSubRightChange;
    private int drawableLeft;
    private int drawableLeftChange;
    private int drawableMiddle = NO_ID;
    private int drawableMiddleChange = NO_ID;
    private String middleText = "";
    private int textStyle = Typeface.NORMAL;
    private int textColor;
    private Context context;
    private Unbinder unbinder;
    private boolean textStartVisible, dividerVisible;
    private String middleTextChange = "";
    private OnFadeCommonToolbarListener listener;


    public FadeCommonToolbar(Context context) {
        this(context, null);
    }

    public FadeCommonToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_common_normal_toolbar, this, true);
        unbinder = ButterKnife.bind(this, view);
        this.context = context;
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CommonToolbarRelativeLayout, 0, 0);

        this.mParallaxStartFadeHeight = a.getInt(R.styleable.CommonToolbarRelativeLayout_cl_ParallaxStartFadeHeight, 0);
        this.mParallaxEndFadeHeight = a.getInt(R.styleable.CommonToolbarRelativeLayout_cl_ParallaxEndFadeHeight, 0);
        this.alphaMain = a.getFloat(R.styleable.CommonToolbarRelativeLayout_cl_alpha, 0f);
        this.middleText = a.getString(R.styleable.CommonToolbarRelativeLayout_cl_text);
        this.middleTextChange = a.getString(R.styleable.CommonToolbarRelativeLayout_cl_textChange);
        this.textColor = a.getColor(R.styleable.CommonToolbarRelativeLayout_cl_textColor, 0);
        this.textStyle = a.getColor(R.styleable.CommonToolbarRelativeLayout_cl_textStyle, Typeface.NORMAL);
        this.drawableRight = a.getResourceId(R.styleable.CommonToolbarRelativeLayout_cl_drawableRight, -1);
        this.drawableRightChange = a.getResourceId(R.styleable.CommonToolbarRelativeLayout_cl_drawableRightChange, -1);
        this.drawableSubRight = a.getResourceId(R.styleable.CommonToolbarRelativeLayout_cl_drawableRight, -1);
        this.drawableSubRightChange = a.getResourceId(R.styleable.CommonToolbarRelativeLayout_cl_drawableRightChange, -1);
        this.drawableLeft = a.getResourceId(R.styleable.CommonToolbarRelativeLayout_cl_drawableLeft, -1);
        this.drawableLeftChange = a.getResourceId(R.styleable.CommonToolbarRelativeLayout_cl_drawableLeftChange, -1);
        this.drawableMiddle = a.getResourceId(R.styleable.CommonToolbarRelativeLayout_cl_drawableMiddle, -1);
        this.drawableMiddleChange = a.getResourceId(R.styleable.CommonToolbarRelativeLayout_cl_drawableMiddleChange, -1);
        this.textStartVisible = a.getBoolean(R.styleable.CommonToolbarRelativeLayout_cl_textStartVisible, true);
        this.dividerVisible = a.getBoolean(R.styleable.CommonToolbarRelativeLayout_cl_dividerVisible, false);

        if (middleTextChange.equals("")) middleTextChange = middleText;
        a.recycle();
        viewTop.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtil.getStatusHeight(context)));
        initToolbar();

    }

    public void setListener(OnFadeCommonToolbarListener listener) {
        this.listener = listener;
    }

    private void setVisibleView(View view, boolean visible) {
        view.setVisibility(visible ? VISIBLE : GONE);
    }

    private void initToolbar() {
        divider.setVisibility(GONE);
        txtMiddle.setText(middleText);
        toolBar.setBackgroundColor(ScrollUtils.getColorWithAlpha(alphaMain, getResources().getColor(R.color.white)));
        txtMiddle.setTextColor(textColor);
        txtMiddle.setTypeface(txtMiddle.getTypeface(), textStyle);
        setVisibleView(txtMiddle, textStartVisible);
        setVisibleView(imgMiddle, drawableMiddle != NO_ID);
        setVisibleView(btnSubRight, drawableSubRight != NO_ID);
        ImageUtils.loadImageDrawableByGlide(context, drawableRight, btnRight);
        ImageUtils.loadImageDrawableByGlide(context, drawableLeft, btnLeft);
        btnLeft.setOnClickListener(this);
        toolBar.setOnClickListener(this);
        btnRight.setOnClickListener(this);
        btnMiddle.setOnClickListener(this);

    }

    public void setRightDrawable(@DrawableRes int drawableRight, @DrawableRes int drawableRightChange) {
        this.drawableRight = drawableRight;
        this.drawableRightChange = drawableRightChange;
    }

    public void setLeftDrawable(@DrawableRes int drawableLeft, @DrawableRes int drawableLeftChange) {
        this.drawableLeft = drawableLeft;
        this.drawableLeftChange = drawableLeftChange;
    }

    public void setMiddleDrawable(@DrawableRes int drawableMiddle, @DrawableRes int drawableMiddleChange) {
        this.drawableMiddle = drawableMiddle;
        this.drawableMiddleChange = drawableMiddleChange;
    }

    public void setTextMiddle(String text) {
        middleText = text;
        txtMiddle.setText(text);
    }

    public void setTextMiddleChange(String text) {
        middleTextChange = text;
    }


    public void setAlphaToolbar(int scrollY) {
        ToolbarUtils.settingCommonToolbar(context, scrollY,
                viewTop,
                btnLeft,
                btnRight,
                txtMiddle,
                rlBot,
                divider,
                imgMiddle,
                mParallaxStartFadeHeight,
                mParallaxEndFadeHeight,
                drawableRight,
                drawableRightChange,
                drawableLeft,
                drawableLeftChange,
                drawableMiddle,
                drawableMiddleChange,
                middleText,
                textColor,
                textStartVisible,
                dividerVisible,
                middleTextChange);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLeft:
                listener.onClickedToolbar(v, TYPE_LEFT_CLICK);
                break;
            case R.id.btnRight:
                listener.onClickedToolbar(v, TYPE_RIGHT_CLICK);
                break;
            case R.id.toolBar:
                listener.onClickedToolbar(v, TYPE_TOOLBAR_CLICK);
                break;
            case R.id.btnMiddle:
                listener.onClickedToolbar(v, TYPE_MIDDLE_CLICK);
                break;
        }
    }
}

