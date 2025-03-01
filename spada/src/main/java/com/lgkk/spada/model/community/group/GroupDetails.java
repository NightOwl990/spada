package com.lgkk.spada.model.community.group;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

// {
//         "_id": "5be399ca7317f25a264d5e8e",
//         "createdAt": "2018-11-08T02:04:58.826Z",
//         "name": "Hội nhóm chung Hội nhóm chung Hội nhóm chungHội nhóm chungHội nhóm chungHội nhóm chung",
//         "memberNumber": 4,
//         "tags": [
//         "5c00ab2ad570521ffc930af3",
//         "5c00ab2ad570521ffc930af4",
//         "5c00ab2ad570521ffc930af7"
//         ],
//         "description": "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum",
//         "image": "/assets/image/1541642698783-45049725_491113424729413_4950794027411701760_n.jpg",
//         "postCount": 20,
//         "isJoined": true
//         }
public class GroupDetails implements Serializable {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("memberNumber")
    @Expose
    private Integer memberNumber;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("postCount")
    @Expose
    private long postCount;
    @SerializedName("isJoined")
    @Expose
    private boolean isJoined;

    public long getPostCount() {
        return postCount;
    }

    public void setPostCount(long postCount) {
        this.postCount = postCount;
    }

    public boolean isJoined() {
        return isJoined;
    }

    public void setJoined(boolean joined) {
        isJoined = joined;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(Integer memberNumber) {
        this.memberNumber = memberNumber;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
