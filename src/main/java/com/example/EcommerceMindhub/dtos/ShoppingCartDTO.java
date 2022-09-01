package com.example.EcommerceMindhub.dtos;

import com.example.EcommerceMindhub.models.ShoppingCart;

public class ShoppingCartDTO {
    private Long id;

    public ShoppingCartDTO(ShoppingCart shoppingCart) {
        this.id = shoppingCart.getId();
    }
}
