package com.lgkk.spada.widget;


import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lgkk.base_project.utils.ImageUtils;
import com.lgkk.spada.R;

public class NutritionNoticeHeader extends LinearLayout {
    private String text = "";
    private int noticeDrawable;
    private int textColor;
    private Context context;

    public NutritionNoticeHeader(Context context) {
        this(context, null);
    }

    public NutritionNoticeHeader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        LayoutInflater.from(context).inflate(R.layout.layout_nutrition_notice_header, this, true);
        text = "Nên";
        this.context = context;
        noticeDrawable = R.drawable.ic_tool_candu;
        textColor = R.color.green_notice_nutrition;
        initHeader();
    }

    public void setNotice(int noticeType) {
        switch (noticeType) {
            case 1:
                text = "Nên";
                noticeDrawable = R.drawable.ic_tool_candu;
                textColor = R.color.green_notice_nutrition;
                break;
            case 2:
                text = "Không nên";
                noticeDrawable = R.drawable.ic_tool_forbit;
                textColor = R.color.red_notice_nutrition;
                break;
            case 3:
                text = "Cần lưu ý";
                noticeDrawable = R.drawable.ic_tool_notice;
                textColor = R.color.yellow_notice_nutrition;
                break;
        }
        initHeader();
    }

    private void initHeader() {
        ImageView imgNotice = findViewById(R.id.imgNotice);
        TextView txtNotice = findViewById(R.id.txtNotice);
        txtNotice.setText(text);
        txtNotice.setTextColor(context.getResources().getColor(textColor));
        ImageUtils.loadImageDrawableByGlide(context, noticeDrawable, imgNotice);
    }
}