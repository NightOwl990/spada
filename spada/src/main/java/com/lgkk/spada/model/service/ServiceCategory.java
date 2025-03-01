package com.lgkk.spada.model.service;

import androidx.annotation.DrawableRes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class ServiceCategory {
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
    private @DrawableRes
    int drawableImage;

    private boolean isNew = false;

    public ServiceCategory(String name, @DrawableRes int drawableImage) {
        this.name = name;
        this.drawableImage = drawableImage;
    }

    public ServiceCategory(String name, @DrawableRes int drawableImage, boolean isNew) {
        this.name = name;
        this.drawableImage = drawableImage;
        this.isNew = isNew;
    }

    public ServiceCategory(String id, String name, @DrawableRes int drawableImage) {
        this.id = id;
        this.name = name;
        this.drawableImage = drawableImage;
    }

    public ServiceCategory(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
