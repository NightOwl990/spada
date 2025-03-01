package com.lgkk.spada.model;

import java.util.List;

import lombok.Data;

@Data
public class FilterTypeDetail {

    private String id;
    private String tagName;
    private String type;
    private boolean isSelected;
    private List<FilterTypeSecond> secondTypeList;
    private int lastSelectedPosition = -1;

    public FilterTypeDetail(String id, String tagName, String type) {
        this.id = id;
        this.tagName = tagName;
        this.type = type;
        this.isSelected = false;
        this.lastSelectedPosition = -1;
    }

    public FilterTypeDetail(String id, String tagName, String type, List<FilterTypeSecond> secondTypeList) {
        this.id = id;
        this.tagName = tagName;
        this.type = type;
        this.secondTypeList = secondTypeList;
        this.lastSelectedPosition = -1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public List<FilterTypeSecond> getSecondTypeList() {
        return secondTypeList;
    }

    public void setSecondTypeList(List<FilterTypeSecond> secondTypeList) {
        this.secondTypeList = secondTypeList;
    }

    public int getLastSelectedPosition() {
        return lastSelectedPosition;
    }

    public void setLastSelectedPosition(int lastSelectedPosition) {
        this.lastSelectedPosition = lastSelectedPosition;
    }
}
