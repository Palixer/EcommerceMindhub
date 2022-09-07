package com.example.EcommerceMindhub.models;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")

    private Long id;

    public ShoppingCart(Client client) {
        this.client=client;

    }

    public ShoppingCart() {
    }

    @OneToMany(mappedBy="shoppingCart", fetch=FetchType.EAGER)
    Set<Bill> bills = new HashSet<>();
    @OneToMany(mappedBy="shoppingCart", fetch=FetchType.LAZY)
    Set<PurchaseOrder> purchaseOrders = new HashSet<>();
    @OneToOne
    @JoinColumn(name="client_id")
    private Client client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Bill> getBills() {
        return bills;
    }
    public void setBills(Set<Bill> bills) {
        this.bills = bills;
    }
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<PurchaseOrder> getPurchaseOrders() {
        return purchaseOrders;
    }

    public void setPurchaseOrders(Set<PurchaseOrder> purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }

    public void add(ShoppingCart shoppingCart) {
    }
}
