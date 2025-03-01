package com.lgkk.spada.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.lgkk.base_project.utils.ImageUtils;
import com.lgkk.spada.R;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ChooseRatingTabLayout extends LinearLayout {
    @BindView(R.id.lnStar)
    LinearLayout lnStar;
    @BindView(R.id.txtRatingCount)
    TextView txtRatingCount;
    @BindView(R.id.txtAll)
    TextView txtAll;
    private Context context;
    private Unbinder unbinder;

    public ChooseRatingTabLayout(Context context) {
        this(context, null);
    }

    public ChooseRatingTabLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_rating_detail, this, true);
        this.context = context;
        unbinder = ButterKnife.bind(this, view);

    }

    public void setRatingCount(int ratingCount) {
        txtRatingCount.setText("(" + ratingCount + ")");
    }

    public void setRatingScore(int ratingScore) {
        if (ratingScore > 0) {
            txtRatingCount.setVisibility(VISIBLE);
            txtAll.setVisibility(GONE);
            for (int i = 0; i < ratingScore; i++) {
                ImageView imageView = new ImageView(context);
                ImageUtils.loadImageDrawableByGlide(context, R.drawable.evaluate_star_10, imageView);
                int height = (int) getResources().getDimension(R.dimen.length_8);
                int width = (int) getResources().getDimension(R.dimen.length_8);
                LayoutParams parms = new LayoutParams(width, height);
                imageView.setLayoutParams(parms);

                lnStar.addView(imageView);
            }
        } else {
            txtAll.setVisibility(VISIBLE);
            txtRatingCount.setVisibility(GONE);
            txtRatingCount.setText("");
        }
    }

}