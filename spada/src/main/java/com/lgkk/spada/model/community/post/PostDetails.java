package com.lgkk.spada.model.community.post;

import com.lgkk.spada.model.User;
import com.lgkk.spada.model.community.post.postdetails.CommentDetails;
import com.lgkk.spada.model.community.post.postdetails.VideoDetails;
import com.lgkk.spada.model.shop.Product;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class PostDetails implements Serializable {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("video")
    @Expose
    private VideoDetails video;
    @SerializedName("background")
    @Expose
    private String background;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("liked")
    @Expose
    private boolean liked;
    @SerializedName("likeNumber")
    @Expose
    private Integer likeNumber;
    @SerializedName("time")
    @Expose
    private Integer time;
    @SerializedName("images")
    @Expose
    private List<String> images = null;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("comment")
    @Expose
    private List<CommentDetails> comment = null;
    @SerializedName("commentNumber")
    @Expose
    private Integer commentNumber;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("group")
    @Expose
    private Group group;
    @SerializedName("anonymous")
    @Expose
    private boolean anonymous;
    @SerializedName("defaultComment")
    @Expose
    private DefaultComment defaultComment;
    @SerializedName("view")
    @Expose
    private long view;
    @SerializedName("sponsorType")
    @Expose
    private String sponsorType;
    @SerializedName("sponsorProductList")
    @Expose
    private List<Product> sponsorProductList = null;
}
