package com.lgkk.spada.model.user;

/**
 * Created by Hoang Nam on 05/07/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MyPhotoDiary implements Serializable {
    @SerializedName("images")
    @Expose
    private List<Image> images = null;
    @SerializedName("week")
    @Expose
    private Integer week;

    public final List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }
}
