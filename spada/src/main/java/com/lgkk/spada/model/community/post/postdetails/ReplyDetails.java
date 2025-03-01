package com.lgkk.spada.model.community.post.postdetails;

/**
 * Created by MIDASMEDIA on 31-Aug-18.
 */

import com.lgkk.spada.model.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;

@Data
public class ReplyDetails implements Serializable {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("liked")
    @Expose
    private boolean liked;
    @SerializedName("likeNumber")
    @Expose
    private Integer likeNumber;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("fileUrl")
    @Expose
    private String fileUrl;
    @SerializedName("deleted")
    @Expose
    private boolean deleted;

    @SerializedName("comment")
    @Expose
    private String comment;

    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("__v")
    @Expose
    private Integer v;

    private int commentPosition;
    private int currentPosition;

}
