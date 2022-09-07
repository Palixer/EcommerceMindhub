package com.example.EcommerceMindhub.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private int quantity;

    private double price;

    private Date creationDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="shoppingCart_id")
    private ShoppingCart shoppingCart;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="product_id")
    private Product product;
    public PurchaseOrder() {
    }

    public PurchaseOrder(int quantity, double price, ShoppingCart shoppingCart, Product product) {
        this.quantity = quantity;
        this.price = price;
        this.creationDate = new Date();
        this.shoppingCart = shoppingCart;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "PurchaseOrder{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", price=" + price +
                ", creationDate=" + creationDate +
                '}';
    }
}
