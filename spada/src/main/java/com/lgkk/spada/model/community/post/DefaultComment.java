package com.lgkk.spada.model.community.post;

import com.lgkk.spada.model.community.post.postdetails.UserPostDetails;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by MIDASMEDIA on 22-Aug-18.
 */

public class DefaultComment implements Serializable {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("user")
    @Expose
    private UserPostDetails user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public UserPostDetails getUser() {
        return user;
    }

    public void setUser(UserPostDetails user) {
        this.user = user;
    }

}
