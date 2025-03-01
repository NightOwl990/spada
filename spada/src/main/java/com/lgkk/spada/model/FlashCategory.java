package com.lgkk.spada.model;

import lombok.Data;

@Data
public class FlashCategory {
    public String id;
    public String title;
    public String type;
    public String description;
    public String discountDescription;
    public String image;
    public String icon;
    public long cdTime;
    public int drawable;
    public int imageDrawable;

    public FlashCategory(String id, String title, String type, String description, String discountDescription, String image, String icon, long cdTime) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.description = description;
        this.discountDescription = discountDescription;
        this.image = image;
        this.icon = icon;
        this.cdTime = cdTime;
    }

    public FlashCategory(String id, String title, String type, String description, String discountDescription, int imageDrawable, int drawable, long cdTime) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.description = description;
        this.discountDescription = discountDescription;
        this.drawable = drawable;
        this.imageDrawable = imageDrawable;
        this.cdTime = cdTime;
    }
}
