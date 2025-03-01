package com.lgkk.base_project.base.listener;

import android.view.View;

public interface OnItemRvClickListener<T> {
    void onItemRvClick(View view, T item, int adapterPosition);
}
