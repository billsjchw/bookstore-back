package com.example.bookstore.misc;

import com.example.bookstore.entity.Consignee;

public class OrderItemsInMyCartDto {
    private Consignee consignee;
    private String paymentMethod;

    public Consignee getConsignee() {
        return consignee;
    }

    public void setConsignee(Consignee consignee) {
        this.consignee = consignee;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
