package com.lgkk.spada.widget;


import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.lgkk.base_project.base.listener.OnCommentSettingDialogClickListener;
import com.lgkk.base_project.widget.FlipVerticalSwingEnterDialog.BottomBaseDialog;
import com.lgkk.spada.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentSettingDialog extends BottomBaseDialog<CommentSettingDialog> {
    OnCommentSettingDialogClickListener onCommentSettingDialogClickListener;
    @BindView(R.id.lnReply)
    LinearLayout lnReply;
    @BindView(R.id.lnCopy)
    LinearLayout lnCopy;
    @BindView(R.id.lnDelete)
    LinearLayout lnDelete;
    private Context context;
    private boolean disableRemove;
    private int adapterPosition;


    public CommentSettingDialog(Context context, View animateView, int adapterPosition, boolean disableRemove, OnCommentSettingDialogClickListener onCommentSettingDialogClickListener) {
        super(context, animateView);
        this.context = context;
        this.disableRemove = disableRemove;
        this.adapterPosition = adapterPosition;
        this.onCommentSettingDialogClickListener = onCommentSettingDialogClickListener;
    }

    public CommentSettingDialog(Context context) {
        super(context);
    }

    @Override
    public View onCreateView() {
        dismissAnim(null);
        View inflate = View.inflate(mContext, R.layout.layout_comment_setting_dialog, null);
        ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void setUiBeforShow() {
        if (disableRemove) lnDelete.setVisibility(View.GONE);
        else lnDelete.setVisibility(View.VISIBLE);

        lnReply.setOnClickListener(v -> {
            onCommentSettingDialogClickListener.onCommentSettingClick(0, adapterPosition, v);
            dismiss();
        });
        lnCopy.setOnClickListener(v -> {
            onCommentSettingDialogClickListener.onCommentSettingClick(1, adapterPosition, v);
            dismiss();
        });
        lnDelete.setOnClickListener(v -> {
            onCommentSettingDialogClickListener.onCommentSettingClick(2, adapterPosition, v);
            dismiss();
        });
    }
}

