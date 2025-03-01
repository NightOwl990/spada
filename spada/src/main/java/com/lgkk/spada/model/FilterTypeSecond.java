package com.lgkk.spada.model;

import lombok.Data;

@Data
public class FilterTypeSecond {
    private String id;
    private String tagName;
    private String type;
    private boolean isSelected;
    private int lastSelectedPosition = -1;

    public FilterTypeSecond(String id, String tagName, String type) {
        this.id = id;
        this.tagName = tagName;
        this.type = type;
        this.lastSelectedPosition = -1;
    }
}
