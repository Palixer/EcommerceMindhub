package com.example.EcommerceMindhub.dtos;

import com.example.EcommerceMindhub.models.Bill;
import com.example.EcommerceMindhub.models.ShoppingCart;

public class BillDTO {
    private Long id;
    private double priceTotal;

    private ShoppingCartDTO shoppingCart;

    public BillDTO(Bill bill) {
        this.id= bill.getId();
        this.priceTotal = bill.getPriceTotal();
        this.shoppingCart=new ShoppingCartDTO(bill.getShoppingCart());
    }

    public ShoppingCartDTO getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCartDTO shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(double priceTotal) {
        this.priceTotal = priceTotal;
    }

}
