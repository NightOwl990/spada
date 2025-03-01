package com.lgkk.spada.model;

import com.lgkk.spada.model.community.post.postdetails.VideoDetails;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddPostResult {
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("group")
    @Expose
    private String group;
    @SerializedName("background")
    @Expose
    private Integer background;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("_id")
    @Expose
    private String _id;
    @SerializedName("deleted")
    @Expose
    private boolean deleted;
    @SerializedName("activated")
    @Expose
    private boolean activated;
    @SerializedName("anonymous")
    @Expose
    private boolean anonymous;
    @SerializedName("view")
    @Expose
    private Integer view;
    @SerializedName("time")
    @Expose
    private Integer time;
    @SerializedName("comment")
    @Expose
    private List<Object> comment = null;
    @SerializedName("like")
    @Expose
    private List<Object> like = null;
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("video")
    @Expose
    private VideoDetails video;
    @SerializedName("images")
    @Expose
    private List<String> images = null;
    @SerializedName("type")
    @Expose
    private String type;

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Integer getBackground() {
        return background;
    }

    public void setBackground(Integer background) {
        this.background = background;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean getActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public boolean getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }

    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public List<Object> getComment() {
        return comment;
    }

    public void setComment(List<Object> comment) {
        this.comment = comment;
    }

    public List<Object> getLike() {
        return like;
    }

    public void setLike(List<Object> like) {
        this.like = like;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public VideoDetails getVideo() {
        return video;
    }

    public void setVideo(VideoDetails video) {
        this.video = video;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
