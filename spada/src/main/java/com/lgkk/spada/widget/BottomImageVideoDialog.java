package com.lgkk.spada.widget;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.lgkk.base_project.widget.FlipVerticalSwingEnterDialog.OnBottomSettingClickListener;
import com.lgkk.spada.R;

public class BottomImageVideoDialog extends Dialog {
    public OnBottomSettingClickListener itemClickListener;
    LinearLayout lnAddImage, lnAddVideo;

    public BottomImageVideoDialog(Context context, OnBottomSettingClickListener listener) {
        this(context, 0);
        this.itemClickListener = listener;
    }

    public BottomImageVideoDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public BottomImageVideoDialog instance(Activity activity) {
        View v = (View) View.inflate(activity, R.layout.dialog_bottom_seting, null);
        final BottomImageVideoDialog dialog = new BottomImageVideoDialog(activity, R.style.BottomSettingDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_bottom_seting);
        final Window window = dialog.getWindow();
        WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
        wmlp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wmlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        wmlp.gravity = Gravity.BOTTOM;
//        wmlp.x = 100;
//        wmlp.y = 50;
//                window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        lnAddImage = dialog.findViewById(R.id.lnAddImage);
        lnAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onBottomSettingClick(1, dialog);
            }
        });

        lnAddVideo = dialog.findViewById(R.id.lnAddVideo);
        lnAddVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onBottomSettingClick(2, dialog);
            }
        });
        return dialog;
    }
}

