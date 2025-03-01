package com.lgkk.spada.model.community.post;

import com.lgkk.spada.model.User;
import com.lgkk.spada.model.community.post.postdetails.CommentDetails;
import com.lgkk.spada.model.community.post.postdetails.VideoDetails;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class PostService implements Serializable {
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
    @SerializedName("images")
    @Expose
    private List<String> images;
    @SerializedName("liked")
    @Expose
    private boolean liked;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("comment")
    @Expose
    private List<CommentDetails> comment = null;
    @SerializedName("likeNumber")
    @Expose
    private long likeNumber;
    @SerializedName("commentNumber")
    @Expose
    private long commentNumber;
    @SerializedName("viewNumber")
    @Expose
    private long viewNumber;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("group")
    @Expose
    private Group group;
    @SerializedName("video")
    @Expose
    private VideoDetails video;

    public PostService(String id, String title, String content, List<String> images, boolean liked, long likeNumber) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.liked = liked;
        this.images = images;
        this.likeNumber = likeNumber;
    }

    public PostService(String id, String title, String content, List<String> images, long viewNumber, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.images = images;
        this.viewNumber = viewNumber;
        this.user = user;
    }
}
