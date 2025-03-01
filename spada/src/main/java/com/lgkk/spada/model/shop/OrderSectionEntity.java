package com.lgkk.spada.model.shop;

import com.chad.library.adapter.base.entity.SectionEntity;

public class OrderSectionEntity<T> extends SectionEntity<T> {
    public boolean isHeader;
    public T t;
    public String header;
    public String orderNumber;
    public String create_At;
    public String status;

    public OrderSectionEntity(boolean isHeader, String header) {
        super(isHeader, header);
        this.isHeader = isHeader;
        this.header = header;
        this.t = null;
    }

    public OrderSectionEntity(boolean isHeader, String orderNumber, String create_At, String status) {
        super(isHeader, orderNumber);
        this.isHeader = isHeader;
        this.orderNumber = orderNumber;
        this.create_At = create_At;
        this.status = status;
        this.t = null;
    }

    public OrderSectionEntity(T t) {
        super(t);
        this.isHeader = false;
        this.header = null;
        this.t = t;
    }
}
