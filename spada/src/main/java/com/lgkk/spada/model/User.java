package com.lgkk.spada.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;

/**
 * Created by Hoang Nam on 11/04/2017.
 */

@Data
public class User implements Serializable {
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("weekNumber")
    @Expose
    private Integer weekNumber;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("score")
    @Expose
    private Integer score;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("isUpdate")
    @Expose
    private boolean isUpdate;
    @SerializedName("questionNumber")
    @Expose
    private Integer questionNumber;
    @SerializedName("commentNumber")
    @Expose
    private Integer commentNumber;
    @SerializedName("likeNumber")
    @Expose
    private Integer likeNumber;
    @SerializedName("likedNumber")
    @Expose
    private Integer likedNumber;
    @SerializedName("statusTitle")
    @Expose
    private String statusTitle;
    @SerializedName("followNumber")
    @Expose
    private Integer followNumber;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("dateBirth")
    @Expose
    private String dateBirth;
    @SerializedName("dayNumber")
    @Expose
    private Integer dayNumber;
    @SerializedName("level")
    @Expose
    private Integer level;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("rank")
    @Expose
    private Integer rank;

    public User(String avatar, String name) {
        this.avatar = avatar;
        this.name = name;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getFollowNumber() {
        return followNumber;
    }

    public void setFollowNumber(Integer followNumber) {
        this.followNumber = followNumber;
    }

    public boolean getUpdate() {
        return isUpdate;
    }

    public void setUpdate(boolean update) {
        isUpdate = update;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(Integer weekNumber) {
        this.weekNumber = weekNumber;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public Integer getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(Integer questionNumber) {
        this.questionNumber = questionNumber;
    }

    public Integer getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(Integer commentNumber) {
        this.commentNumber = commentNumber;
    }

    public Integer getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(Integer likeNumber) {
        this.likeNumber = likeNumber;
    }

    public Integer getLikedNumber() {
        return likedNumber;
    }

    public void setLikedNumber(Integer likedNumber) {
        this.likedNumber = likedNumber;
    }

    public String getStatusTitle() {
        return statusTitle;
    }

    public void setStatusTitle(String statusTitle) {
        this.statusTitle = statusTitle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }

    public Integer getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(Integer dayNumber) {
        this.dayNumber = dayNumber;
    }

    public Integer getLevel() {
        if (level == null) return 0;
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
