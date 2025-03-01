package com.lgkk.spada.model;

import lombok.Data;

@Data
public class TrackingTime {
    private long startTime;
    private long endTime;

    public TrackingTime(long startTime, long endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
