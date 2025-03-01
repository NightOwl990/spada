package com.lgkk.spada.model.community.group;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class GroupByTag {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("groups")
    @Expose
    private List<GroupDetails> groups = null;
    @SerializedName("description")
    @Expose
    private String description;

    private boolean selected = false;
    private boolean firstSelect = false;

}
