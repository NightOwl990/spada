package com.lgkk.spada.model;

import android.graphics.drawable.Drawable;

/**
 * Created by Hoang Nam on 13/06/2017.
 */

public class UserModel {
    String title;
    Drawable image;

    public UserModel(String title, Drawable image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }
}
