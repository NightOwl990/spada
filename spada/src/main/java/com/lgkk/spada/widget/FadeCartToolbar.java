package com.lgkk.spada.widget;


import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lgkk.base_project.base.listener.OnFadeCommonToolbarListener;
import com.lgkk.base_project.utils.ImageUtils;
import com.lgkk.base_project.utils.ScreenUtil;
import com.lgkk.base_project.widget.fadetoolbar.ScrollUtils;
import com.lgkk.spada.R;
import com.lgkk.base_project.widget.roundedimageview.RoundedImageView;
import com.lgkk.spada.screen.service.activity.CartActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FadeCartToolbar extends RelativeLayout implements View.OnClickListener {
    public static final int TYPE_LEFT_CLICK = 0;
    public static final int TYPE_RIGHT_CLICK = 1;
    public static final int TYPE_TOOLBAR_CLICK = 2;
    @BindView(R.id.viewTop)
    View viewTop;
    @BindView(R.id.imgBack)
    RoundedImageView imgBack;
    @BindView(R.id.btnLeft)
    RelativeLayout btnBack;
    @BindView(R.id.imgCart)
    RoundedImageView imgCart;
    @BindView(R.id.txtAmountCart)
    TextView txtAmountCart;
    @BindView(R.id.btnGoToCart)
    RelativeLayout btnGoToCart;
    @BindView(R.id.btnSetting)
    ImageView btnSetting;
    @BindView(R.id.divider)
    View divider;
    @BindView(R.id.rlBot)
    RelativeLayout rlBot;
    @BindView(R.id.toolBar)
    RelativeLayout toolBar;
    @BindView(R.id.imgIconCart)
    ImageView imgIconCart;
    @BindView(R.id.txtMiddle)
    TextView txtMiddle;

    private int mParallaxStartFadeHeight;
    private int mParallaxEndFadeHeight;
    private float alphaMain;
    private int drawableRight;
    private int drawableRightChange;
    private String middleText = "";
    private int textColor;
    private Unbinder unbinder1;
    private OnFadeCommonToolbarListener listener;
    private Context context;


    public FadeCartToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_common_cart_toolbar, this, true);
        unbinder1 = ButterKnife.bind(this, view);
        this.context = context;
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CartToolbarRelativeLayout, 0, 0);
        this.mParallaxStartFadeHeight = a.getInt(R.styleable.CartToolbarRelativeLayout_ctrl_ParallaxStartFadeHeight, 0);
        this.mParallaxEndFadeHeight = a.getInt(R.styleable.CartToolbarRelativeLayout_ctrl_ParallaxEndFadeHeight, 0);
        this.drawableRight = a.getResourceId(R.styleable.CartToolbarRelativeLayout_ctrl_drawableRight, 0);
        this.drawableRightChange = a.getResourceId(R.styleable.CartToolbarRelativeLayout_ctrl_drawableRightChange, 0);
        this.alphaMain = a.getFloat(R.styleable.CartToolbarRelativeLayout_ctrl_alpha, 0f);
        this.middleText = a.getString(R.styleable.CartToolbarRelativeLayout_ctrl_text);
        this.textColor = a.getColor(R.styleable.CartToolbarRelativeLayout_ctrl_textColor, 0);

        a.recycle();
        viewTop.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtil.getStatusHeight(context)));
        initToolbar();
    }

    public FadeCartToolbar(Context context) {
        this(context, null);
    }

    public void setListener(OnFadeCommonToolbarListener listener) {
        this.listener = listener;
    }

    public void setAmountCart(int amount) {
        txtAmountCart.setText(amount + "");
        txtAmountCart.setVisibility(View.VISIBLE);
        if (amount == 0) {
            txtAmountCart.setVisibility(GONE);
        } else if (amount > 99) {
            txtAmountCart.setText("99+");
        }
    }

    private void initToolbar() {
        divider.setVisibility(GONE);
        toolBar.setBackgroundColor(ScrollUtils.getColorWithAlpha(alphaMain, getResources().getColor(R.color.white)));
        txtMiddle.setText(middleText);
        txtMiddle.setAlpha(0);
        ImageUtils.loadImageDrawableByGlide(context, drawableRight, imgIconCart);
        txtMiddle.setTextColor(textColor);
        btnBack.setOnClickListener(this);
        btnGoToCart.setOnClickListener(this);
        toolBar.setOnClickListener(this);
    }

    public void setAlphaToolbar(int scrollY) {
        int baseColor = getResources().getColor(R.color.white);
        float alpha2 = Math.min(1, (float) (scrollY - mParallaxStartFadeHeight) / (mParallaxEndFadeHeight - mParallaxStartFadeHeight));

        if (scrollY > mParallaxStartFadeHeight) {
            rlBot.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha2, baseColor));
            txtMiddle.setAlpha(alpha2);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                viewTop.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha2, baseColor));
            } else {
                if (alpha2 >= 0.8f) {
                    viewTop.setBackgroundColor(ScrollUtils.getColorWithAlpha(0.8f, baseColor));
                }
            }
            viewTop.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha2, baseColor));
            imgBack.setBackgroundColor(ScrollUtils.getColorWithAlpha(1f - alpha2 - 0.5f, getResources().getColor(R.color.black)));
            imgCart.setBackgroundColor(ScrollUtils.getColorWithAlpha(1f - alpha2 - 0.5f, getResources().getColor(R.color.black)));

            if (scrollY > mParallaxStartFadeHeight + 30) {
                ImageUtils.loadImageDrawableByGlide(context, R.drawable.baby_icon_nav_back, imgBack);
                ImageUtils.loadImageDrawableByGlide(context, drawableRightChange, imgIconCart);

            } else {
                ImageUtils.loadImageDrawableByGlide(context, R.drawable.baby_icon_back, imgBack);
                ImageUtils.loadImageDrawableByGlide(context, drawableRight, imgIconCart);
            }
        } else {
            txtMiddle.setAlpha(0);
            rlBot.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, baseColor));
            viewTop.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, baseColor));
            imgBack.setBackgroundColor(ScrollUtils.getColorWithAlpha(0.5f, getResources().getColor(R.color.black)));
            imgCart.setBackgroundColor(ScrollUtils.getColorWithAlpha(0.5f, getResources().getColor(R.color.black)));
            ImageUtils.loadImageDrawableByGlide(context, R.drawable.baby_icon_back, imgBack);
            ImageUtils.loadImageDrawableByGlide(context, drawableRight, imgIconCart);
        }

        if (scrollY >= mParallaxEndFadeHeight) {
            divider.setVisibility(View.VISIBLE);
        } else {
            divider.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLeft:
                listener.onClickedToolbar(v, TYPE_LEFT_CLICK);
                break;
            case R.id.btnGoToCart:
                CartActivity.startActivity(context, "test");
                break;
            case R.id.toolBar:
                listener.onClickedToolbar(v, TYPE_TOOLBAR_CLICK);
                break;
        }

    }
}
