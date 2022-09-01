package com.example.EcommerceMindhub.dtos;

import com.example.EcommerceMindhub.models.Bill;

public class BillDTO {
    private Long id;
    private double priceTotal;

    public BillDTO(Bill bill) {
        this.id= bill.getId();
        this.priceTotal = bill.getPriceTotal();
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
