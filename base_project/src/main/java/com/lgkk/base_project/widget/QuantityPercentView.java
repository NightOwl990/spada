package com.lgkk.base_project.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.lgkk.base_project.R;
import com.lgkk.base_project.base.listener.OnCustomViewClickListener;

public class QuantityPercentView extends RelativeLayout {
    public static final String TYPE_CHOOSE_CLICK = "quantityPercent";

    ImageView imgPercent;

    TextView txtSoldCount;

    TextView txtPercent;

    private Context context;
    private int percent = 1;
    private int soldCount = 0;
    private OnCustomViewClickListener listener;

    public QuantityPercentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_quantity_percent_view, this, true);
        txtSoldCount = view.findViewById(R.id.txtSoldCount);
        imgPercent = view.findViewById(R.id.imgPercent);
        txtPercent = view.findViewById(R.id.txtPercent);
        this.context = context;
    }

    public QuantityPercentView(Context context) {
        this(context, (AttributeSet) null);
    }


    public void setListener(OnCustomViewClickListener listener) {
        this.listener = listener;
    }

    public void setData(int percent, int soldCount) {
        this.percent = percent;
        this.soldCount = soldCount;
        initView();
    }


    private void initView() {
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
                imgPercent.setLayoutParams(new LayoutParams((getWidth()*percent/100), LayoutParams.MATCH_PARENT));//height is ready
            }
        });
        txtPercent.setText(percent+"%");
        txtSoldCount.setText("Đã bán "+soldCount);
    }
}