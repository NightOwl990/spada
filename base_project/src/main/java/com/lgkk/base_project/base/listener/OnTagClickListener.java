package com.lgkk.base_project.base.listener;

import android.view.View;

import com.lgkk.base_project.widget.FlowTagLayout;

/**
 * Created by HanHailong on 15/10/20.
 */
public interface OnTagClickListener {
    void onItemClick(FlowTagLayout parent, View view, int position);
}
