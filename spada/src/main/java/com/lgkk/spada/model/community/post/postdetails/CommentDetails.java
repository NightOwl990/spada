package com.lgkk.spada.model.community.post.postdetails;

import com.lgkk.spada.model.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * Created by MIDASMEDIA on 22-Aug-18.
 */

@Data
public class CommentDetails implements Serializable {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("liked")
    @Expose
    private boolean liked;
    @SerializedName("likeNumber")
    @Expose
    private Integer likeNumber;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("replyNumber")
    @Expose
    private int replyNumber;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("reply")
    @Expose
    private List<ReplyDetails> reply = null;
    @SerializedName("fileUrl")
    @Expose
    private String fileUrl;
    @SerializedName("deleted")
    @Expose
    private boolean deleted;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("__v")
    @Expose
    private Integer v;

    private int currentPosition;
    private int startLoadReplyPosition;
    private boolean firstLoadReply = true;
}
