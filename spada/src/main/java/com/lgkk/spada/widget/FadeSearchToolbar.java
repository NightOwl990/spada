package com.lgkk.spada.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
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


import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FadeSearchToolbar extends RelativeLayout implements View.OnClickListener {
    public static final int TYPE_LEFT_CLICK = 0;
    public static final int TYPE_RIGHT_CLICK = 1;
    public static final int TYPE_TOOLBAR_CLICK = 2;
    public static final int TYPE_SEARCH_CLICK = 3;
    @BindView(R.id.viewTop)
    View viewTop;
    @BindView(R.id.imgLeft)
    ImageView imgLeft;
    @BindView(R.id.txtMiddle)
    TextView txtMiddle;
    @BindView(R.id.imgIconRight)
    ImageView imgIconRight;
    @BindView(R.id.txtAmountRight)
    TextView txtAmountRight;
    @BindView(R.id.btnRight)
    RelativeLayout btnRight;
    @BindView(R.id.toolBar)
    RelativeLayout toolBar;
    @BindView(R.id.rlSearch)
    RelativeLayout rlSearch;
    @BindView(R.id.rlBot)
    RelativeLayout rlBot;

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


    public FadeSearchToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_common_search_toolbar, this, true);
        unbinder1 = ButterKnife.bind(this, view);
        this.context = context;
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FadeSearchToolbar, 0, 0);
        this.mParallaxStartFadeHeight = a.getInt(R.styleable.FadeSearchToolbar_fs_ParallaxStartFadeHeight, 0);
        this.mParallaxEndFadeHeight = a.getInt(R.styleable.FadeSearchToolbar_fs_ParallaxEndFadeHeight, 0);
        this.drawableRight = a.getResourceId(R.styleable.FadeSearchToolbar_fs_drawableRight, 0);
        this.drawableRightChange = a.getResourceId(R.styleable.FadeSearchToolbar_fs_drawableRightChange, 0);
        this.alphaMain = a.getFloat(R.styleable.FadeSearchToolbar_fs_alpha, 0f);
        this.middleText = a.getString(R.styleable.FadeSearchToolbar_fs_text);
        this.textColor = a.getColor(R.styleable.FadeSearchToolbar_fs_textColor, 0);

        a.recycle();
        viewTop.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtil.getStatusHeight(context)));
        initToolbar();
    }

    public FadeSearchToolbar(Context context) {
        this(context, null);
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
        toolBar.setBackgroundColor(ScrollUtils.getColorWithAlpha(alphaMain, getResources().getColor(R.color.white)));
        txtMiddle.setText(middleText);
        txtMiddle.setAlpha(1f);
        ImageUtils.loadImageDrawableByGlide(context, drawableRight, imgIconRight);
        txtMiddle.setTextColor(textColor);
        imgLeft.setOnClickListener(this);
        btnRight.setOnClickListener(this);
        toolBar.setOnClickListener(this);
        rlSearch.setOnClickListener(this);
    }

    public void setAlphaToolbar(int scrollY) {
        int baseColor = getResources().getColor(R.color.white);
        float alpha2 = Math.min(1, (float) (scrollY - mParallaxStartFadeHeight) / (mParallaxEndFadeHeight - mParallaxStartFadeHeight));

        if (scrollY > mParallaxStartFadeHeight) {
            rlBot.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha2, baseColor));
//            txtMiddle.setAlpha(alpha2);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                viewTop.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha2, baseColor));
            } else {
                if (alpha2 >= 0.8f) {
                    viewTop.setBackgroundColor(ScrollUtils.getColorWithAlpha(0.8f, baseColor));
                }
            }
            viewTop.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha2, baseColor));
            rlSearch.setBackground(getResources().getDrawable(R.drawable.bg_round_home_search));
//            imgIconRight.setBackgroundColor(ScrollUtils.getColorWithAlpha(1f - alpha2 - 0.5f, getResources().getColor(R.color.black)));

            if (scrollY > mParallaxStartFadeHeight + 30) {
                ImageUtils.loadImageDrawableByGlide(context, R.drawable.baby_icon_nav_back, imgLeft);
                ImageUtils.loadImageDrawableByGlide(context, drawableRightChange, imgIconRight);

            } else {
                ImageUtils.loadImageDrawableByGlide(context, R.drawable.baby_icon_back, imgLeft);
                ImageUtils.loadImageDrawableByGlide(context, drawableRight, imgIconRight);
            }
        } else {
//            txtMiddle.setAlpha(0);
            rlBot.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, baseColor));
            viewTop.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, baseColor));
            rlSearch.setBackground(getResources().getDrawable(R.drawable.bg_round_border_white_fill));
//            imgIconRight.setBackgroundColor(ScrollUtils.getColorWithAlpha(0.5f, getResources().getColor(R.color.black)));
            ImageUtils.loadImageDrawableByGlide(context, R.drawable.baby_icon_back, imgLeft);
            ImageUtils.loadImageDrawableByGlide(context, drawableRight, imgIconRight);
        }

//        if (scrollY >= mParallaxEndFadeHeight) {
//            divider.setVisibility(View.VISIBLE);
//        } else {
//            divider.setVisibility(View.GONE);
//        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgLeft:
                listener.onClickedToolbar(v, TYPE_LEFT_CLICK);
                break;
            case R.id.btnRight:
                listener.onClickedToolbar(v, TYPE_RIGHT_CLICK);
                break;
            case R.id.toolBar:
                listener.onClickedToolbar(v, TYPE_TOOLBAR_CLICK);
                break;
            case R.id.rlSearch:
                listener.onClickedToolbar(v, TYPE_SEARCH_CLICK);
                break;
        }

    }
}
