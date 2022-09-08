package com.example.EcommerceMindhub.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private WayToPayType wayToPayType;
    private double priceTotal;
    private Date createDate;

    public Payment() {
    }

    public Payment(WayToPayType wayToPayType, Bill bill) {
        this.wayToPayType = wayToPayType;
        this.priceTotal = bill.getPriceTotal();
        this.createDate = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
