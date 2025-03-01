package com.lgkk.base_project.base.listener;

import android.view.View;

public interface OnCommentLayoutClickLisnener<T> {
    void onCommentLayoutClick(View view, T item, int adapterPosition);
}

