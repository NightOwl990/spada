package com.lgkk.spada.model.shop;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Product implements Serializable {
    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("shortDescription")
    @Expose
    private String shortDescription;

    @SerializedName("discountPercent")
    @Expose
    private long discountPercent;

    @SerializedName("quantity")
    @Expose
    private long quantity;

    @SerializedName("isActive")
    @Expose
    private boolean isActive;

    @SerializedName("subCategories")
    @Expose
    private List<SubCategory> subCategories;

    @SerializedName("avatar")
    @Expose
    private String avatar;

    @SerializedName("thumbnails")
    @Expose
    private List<String> thumbnails;

    @SerializedName("isFreeShip")
    @Expose
    private boolean isFreeShip;

    @SerializedName("orderNumber")
    @Expose
    private Integer orderNumber;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("price")
    @Expose
    private long price;

    @SerializedName("shop")
    @Expose
    private Shop shop;

    @SerializedName("rateLength")
    @Expose
    private Integer rateLength;

    @SerializedName("rateScore")
    @Expose
    private Double rateScore;

    @SerializedName("fromWeek")
    @Expose
    private Integer fromWeek;

    @SerializedName("toWeek")
    @Expose
    private Integer toWeek;

    @SerializedName("likeNumber")
    @Expose
    private Integer likeNumber;

    @SerializedName("liked")
    @Expose
    private boolean liked;

    @SerializedName("isRating")
    @Expose
    private boolean isRating;

    private double discountPrice;
    private int orderAmount;
    private long orderTotalPrice;
    private int orderShopTotalProduct;
    private String cartId;
    private String orderId;
    private boolean isCheckedProduct = true;
    private int ratingScoreReport;
    private String ratingContent;

    public Product(String id, String avatar, String name, long price, double discountPrice, double rateScore) {
        this.id = id;
        this.avatar = avatar;
        this.name = name;
        this.price = price;
        this.discountPrice = discountPrice;
        this.rateScore = rateScore;
    }

    public Product() {
    }

    public boolean isFreeShip() {
        return isFreeShip;
    }

    public void setFreeShip(boolean freeShip) {
        this.isFreeShip = freeShip;
    }

    public Integer getLikeNumber() {
        return likeNumber == null ? 0 : likeNumber;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Include other getters and setters similarly


    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public long getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(long discountPercent) {
        this.discountPercent = discountPercent;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<String> getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(List<String> thumbnails) {
        this.thumbnails = thumbnails;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Integer getRateLength() {
        return rateLength;
    }

    public void setRateLength(Integer rateLength) {
        this.rateLength = rateLength;
    }

    public Double getRateScore() {
        return rateScore;
    }

    public void setRateScore(Double rateScore) {
        this.rateScore = rateScore;
    }

    public Integer getFromWeek() {
        return fromWeek;
    }

    public void setFromWeek(Integer fromWeek) {
        this.fromWeek = fromWeek;
    }

    public Integer getToWeek() {
        return toWeek;
    }

    public void setToWeek(Integer toWeek) {
        this.toWeek = toWeek;
    }

    public void setLikeNumber(Integer likeNumber) {
        this.likeNumber = likeNumber;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public boolean isRating() {
        return isRating;
    }

    public void setRating(boolean rating) {
        isRating = rating;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public long getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(long orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public int getOrderShopTotalProduct() {
        return orderShopTotalProduct;
    }

    public void setOrderShopTotalProduct(int orderShopTotalProduct) {
        this.orderShopTotalProduct = orderShopTotalProduct;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public boolean isCheckedProduct() {
        return isCheckedProduct;
    }

    public void setCheckedProduct(boolean checkedProduct) {
        isCheckedProduct = checkedProduct;
    }

    public int getRatingScoreReport() {
        return ratingScoreReport;
    }

    public void setRatingScoreReport(int ratingScoreReport) {
        this.ratingScoreReport = ratingScoreReport;
    }

    public String getRatingContent() {
        return ratingContent;
    }

    public void setRatingContent(String ratingContent) {
        this.ratingContent = ratingContent;
    }
}
