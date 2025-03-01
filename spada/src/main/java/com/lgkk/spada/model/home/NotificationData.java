package com.lgkk.spada.model.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;

@Data
public class NotificationData implements Serializable {
    @SerializedName("userName")
    @Expose
    private String userName= "";
    @SerializedName("groupName")
    @Expose
    private String groupName = "";
    @SerializedName("typeNotification")
    @Expose
    private String typeNotification = "";
    @SerializedName("notificationId")
    @Expose
    private String notificationId = "";
    @SerializedName("action")
    @Expose
    private String action = "";
    @SerializedName("questionId")
    @Expose
    private String questionId = "";
    @SerializedName("commentId")
    @Expose
    private String commentId = "";

    @SerializedName("url")
    @Expose
    private String url = "";
    @SerializedName("targetId")
    @Expose
    private String targetId = "";
    @SerializedName("status")
    @Expose
    private String status = "";
    @SerializedName("productOrderId")
    @Expose
    private String productOrderId = "";

    @SerializedName("subCategoryId")
    @Expose
    private String subCategoryId = "";
    @SerializedName("image")
    @Expose
    private String image = "";
}

