package com.lgkk.base_project.base.listener;

import android.view.View;

public interface OnSettingNotificationListener<T> {
    void onSettingClicked(int positionSetting, View view, T item, int adapterPosition, String type);
}
