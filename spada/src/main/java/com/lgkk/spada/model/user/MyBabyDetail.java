package com.lgkk.spada.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Hoang Nam on 02/08/2017.
 */

public class MyBabyDetail {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("fruitImg")
    @Expose
    private String fruitImg;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("growUp")
    @Expose
    private String growUp;
    @SerializedName("changes")
    @Expose
    private String changes;
    @SerializedName("advice")
    @Expose
    private String advice;
    @SerializedName("contentImg")
    @Expose
    private String contentImg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFruitImg() {
        return fruitImg;
    }

    public void setFruitImg(String fruitImg) {
        this.fruitImg = fruitImg;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getGrowUp() {
        return growUp;
    }

    public void setGrowUp(String growUp) {
        this.growUp = growUp;
    }

    public String getChanges() {
        return changes;
    }

    public void setChanges(String changes) {
        this.changes = changes;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getContentImg() {
        return contentImg;
    }

    public void setContentImg(String contentImg) {
        this.contentImg = contentImg;
    }
}
