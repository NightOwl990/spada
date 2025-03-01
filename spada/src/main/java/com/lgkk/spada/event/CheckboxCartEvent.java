package com.lgkk.spada.event;

public class CheckboxCartEvent {
    public int adapterPosition;
    public boolean isChecked;

    public CheckboxCartEvent(int adapterPosition, boolean isChecked) {
        this.adapterPosition = adapterPosition;
        this.isChecked = isChecked;
    }
}
