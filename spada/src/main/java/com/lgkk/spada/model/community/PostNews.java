package com.lgkk.spada.model.community;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Hoang Nam on 28/04/2017.
 */

public class PostNews {
    @SerializedName("category")
    @Expose
    private CategoriesNews category;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("week")
    @Expose
    private Integer week;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("isBookmark")
    @Expose
    private boolean isBookmark;
    @SerializedName("interestNumber")
    @Expose
    private long interestNumber;

    public PostNews(String title, String url, String image, String createdAt) {
        this.title = title;
        this.url = url;
        this.image = image;
        this.createdAt = createdAt;
    }

    public PostNews() {

    }

    public boolean isBookmark() {
        return isBookmark;
    }

    public long getInterestNumber() {
        return interestNumber;
    }

    public void setInterestNumber(long interestNumber) {
        this.interestNumber = interestNumber;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean getBookmark() {
        return isBookmark;
    }

    public void setBookmark(boolean bookmark) {
        isBookmark = bookmark;
    }

    public CategoriesNews getCategory() {
        return category;
    }

    public void setCategory(CategoriesNews category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public boolean getIsBookmark() {
        return isBookmark;
    }

    public void setIsBookmark(boolean isBookmark) {
        this.isBookmark = isBookmark;
    }
}


