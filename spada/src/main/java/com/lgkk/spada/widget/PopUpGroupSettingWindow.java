package com.lgkk.spada.widget;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lgkk.spada.R;
import com.lgkk.spada.event.EventManager;
import com.lgkk.spada.event.LeaveGroupEvent;

public class PopUpGroupSettingWindow extends PopupWindow {

    private View mContentView;
    private Activity mActivity;
    private TextView txtLeaveGroup;
    private TextView txtSettingNotiGroup;

    public PopUpGroupSettingWindow(final Activity activity, int adapterPosition) {
        mActivity = activity;

        mContentView = LayoutInflater.from(activity).inflate(R.layout.custom_popup_setting_groupdetail, null);
        LinearLayout container = mContentView.findViewById(R.id.container);

        int width = (int) activity.getResources().getDimension(R.dimen.length_250);
        int height = (int) activity.getResources().getDimension(R.dimen.length_120);
        setWidth(width);
        setHeight(height);

        setContentView(mContentView);

        setFocusable(true);
        setOutsideTouchable(true);
        setTouchable(true);
        Window window = activity.getWindow();
        setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
//        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        setAnimationStyle(R.style.PopUpSettingWindow);

        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                lighton();
            }
        });

        txtLeaveGroup = mContentView.findViewById(R.id.txtLeaveGroup);
        txtSettingNotiGroup = mContentView.findViewById(R.id.txtSettingNotiGroup);
        txtLeaveGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                EventBus.getDefault().post(new LeaveGroupEvent(adapterPosition));
                dismiss();
            }
        });
        txtSettingNotiGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                EventManager.initOpenSettingNotiGroupEvent(adapterPosition);
                dismiss();
            }
        });
    }

    private void lighton() {
        WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
        lp.alpha = 1.0f;
        mActivity.getWindow().setAttributes(lp);
    }

    private void lightoff() {
        WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
        lp.alpha = 0.3f;
        mActivity.getWindow().setAttributes(lp);
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff) {
//        lightoff();
        super.showAsDropDown(anchor, xoff, yoff);
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
//        lightoff();
        super.showAtLocation(parent, gravity, x, y);
    }
}

