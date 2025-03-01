package com.lgkk.spada.model;

import androidx.annotation.DrawableRes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class UserSetting {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("services")
    @Expose
    private Integer services;
    @SerializedName("amountNotification")
    @Expose
    private Integer amountNotification;
    private @DrawableRes
    int drawableImage;

    public UserSetting(String id, String name, @DrawableRes int drawableImage) {
        this.id = id;
        this.name = name;
        this.drawableImage = drawableImage;
    }

    public UserSetting(String name, @DrawableRes int drawableImage) {
        this.name = name;
        this.drawableImage = drawableImage;
    }

    public UserSetting(String name, @DrawableRes int drawableImage, int amountNotification) {
        this.name = name;
        this.drawableImage = drawableImage;
        this.amountNotification = amountNotification;
    }

    public Integer getAmountNotification() {
        if (amountNotification == null) return 0;
        else {
            return amountNotification;
        }
    }
}
