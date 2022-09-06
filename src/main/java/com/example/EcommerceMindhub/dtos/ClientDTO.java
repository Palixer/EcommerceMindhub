package com.example.EcommerceMindhub.dtos;

import com.example.EcommerceMindhub.models.Client;
import com.example.EcommerceMindhub.models.ShoppingCart;

public class ClientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;

    private ShoppingCart shoppingCart;
    public ClientDTO(Client client) {
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();
        this.address = client.getAddress();
        this.shoppingCart= client.getShoppingCart();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
