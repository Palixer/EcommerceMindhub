package com.example.EcommerceMindhub.models;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Date;

@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private double priceTotal;
    private Date createDate;

    private WayToPayType wayToPay;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="shoppingCart_id")
    private ShoppingCart shoppingCart;
    public Bill(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
    public Bill(double priceTotal, ShoppingCart shoppingCart) {
        this.priceTotal = priceTotal;
        this.createDate = new Date();
        this.shoppingCart = shoppingCart;
    }

    public double getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(double priceTotal) {
        this.priceTotal = priceTotal;
    }


    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
