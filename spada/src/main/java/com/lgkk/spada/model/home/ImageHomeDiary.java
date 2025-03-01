package com.lgkk.spada.model.home;

import java.io.Serializable;

/**
 * Created by Hoang Nam on 27/06/2017.
 */

public class ImageHomeDiary implements Serializable {
    String title;
    String date;
    String image;

    public ImageHomeDiary(String title, String date, String image) {
        this.title = title;
        this.date = date;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
