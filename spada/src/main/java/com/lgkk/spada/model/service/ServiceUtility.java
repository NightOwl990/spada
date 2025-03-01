package com.lgkk.spada.model.service;

import androidx.annotation.DrawableRes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceUtility {
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

    public ServiceUtility(String name, @DrawableRes int drawableImage) {
        this.name = name;
        this.drawableImage = drawableImage;
    }

    public ServiceUtility(String id, String name, @DrawableRes int drawableImage) {
        this.id = id;
        this.name = name;
        this.drawableImage = drawableImage;
    }

    public ServiceUtility(String id, String name, String description, @DrawableRes int drawableImage) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.drawableImage = drawableImage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getServices() {
        return services;
    }

    public void setServices(Integer services) {
        this.services = services;
    }

    public int getDrawableImage() {
        return drawableImage;
    }

    public void setDrawableImage(int drawableImage) {
        this.drawableImage = drawableImage;
    }
}
