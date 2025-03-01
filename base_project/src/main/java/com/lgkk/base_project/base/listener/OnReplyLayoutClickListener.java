package com.lgkk.base_project.base.listener;

import android.view.View;

public interface OnReplyLayoutClickListener<T> {
    void onReplyLayoutClick(View view, T item, int currentPosition, int commentPosition);
}
