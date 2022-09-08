package com.example.EcommerceMindhub.models;

import com.example.EcommerceMindhub.dtos.PurchaseOrderDTO;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private double priceTotal;
    private Date createDate;

    private WayToPayType wayToPay;

    /*@ElementCollection
    private Set<PurchaseOrder> purchaseOrders;*/

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="shoppingCart_id")
    private ShoppingCart shoppingCart;


    private double allPrices(ShoppingCart shoppingCart){
        List<Double> prices=shoppingCart.getPurchaseOrders().stream().map(purchaseOrder -> purchaseOrder.getPrice()).collect(Collectors.toList());
        double total=0.0;
        for (double price : prices) {
            total=total+price;
        }
        return total;
    }
    public Bill(ShoppingCart shoppingCart, WayToPayType wayToPayType) {
        this.priceTotal = this.allPrices(shoppingCart);
        this.createDate = new Date();
        this.shoppingCart = shoppingCart;
        this.wayToPay = wayToPayType;

       // this.purchaseOrders=shoppingCart.getPurchaseOrders();
    }

   /* public Set<PurchaseOrder> getPurchaseOrders() {
        return purchaseOrders;
    }

    public void setPurchaseOrders(Set<PurchaseOrder> purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }
*/

    public Bill() {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WayToPayType getWayToPay() {
        return wayToPay;
    }

    public void setWayToPay(WayToPayType wayToPay) {
        this.wayToPay = wayToPay;
    }
    /* public List<PurchaseOrderDTO> getPurchaseOrders() {
        return purchaseOrders;
    }*/
}
