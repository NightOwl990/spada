package com.lgkk.base_project.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.lgkk.base_project.R;


public class LoadingDialog extends Dialog {
    private BallsLoadingView loadingView;

    public LoadingDialog(Context context) {
        this(context, 0);
    }

    public LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static LoadingDialog instance(Activity activity) {
        RelativeLayout loadingView = (RelativeLayout) View.inflate(activity, R.layout.common_progress_view, null);
//        v.setColor(ContextCompat.getColor(activity, R.color.menu_bg_color));
        LoadingDialog dialog = new LoadingDialog(activity, R.style.loading_dialog);
        dialog.setContentView(loadingView,
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT)
        );
        return dialog;
    }

    public void stopLoadingView() {
        loadingView.release();
    }

}