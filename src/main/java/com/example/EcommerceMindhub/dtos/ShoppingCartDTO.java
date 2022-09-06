package com.example.EcommerceMindhub.dtos;

import com.example.EcommerceMindhub.models.ShoppingCart;

import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCartDTO {
    private Long id;
    private List<PurchaseOrderDTO> purchaseOrder;

    public ShoppingCartDTO() {
    }

    public ShoppingCartDTO(ShoppingCart shoppingCart) {

        this.id = shoppingCart.getId();
        this.purchaseOrder= shoppingCart.getPurchaseOrders().stream().map(purchaseOrder -> new PurchaseOrderDTO(purchaseOrder)).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<PurchaseOrderDTO> getPurchaseOrder() {
        return purchaseOrder;
    }
}
