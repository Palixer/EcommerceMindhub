package com.example.EcommerceMindhub.models;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Date;

@Entity
public class Bill {
    private ShoppingCart shoppingCart;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private double priceTotal;
    private Date createDate;

    //cambiar a como type
    private String wayToPay;

    /*@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="shoppingCart_id")
    private ShoppingCart shoppingCart;*/

    public Bill(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
    public Bill(double priceTotal, Date createDate, String wayToPay, ShoppingCart shoppingCart) {
        this.priceTotal = priceTotal;
        this.createDate = createDate;
        this.wayToPay = wayToPay;
        this.shoppingCart = shoppingCart;
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

    public String getWayToPay() {
        return wayToPay;
    }

    public void setWayToPay(String wayToPay) {
        this.wayToPay = wayToPay;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
