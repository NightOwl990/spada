package com.lgkk.spada.model;

import lombok.Data;

@Data
public class RecommendCity {
    private String id;
    private String image;
    private String description;
    private String city;
    private int spaCount;
    private int doctorCount;

    public RecommendCity(String id, String image, String description, String city, int spaCount, int doctorCount) {
        this.id = id;
        this.image = image;
        this.description = description;
        this.city = city;
        this.spaCount = spaCount;
        this.doctorCount = doctorCount;
    }
}
