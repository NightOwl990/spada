package com.lgkk.spada.widget;


import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.lgkk.base_project.base.listener.OnBottomSettingCommunityClickListener;
import com.lgkk.base_project.widget.FlipVerticalSwingEnterDialog.BottomBaseDialog;
import com.lgkk.spada.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommunitySettingDialog extends BottomBaseDialog<CommunitySettingDialog> {
    OnBottomSettingCommunityClickListener onBottomSettingCommunityClickListener;
    @BindView(R.id.lnReply)
    LinearLayout lnSave;
    @BindView(R.id.lnCopy)
    LinearLayout lnNotification;
    @BindView(R.id.lnDelete)
    LinearLayout lnDelete;
    @BindView(R.id.lnReport)
    LinearLayout lnReport;
    private Context context;
    private boolean disableRemove;


    public CommunitySettingDialog(Context context, View animateView, boolean disableRemove, OnBottomSettingCommunityClickListener onBottomSettingCommunityClickListener) {
        super(context, animateView);
        this.context = context;
        this.disableRemove = disableRemove;
        this.onBottomSettingCommunityClickListener = onBottomSettingCommunityClickListener;
    }

    public CommunitySettingDialog(Context context) {
        super(context);
    }

    @Override
    public View onCreateView() {
        dismissAnim(null);
        View inflate = View.inflate(mContext, R.layout.dialog_community_setting, null);
        ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void setUiBeforShow() {
        if (disableRemove) lnDelete.setVisibility(View.GONE);
        else lnDelete.setVisibility(View.VISIBLE);

        lnSave.setOnClickListener(v -> {
            onBottomSettingCommunityClickListener.onBottomCommunitySettingClick(0, v);
            dismiss();
        });
        lnDelete.setOnClickListener(v -> {
            onBottomSettingCommunityClickListener.onBottomCommunitySettingClick(2, v);
            dismiss();
        });
        lnNotification.setOnClickListener(v -> {
            onBottomSettingCommunityClickListener.onBottomCommunitySettingClick(1, v);
            dismiss();
        });
        lnReport.setOnClickListener(v -> {
            onBottomSettingCommunityClickListener.onBottomCommunitySettingClick(3, v);
            dismiss();
        });
    }
}
