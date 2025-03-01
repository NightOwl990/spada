package com.lgkk.spada.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.lgkk.base_project.base.listener.OnItemRvClickListener;
import com.lgkk.base_project.widget.basepopup.BasePopupWindow;
import com.lgkk.spada.R;
import com.lgkk.spada.screen._main.CheckUpdateContentAdapter;

import java.util.List;


public class CheckUpdatePopupDialog extends BasePopupWindow implements View.OnClickListener, OnItemRvClickListener {
    private String versionNamme;
    private String link;
    private LinearLayout rootView;
    private TextView txtVersion, txtDescription, btnUpdate, btnDismiss;
    private Context context;
    private RecyclerView rvContent;
    private CheckUpdateContentAdapter contentAdapter;
    private List<String> contentList;

    public CheckUpdatePopupDialog(Context context, List<String> contentList, String versionNamme) {
        super(context);
        this.contentList = contentList;
        this.context = context;
        this.versionNamme = versionNamme;
        setClipChildren(false);
        setPopupGravity(Gravity.CENTER);
        bindEvent(context);
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    protected Animation onCreateShowAnimation() {
        return null;
    }

    @Override
    protected Animation onCreateDismissAnimation() {
        return null;
    }

    private void bindEvent(Context context) {

        txtVersion = findViewById(R.id.txtVersion);
        txtDescription = findViewById(R.id.txtDescription);
        rootView = findViewById(R.id.rootView);
        rvContent = findViewById(R.id.rvContent);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDismiss = findViewById(R.id.btnDismiss);
        rootView.setOnClickListener(this);
        btnDismiss.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);

        contentAdapter = new CheckUpdateContentAdapter(context, contentList, this);
        LinearLayoutManager layoutManagerNews = new LinearLayoutManager(context);
        rvContent.setNestedScrollingEnabled(false);
        rvContent.setLayoutManager(layoutManagerNews);
        rvContent.setAdapter(contentAdapter);
        contentAdapter.notifyDataSetChanged();
        txtVersion.setText("Đã có phiên bản mới " + versionNamme);
    }

    //=============================================================super methods


    @Override
    public Animator onCreateShowAnimator() {
//        return getDefaultSlideFromBottomAnimationSet();
        AnimatorSet set = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            set = new AnimatorSet();

            set.playTogether(
                    ObjectAnimator.ofFloat(getDisplayAnimateView(), "translationY", 1050, 0).setDuration(500),
                    ObjectAnimator.ofFloat(getDisplayAnimateView(), "alpha", 0.4f, 1).setDuration(500));

        }
        return set;
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.layout_popup_check_update);
    }

    @Override
    public Animator onCreateDismissAnimator() {
        AnimatorSet set = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            set = new AnimatorSet();
            if (getDisplayAnimateView() != null) {
                set.playTogether(
                        ObjectAnimator.ofFloat(getDisplayAnimateView(), "translationY", 0, 250).setDuration(400),
                        ObjectAnimator.ofFloat(getDisplayAnimateView(), "alpha", 1, 0.4f).setDuration(250 * 3 / 2));
            }
        }
        return set;
    }

    //=============================================================click event
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDismiss:
                dismiss();
                break;
            case R.id.btnUpdate:
                final String appPackageName = context.getPackageName(); // getPackageName() from Context or Activity object
                try {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link)));
                }
                dismiss();
                break;
        }

    }

    @Override
    public void onItemRvClick(View view, Object item, int adapterPosition) {

    }
}
