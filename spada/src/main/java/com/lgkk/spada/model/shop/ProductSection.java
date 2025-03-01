package com.lgkk.spada.model.shop;


public class ProductSection extends OrderSectionEntity<Product> {
    public String status;
    public String orderId;
    private boolean isEnd = false;
    private boolean isCheckedShop = true;
    private int positionShop;

    public ProductSection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public ProductSection(boolean isHeader, String orderNumber, String create_At, String status) {
        super(isHeader, orderNumber, create_At, status);
        this.status = status;
    }

    public ProductSection(Product product, int positionShop) {
        super(product);
        this.positionShop = positionShop;
    }

    public ProductSection(Product product, int positionShop, String status, String orderId) {
        super(product);
        this.positionShop = positionShop;
        this.status = status;
        this.orderId = orderId;
    }

    public ProductSection(Product product, boolean isEnd) {
        super(product);
        this.isEnd = isEnd;
    }

    public boolean isCheckedShop() {
        return isCheckedShop;
    }

    public void setCheckedShop(boolean checkedShop) {
        isCheckedShop = checkedShop;
    }

    public int getPositionShop() {
        return positionShop;
    }

    public void setPositionShop(int positionShop) {
        this.positionShop = positionShop;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }
}
