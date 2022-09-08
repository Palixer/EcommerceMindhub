package com.example.EcommerceMindhub.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private WayToPayType wayToPayType;

    public Payment() {
    }

    public Payment(WayToPayType wayToPayType) {
        this.wayToPayType = wayToPayType;
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


}
