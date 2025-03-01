package com.lgkk.spada.model.home;

/**
 * Created by Hoang Nam on 15/05/2017.
 */

public class Month {
    int id;
    String month;

    public Month(int id, String month) {
        this.id = id;
        this.month = month;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
