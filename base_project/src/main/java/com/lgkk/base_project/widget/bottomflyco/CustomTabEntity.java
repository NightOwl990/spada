package com.lgkk.base_project.widget.bottomflyco;

import androidx.annotation.DrawableRes;

public interface CustomTabEntity {

    String getTabTitle();

    @DrawableRes
    int getTabSelectedIcon();

    @DrawableRes
    int getTabUnselectedIcon();

}
