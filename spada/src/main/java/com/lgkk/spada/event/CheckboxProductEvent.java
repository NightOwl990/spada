package com.lgkk.spada.event;

public class CheckboxProductEvent {
    public int adapterPosition;
    public boolean isChecked;

    public CheckboxProductEvent(int adapterPosition, boolean isChecked) {
        this.adapterPosition = adapterPosition;
        this.isChecked = isChecked;
    }
}
