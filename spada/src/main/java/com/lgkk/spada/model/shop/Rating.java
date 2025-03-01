package com.lgkk.spada.model.shop;

import com.lgkk.spada.model.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Rating {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("score")
    @Expose
    private Double score;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("isAnonymous")
    @Expose
    private boolean isAnonymous;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("isBuy")
    @Expose
    private boolean isBuy;
    @SerializedName("likeNumber")
    @Expose
    private Integer likeNumber;
    @SerializedName("liked")
    @Expose
    private boolean liked;
}
