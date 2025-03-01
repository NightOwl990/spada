package com.lgkk.spada.model;

/**
 * Created by Hoang Nam on 24/04/2017.
 */

public class BMI {
    private String Date;
    private String Time;
    private float Kick;
    private int stt;

    public BMI(String date, String time, float kick) {
        Date = date;
        Time = time;
        Kick = kick;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public float getKick() {
        return Kick;
    }

    public void setKick(float kick) {
        Kick = kick;
    }
}
