package com.lgkk.spada.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Diary {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("week")
    @Expose
    private Integer week;
    @SerializedName("day")
    @Expose
    private Integer day;
    @SerializedName("images")
    @Expose
    private List<DiaryContent> diaryContents = null;

    public Diary(List<DiaryContent> diaryContents) {
        this.diaryContents = diaryContents;
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

    public Integer getDay() {
        if (day == null) return 1;
        else return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public List<DiaryContent> getDiaryContents() {
        return diaryContents;
    }

    public void setDiaryContents(List<DiaryContent> diaryContents) {
        this.diaryContents = diaryContents;
    }
}
