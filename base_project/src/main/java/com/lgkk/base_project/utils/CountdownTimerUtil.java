package com.lgkk.base_project.utils;

import cn.iwgang.countdownview.CountdownView;

public class CountdownTimerUtil {
    public static void setCountDownTimer(CountdownView cdm, long milisecond) {
        cdm.setTag("cdtimer1");
        cdm.start(milisecond);
    }
}
