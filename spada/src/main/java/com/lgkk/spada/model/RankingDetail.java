package com.lgkk.spada.model;

import lombok.Data;

@Data
public class RankingDetail {
    private String id;
    private String title;
    private String image;
    private String top1;
    private String top2;
    private String top3;
    private int iconType;
    private String type;

    public RankingDetail(String id, String title, String image, String top1, String top2, String top3, int iconType, String type) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.top1 = top1;
        this.top2 = top2;
        this.top3 = top3;
        this.iconType = iconType;
        this.type = type;
    }
}
