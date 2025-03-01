package com.lgkk.spada.model.home;

/**
 * Created by Hoang Nam on 26/06/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

// {
//         "_id": "59bb4a6638569f89980a9ace",
//         "week": 1,
//         "fruitImg": "/assets/fruit/week_1@2x.png.png",
//         "weight": "-",
//         "size": "-",
//         "babyImg": "/assets/hdpi/week_1.png",
//         "tip": [
//         "Hôm nay là ngày đặc biệt của con và ba mẹ: khởi đầu của niềm vui trong 9 tháng 10 ngày nữa ba mẹ ạ!"
//         ]
//         },
public class LifeSciences implements Serializable {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("week")
    @Expose
    private Integer week;
    @SerializedName("babyImg")
    @Expose
    private String babyImg;
    @SerializedName("fruitImg")
    @Expose
    private String fruitImg;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("tip")
    @Expose
    private List<String> tip = null;
    @SerializedName("videoUrl")
    @Expose
    private String videoUrl;

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public String getBabyImg() {
        return babyImg;
    }

    public void setBabyImg(String babyImg) {
        this.babyImg = babyImg;
    }

    public String getFruitImg() {
        return fruitImg;
    }

    public void setFruitImg(String fruitImg) {
        this.fruitImg = fruitImg;
    }

    public String getWeight() {
        if (weight == null) return "-";
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public List<String> getTip() {
        return tip;
    }

    public void setTip(List<String> tip) {
        this.tip = tip;
    }
}
