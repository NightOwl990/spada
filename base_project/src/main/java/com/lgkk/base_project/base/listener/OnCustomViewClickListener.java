package com.lgkk.base_project.base.listener;

import android.view.View;

public interface OnCustomViewClickListener {
    void onClickCustomView(View v, String action, int position);
    void onClickCustomView(View v, String action, int position, int position2);
}
