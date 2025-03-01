package com.lgkk.spada.screen.home.widget;

import android.content.Context;
import android.content.res.TypedArray;
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
import com.lgkk.base_project.utils.ToolbarUtils;
import com.lgkk.base_project.utils.ImageUtils;
import com.lgkk.base_project.utils.ScreenUtil;
import com.lgkk.base_project.widget.fadetoolbar.ScrollUtils;
import com.lgkk.base_project.widget.roundedimageview.RoundedImageView;
import com.lgkk.spada.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FadeShopToolbar extends RelativeLayout implements View.OnClickListener {
    public static final int TYPE_LEFT_CLICK = 0;
    public static final int TYPE_RIGHT_CLICK = 1;
    public static final int TYPE_MIDDLE_CLICK = 2;
    public static final int TYPE_TOOLBAR_CLICK = 3;
    public static final int TYPE_SUB_RIGHT_CLICK = 4;
    @BindView(R.id.viewTop)
    View viewTop;
    @BindView(R.id.imgLeft)
    ImageView imgLeft;
    @BindView(R.id.txtLeft)
    TextView txtLeft;
    @BindView(R.id.btnLeft)
    LinearLayout btnLeft;
    @BindView(R.id.imgMiddle)
    ImageView imgMiddle;
    @BindView(R.id.txtMiddle)
    TextView txtMiddle;
    @BindView(R.id.btnMiddle)
    RelativeLayout btnMiddle;
    @BindView(R.id.imgBgMiddle)
    RoundedImageView imgBgMiddle;
    @BindView(R.id.btnSubRight)
    ImageView btnSubRight;
    @BindView(R.id.imgIconRight)
    ImageView imgIconRight;
    @BindView(R.id.txtAmountRight)
    TextView txtAmountRight;
    @BindView(R.id.btnRight)
    RelativeLayout btnRight;
    @BindView(R.id.lnRight)
    LinearLayout lnRight;
    @BindView(R.id.rlBot)
    RelativeLayout rlBot;
    @BindView(R.id.toolBar)
    RelativeLayout toolBar;
    private int mParallaxStartFadeHeight;
    private int mParallaxEndFadeHeight;
    private int mParallaxMiddleFadeHeight;
    private float alphaMain;
    private int drawableRight;
    private int drawableRightChange;
    private int drawableLeft;
    private int drawableLeftChange;
    private int drawableMiddle = NO_ID;
    private int drawableSubRight = NO_ID;
    private int drawableMiddleChange = NO_ID;
    private String middleText = "";
    private int textColor;
    private Context context;
    private String middleTextChange = "";
    private OnFadeCommonToolbarListener listener;
    int length45 = getResources().getDimensionPixelSize(R.dimen.length_45);
    int length80 = getResources().getDimensionPixelSize(R.dimen.length_80);


    public FadeShopToolbar(Context context) {
        this(context, null);
    }

    public FadeShopToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_fade_shop_toolbar, this, true);
        ButterKnife.bind(this, view);
        this.context = context;
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FadeShopToolbar, 0, 0);

        this.mParallaxStartFadeHeight = a.getInt(R.styleable.FadeShopToolbar_fst_ParallaxStartFadeHeight, 0);
        this.mParallaxEndFadeHeight = a.getInt(R.styleable.FadeShopToolbar_fst_ParallaxEndFadeHeight, 0);
        this.mParallaxMiddleFadeHeight = a.getInt(R.styleable.FadeShopToolbar_fst_ParallaxMiddleFadeHeight, 0);
        this.alphaMain = a.getFloat(R.styleable.FadeShopToolbar_fst_alpha, 0f);
        this.middleText = a.getString(R.styleable.FadeShopToolbar_fst_text);
        this.middleTextChange = a.getString(R.styleable.FadeShopToolbar_fst_textChange);
        this.textColor = a.getColor(R.styleable.FadeShopToolbar_fst_textColor, 0);
        this.drawableRight = a.getResourceId(R.styleable.FadeShopToolbar_fst_drawableRight, -1);
        this.drawableRightChange = a.getResourceId(R.styleable.FadeShopToolbar_fst_drawableRightChange, -1);
        this.drawableLeft = a.getResourceId(R.styleable.FadeShopToolbar_fst_drawableLeft, -1);
        this.drawableLeftChange = a.getResourceId(R.styleable.FadeShopToolbar_fst_drawableLeftChange, -1);
        this.drawableMiddle = a.getResourceId(R.styleable.FadeShopToolbar_fst_drawableMiddle, -1);
        this.drawableMiddleChange = a.getResourceId(R.styleable.FadeShopToolbar_fst_drawableMiddleChange, -1);
        this.drawableSubRight = a.getResourceId(R.styleable.FadeShopToolbar_fst_drawableSubRight, -1);

        a.recycle();
        viewTop.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtil.getStatusHeight(context)));
        initToolbar();

    }

    public void setListener(OnFadeCommonToolbarListener listener) {
        this.listener = listener;
    }

    public void setAmountCart(int amount) {
        txtAmountRight.setText(amount + "");
        txtAmountRight.setVisibility(View.VISIBLE);
        if (amount == 0) {
            txtAmountRight.setVisibility(GONE);
        } else if (amount > 99) {
            txtAmountRight.setText("99+");
        }
    }


    private void initToolbar() {
        txtMiddle.setText(middleText);
        toolBar.setBackgroundColor(ScrollUtils.getColorWithAlpha(alphaMain, getResources().getColor(R.color.white)));
        txtMiddle.setTextColor(textColor);
        ImageUtils.loadImageDrawableByGlide(context, drawableRight, imgIconRight);
        ImageUtils.loadImageDrawableByGlide(context, drawableLeft, imgLeft);
        ImageUtils.loadImageDrawableByGlide(context, drawableSubRight, btnSubRight);
        btnLeft.setScaleX(0f);
        btnLeft.setScaleY(0f);
        btnLeft.setPivotX(0);
        btnLeft.setPivotY(length45 / 2);
        btnLeft.setOnClickListener(this);
        toolBar.setOnClickListener(this);
        btnRight.setOnClickListener(this);
        btnSubRight.setOnClickListener(this);
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
        ToolbarUtils.settingFadeShopToolbar(context, scrollY, mParallaxStartFadeHeight, mParallaxMiddleFadeHeight,
                mParallaxEndFadeHeight, btnLeft,
                btnMiddle, rlBot, viewTop,
                drawableRightChange, imgIconRight, drawableLeftChange,
                imgLeft, imgBgMiddle, txtMiddle,
                middleText, drawableMiddleChange, imgMiddle, drawableRight,
                drawableLeft, textColor, drawableMiddle);
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
            case R.id.btnSubRight:
                listener.onClickedToolbar(v, TYPE_SUB_RIGHT_CLICK);
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

