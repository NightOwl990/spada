package com.lgkk.spada.widget;


import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lgkk.base_project.widget.CircleImageView;
import com.lgkk.base_project.widget.FlipVerticalSwingEnterDialog.BottomBaseDialog;
import com.lgkk.base_project.widget.FlipVerticalSwingEnterDialog.OnBottomSettingClickListener;
import com.lgkk.spada.R;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangeAvatarDialog extends BottomBaseDialog<ChangeAvatarDialog> {
    OnBottomSettingClickListener onBottomSettingClickListener;
    @BindView(R.id.txtDescription)
    TextView txtDescription;
    @BindView(R.id.imgAvatar)
    CircleImageView imgAvatar;
    @BindView(R.id.btnCancel)
    LinearLayout btnCancel;
    @BindView(R.id.btnSubmit)
    LinearLayout btnSubmit;

    private Context context;
    private String urlImage;

    public ChangeAvatarDialog(Context context, View animateView, String urlImage, OnBottomSettingClickListener onBottomSettingClickListener) {
        super(context, animateView);
        this.context = context;
        this.urlImage = urlImage;
        this.onBottomSettingClickListener = onBottomSettingClickListener;
    }

    public ChangeAvatarDialog(Context context) {
        super(context);
    }

    @Override
    public View onCreateView() {
        dismissAnim(null);
        View inflate = View.inflate(mContext, R.layout.layout_popup_change_avatar, null);
        ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void setUiBeforShow() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.centerCrop();
        Glide.with(context).asBitmap().apply(requestOptions).load(new File(urlImage)).into(imgAvatar);
        btnCancel.setOnClickListener(v -> {
            onBottomSettingClickListener.onBottomSettingClick(0, v);
        });
        btnSubmit.setOnClickListener(v -> {
            onBottomSettingClickListener.onBottomSettingClick(1, v);
        });
    }
}
