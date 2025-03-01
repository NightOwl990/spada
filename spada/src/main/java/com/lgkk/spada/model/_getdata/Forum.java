package com.lgkk.spada.model._getdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Forum {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("icon2")
    @Expose
    private String icon2;
    @SerializedName("icon3")
    @Expose
    private String icon3;
    @SerializedName("total_user")
    @Expose
    private Integer totalUser;
    @SerializedName("total_topic")
    @Expose
    private Integer totalTopic;
    @SerializedName("total_post")
    @Expose
    private Integer totalPost;
    @SerializedName("total_updates")
    @Expose
    private Integer totalUpdates;
    @SerializedName("newest_topic_title")
    @Expose
    private String newestTopicTitle;
    @SerializedName("is_falls")
    @Expose
    private Boolean isFalls;
    @SerializedName("is_aitao")
    @Expose
    private Boolean isAitao;
    @SerializedName("switch")
    @Expose
    private Integer _switch;
    @SerializedName("order_by")
    @Expose
    private String orderBy;
    @SerializedName("limit_image")
    @Expose
    private Integer limitImage;
    @SerializedName("must_tag")
    @Expose
    private Boolean mustTag;
    @SerializedName("join_reply")
    @Expose
    private Boolean joinReply;
    @SerializedName("join_praise")
    @Expose
    private Boolean joinPraise;
    @SerializedName("reply_image")
    @Expose
    private Boolean replyImage;
    @SerializedName("city_name")
    @Expose
    private String cityName;
    @SerializedName("is_open_mood")
    @Expose
    private Boolean isOpenMood;
    @SerializedName("expert_show_type")
    @Expose
    private Integer expertShowType;
    @SerializedName("is_identity_show")
    @Expose
    private Boolean isIdentityShow;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("introduction")
    @Expose
    private String introduction;
    @SerializedName("is_show_image")
    @Expose
    private Boolean isShowImage;
    @SerializedName("home_name")
    @Expose
    private String homeName;
    @SerializedName("more_name")
    @Expose
    private String moreName;
    @SerializedName("is_joined")
    @Expose
    private Boolean isJoined;
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    @SerializedName("is_same_city")
    @Expose
    private Boolean isSameCity;
    @SerializedName("is_recommended")
    @Expose
    private Boolean isRecommended;
    @SerializedName("data_cache")
    @Expose
    private Integer dataCache;
    @SerializedName("has_expert")
    @Expose
    private Boolean hasExpert;
    @SerializedName("has_checkin")
    @Expose
    private Boolean hasCheckin;
    @SerializedName("is_new")
    @Expose
    private Boolean isNew;
    @SerializedName("is_unable_quit")
    @Expose
    private Boolean isUnableQuit;
    @SerializedName("icon4")
    @Expose
    private String icon4;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getTotalUser() {
        return totalUser;
    }

    public void setTotalUser(Integer totalUser) {
        this.totalUser = totalUser;
    }

    public Integer getTotalTopic() {
        return totalTopic;
    }

    public void setTotalTopic(Integer totalTopic) {
        this.totalTopic = totalTopic;
    }

    public Integer getTotalPost() {
        return totalPost;
    }

    public void setTotalPost(Integer totalPost) {
        this.totalPost = totalPost;
    }

    public Integer getTotalUpdates() {
        return totalUpdates;
    }

    public void setTotalUpdates(Integer totalUpdates) {
        this.totalUpdates = totalUpdates;
    }

    public String getNewestTopicTitle() {
        return newestTopicTitle;
    }

    public void setNewestTopicTitle(String newestTopicTitle) {
        this.newestTopicTitle = newestTopicTitle;
    }

    public Boolean getIsFalls() {
        return isFalls;
    }

    public void setIsFalls(Boolean isFalls) {
        this.isFalls = isFalls;
    }

    public Boolean getIsAitao() {
        return isAitao;
    }

    public void setIsAitao(Boolean isAitao) {
        this.isAitao = isAitao;
    }

    public Integer getSwitch() {
        return _switch;
    }

    public void setSwitch(Integer _switch) {
        this._switch = _switch;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getLimitImage() {
        return limitImage;
    }

    public void setLimitImage(Integer limitImage) {
        this.limitImage = limitImage;
    }

    public Boolean getMustTag() {
        return mustTag;
    }

    public void setMustTag(Boolean mustTag) {
        this.mustTag = mustTag;
    }

    public Boolean getJoinReply() {
        return joinReply;
    }

    public void setJoinReply(Boolean joinReply) {
        this.joinReply = joinReply;
    }

    public Boolean getJoinPraise() {
        return joinPraise;
    }

    public void setJoinPraise(Boolean joinPraise) {
        this.joinPraise = joinPraise;
    }

    public Boolean getReplyImage() {
        return replyImage;
    }

    public void setReplyImage(Boolean replyImage) {
        this.replyImage = replyImage;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Boolean getIsOpenMood() {
        return isOpenMood;
    }

    public void setIsOpenMood(Boolean isOpenMood) {
        this.isOpenMood = isOpenMood;
    }

    public Integer getExpertShowType() {
        return expertShowType;
    }

    public void setExpertShowType(Integer expertShowType) {
        this.expertShowType = expertShowType;
    }

    public Boolean getIsIdentityShow() {
        return isIdentityShow;
    }

    public void setIsIdentityShow(Boolean isIdentityShow) {
        this.isIdentityShow = isIdentityShow;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Boolean getIsShowImage() {
        return isShowImage;
    }

    public void setIsShowImage(Boolean isShowImage) {
        this.isShowImage = isShowImage;
    }

    public String getHomeName() {
        return homeName;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    public String getMoreName() {
        return moreName;
    }

    public void setMoreName(String moreName) {
        this.moreName = moreName;
    }

    public Boolean getIsJoined() {
        return isJoined;
    }

    public void setIsJoined(Boolean isJoined) {
        this.isJoined = isJoined;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Boolean getIsSameCity() {
        return isSameCity;
    }

    public void setIsSameCity(Boolean isSameCity) {
        this.isSameCity = isSameCity;
    }

    public Boolean getIsRecommended() {
        return isRecommended;
    }

    public void setIsRecommended(Boolean isRecommended) {
        this.isRecommended = isRecommended;
    }

    public Integer getDataCache() {
        return dataCache;
    }

    public void setDataCache(Integer dataCache) {
        this.dataCache = dataCache;
    }

    public Boolean getHasExpert() {
        return hasExpert;
    }

    public void setHasExpert(Boolean hasExpert) {
        this.hasExpert = hasExpert;
    }

    public Boolean getHasCheckin() {
        return hasCheckin;
    }

    public void setHasCheckin(Boolean hasCheckin) {
        this.hasCheckin = hasCheckin;
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    public Boolean getIsUnableQuit() {
        return isUnableQuit;
    }

    public void setIsUnableQuit(Boolean isUnableQuit) {
        this.isUnableQuit = isUnableQuit;
    }

    public String getIcon4() {
        return icon4;
    }

    public void setIcon4(String icon4) {
        this.icon4 = icon4;
    }

}
