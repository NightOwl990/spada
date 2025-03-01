package com.lgkk.spada.model.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;

@Data
public class NotificationBean implements Serializable {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("isSeen")
    @Expose
    private boolean isSeen;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("route")
    @Expose
    private String route;
    @SerializedName("data")
    @Expose
    private NotificationData data;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;

    public NotificationBean(String id) {
        this.id = id;
    }
}
