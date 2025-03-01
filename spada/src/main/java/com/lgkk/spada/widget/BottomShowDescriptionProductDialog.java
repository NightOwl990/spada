package com.lgkk.spada.widget;


import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.lgkk.base_project.widget.FlipVerticalSwingEnterDialog.BottomBaseDialog;
import com.lgkk.base_project.widget.FlipVerticalSwingEnterDialog.OnBottomSettingClickListener;
import com.lgkk.base_project.widget.ItemOffsetDecoration;
import com.lgkk.spada.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BottomShowDescriptionProductDialog extends BottomBaseDialog<BottomShowDescriptionProductDialog> {

    @BindView(R.id.txtDescription)
    TextView txtDescription;
    OnBottomSettingClickListener onBottomSettingClickListener;
    private String description;
    private Context context;

    private ItemOffsetDecoration dividerDecoration;


    public BottomShowDescriptionProductDialog(Context context, View animateView, String description, OnBottomSettingClickListener onBottomSettingClickListener) {
        super(context, animateView);
        this.context = context;
        this.description = description;
        this.onBottomSettingClickListener = onBottomSettingClickListener;
    }

    public BottomShowDescriptionProductDialog(Context context) {
        super(context);
    }


    @Override
    public View onCreateView() {
        dismissAnim(null);
        View inflate = View.inflate(mContext, R.layout.dialog_bottom_show_description_product, null);
        ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void setUiBeforShow() {
        txtDescription.setText(description);
    }
}

