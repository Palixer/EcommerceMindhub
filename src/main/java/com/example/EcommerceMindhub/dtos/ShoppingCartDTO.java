package com.example.EcommerceMindhub.dtos;

import com.example.EcommerceMindhub.models.ShoppingCart;

public class ShoppingCartDTO {
    private Long id;

    public ShoppingCartDTO(ShoppingCart shoppingCart) {
        this.id = shoppingCart.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PurchaseOrderDTO getPurchaseOrders() {
        return new PurchaseOrderDTO();
    }
}
