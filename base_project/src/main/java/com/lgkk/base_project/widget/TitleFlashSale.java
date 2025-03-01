package com.lgkk.base_project.widget;

import lombok.Data;

@Data
public class TitleFlashSale {
    private int hour;
    private String status;

    public TitleFlashSale(int hour, String status) {
        this.hour = hour;
        this.status = status;
    }
}
