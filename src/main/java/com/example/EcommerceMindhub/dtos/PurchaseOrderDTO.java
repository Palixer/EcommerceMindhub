package com.example.EcommerceMindhub.dtos;

import com.example.EcommerceMindhub.models.Product;
import com.example.EcommerceMindhub.models.PurchaseOrder;
import com.example.EcommerceMindhub.models.ShoppingCart;

public class PurchaseOrderDTO {
    private long id;
    private double price;
    private int quantity;

    private ShoppingCart shoppingCart;

    public PurchaseOrderDTO(PurchaseOrder purchaseOrder) {
        this.id = purchaseOrder.getId();
        this.price = purchaseOrder.getPrice();
        this.quantity = purchaseOrder.getQuantity();
        this.shoppingCart= purchaseOrder.getShoppingCart();
    }

    public PurchaseOrderDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
