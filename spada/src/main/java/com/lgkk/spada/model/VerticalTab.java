package com.lgkk.spada.model;

public class VerticalTab {

    String name;
    int positionRecycler;

    public VerticalTab(String name, int positionRecycler) {
        this.name = name;
        this.positionRecycler = positionRecycler;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPositionRecycler() {
        return positionRecycler;
    }

    public void setPositionRecycler(int positionRecycler) {
        this.positionRecycler = positionRecycler;
    }
}
