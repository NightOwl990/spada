package com.lgkk.spada.event;

public class ChangeProductAmountEvent {
    public int amountProduct;
    public String formula;
    public int adapterPosition = 0;
    public boolean isCheck = true;

    public ChangeProductAmountEvent(int amountProduct, String formula) {
        this.amountProduct = amountProduct;
        this.formula = formula;
    }

    public ChangeProductAmountEvent(int amountProduct, String formula, int adapterPosition, boolean isCheck) {
        this.adapterPosition = adapterPosition;
        this.amountProduct = amountProduct;
        this.formula = formula;
        this.isCheck = isCheck;
    }
}
