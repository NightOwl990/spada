package com.lgkk.spada.model;

import java.io.Serializable;

/**
 * Created by Hoang Nam on 26/04/2017.
 */

public class PhotoDiary implements Serializable {
    String Image;
    String Title;
    String description;
    String Date;
    String Time;
    String week;

    public PhotoDiary(String image, String title, String description, String date, String time, String week) {
        Image = image;
        Title = title;
        this.description = description;
        Date = date;
        Time = time;
        this.week = week;
    }

    public PhotoDiary(String image) {
        Image = image;

    }

    public PhotoDiary(String image, String title, String time) {
        Image = image;
        Title = title;
        Time = time;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }
}
