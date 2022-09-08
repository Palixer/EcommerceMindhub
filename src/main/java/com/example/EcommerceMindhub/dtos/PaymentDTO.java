package com.example.EcommerceMindhub.dtos;

import com.example.EcommerceMindhub.models.Payment;
import com.example.EcommerceMindhub.models.WayToPayType;

import java.util.Date;

public class PaymentDTO {

    private long id;
    private WayToPayType wayToPayType;
    private double priceTotal;
    private Date createDate;

    public PaymentDTO() {
    }

    public PaymentDTO(Payment payment) {
        this.id = payment.getId();
        this.wayToPayType = payment.getWayToPayType();
        this.priceTotal = payment.getPriceTotal();
        this.createDate = payment.getCreateDate();
    }

    public WayToPayType getWayToPayType() {
        return wayToPayType;
    }

    public void setWayToPayType(WayToPayType wayToPayType) {
        this.wayToPayType = wayToPayType;
    }

    public double getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(double priceTotal) {
        this.priceTotal = priceTotal;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
