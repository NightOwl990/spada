package com.lgkk.spada.model._getdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryData {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("forum_number")
    @Expose
    private Integer forumNumber;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("icon2")
    @Expose
    private String icon2;
    @SerializedName("icon3")
    @Expose
    private String icon3;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("has_new")
    @Expose
    private Boolean hasNew;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getForumNumber() {
        return forumNumber;
    }

    public void setForumNumber(Integer forumNumber) {
        this.forumNumber = forumNumber;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon2() {
        return icon2;
    }

    public void setIcon2(String icon2) {
        this.icon2 = icon2;
    }

    public String getIcon3() {
        return icon3;
    }

    public void setIcon3(String icon3) {
        this.icon3 = icon3;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getHasNew() {
        return hasNew;
    }

    public void setHasNew(Boolean hasNew) {
        this.hasNew = hasNew;
    }
}
