package com.lgkk.spada.widget;


import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lgkk.base_project.base.listener.OnSettingNotificationListener;
import com.lgkk.base_project.utils.ImageUtils;
import com.lgkk.base_project.widget.CircleImageView;
import com.lgkk.base_project.widget.FlipVerticalSwingEnterDialog.BottomBaseDialog;
import com.lgkk.spada.R;
import com.lgkk.spada.model.home.NotificationBean;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationSettingDialog extends BottomBaseDialog<NotificationSettingDialog> {
    OnSettingNotificationListener onSettingNotificationListener;

    @BindView(R.id.imgAvatar)
    CircleImageView imgAvatar;
    @BindView(R.id.txtDescription)
    TextView txtDescription;
    @BindView(R.id.rlHide)
    RelativeLayout rlHide;

    private NotificationBean notificationBean;
    private int adapterPosition;
    private Context context;
    private String type;

    public NotificationSettingDialog(Context context, View animateView, NotificationBean notificationBean, OnSettingNotificationListener onSettingNotificationListener, int adapterPosition, String type) {
        super(context, animateView);
        this.context = context;
        this.type = type;
        this.notificationBean = notificationBean;
        this.adapterPosition = adapterPosition;
        this.onSettingNotificationListener = onSettingNotificationListener;
    }

    public NotificationSettingDialog(Context context) {
        super(context);
    }

    @Override
    public View onCreateView() {
        dismissAnim(null);
        View inflate = View.inflate(mContext, R.layout.layout_popup_notification_setting, null);
        ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void setUiBeforShow() {
        txtDescription.setText(notificationBean.getData().getUserName() + notificationBean.getContent() + notificationBean.getData().getGroupName() + ".");
        ImageUtils.loadCircleImageByGlide(context, notificationBean.getIcon(), imgAvatar);
        imgAvatar.setOnClickListener(v -> {
            onSettingNotificationListener.onSettingClicked(-1, v, notificationBean, adapterPosition, type);
        });

        rlHide.setOnClickListener(v -> {
            onSettingNotificationListener.onSettingClicked(0, v, notificationBean, adapterPosition, type);
        });
    }
}
