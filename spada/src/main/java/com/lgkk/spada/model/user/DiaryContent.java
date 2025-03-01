package com.lgkk.spada.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DiaryContent {

    @SerializedName("_id")
    @Expose
    private String idContent;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("tag")
    @Expose
    private String tag;

//    Extends ----------

    private String idDiary;
    private Integer week;
    private Integer day;


    public DiaryContent(String idContent) {
        this.idContent = idContent;
    }

    public String getTag() {
        if (tag == null) return "Đi chơi";
        else return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }


    public String getIdContent() {
        return idContent;
    }

    public void setIdContent(String idContent) {
        this.idContent = idContent;
    }

    public String getIdDiary() {
        return idDiary;
    }

    public void setIdDiary(String idDiary) {
        this.idDiary = idDiary;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
