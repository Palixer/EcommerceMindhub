package com.example.EcommerceMindhub.controllers;

import com.example.EcommerceMindhub.dtos.BillDTO;
import com.example.EcommerceMindhub.models.*;
import com.example.EcommerceMindhub.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class BillController {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PurchaseOrRepository purchaseOrRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/bills")
    public List<BillDTO> findAll() {
        return billRepository.findAll().stream().map(bill -> new BillDTO(bill)).collect(Collectors.toList());
    }

    @GetMapping("/bills/{id}")
    public BillDTO getBillById(@PathVariable Long id) {

        return billRepository.findById(id).map(BillDTO::new).orElse(null);
    }

    @PostMapping("/shoppingCart/current/bills/payment/cash")
    public ResponseEntity<Object> paymentCash(Authentication authentication) {

        Client clientInSession = this.clientRepository.findByEmail(authentication.getName());
        ShoppingCart shoppingCart = clientInSession.getShoppingCart();

        if (shoppingCart.getPurchaseOrders().isEmpty()){
            return new ResponseEntity<>("No tienes ordenes de compra para facturar", HttpStatus.FORBIDDEN);
        }

        Bill newBill = new Bill(shoppingCart, WayToPayType.CASH);
        billRepository.save(newBill);

        Set<PurchaseOrder> purchaseOrders= shoppingCart.getPurchaseOrders();



        purchaseOrders.forEach(purchaseOrder -> {
            Product product = purchaseOrder.getProduct();
            product.setStock(purchaseOrder.getQuantity()-product.getStock());
            productRepository.save(product);
        });


        purchaseOrRepository.deleteAll(clientInSession.getShoppingCart().getPurchaseOrders());
        clientRepository.save(clientInSession);


        ShoppingCart newShoppingCart = new ShoppingCart(clientInSession);
        shoppingCartRepository.save(newShoppingCart);

        return new ResponseEntity<>("Factura Creada", HttpStatus.CREATED);


    }



    @PostMapping("/shoppingCart/current/bills/payment/card")
    public  ResponseEntity<Object> paymentCard (Authentication authentication,
                                                @RequestParam String cardNumber,
                                                @RequestParam String cvv){

        if(cardNumber.isEmpty() || cvv.isEmpty() ){
            return new ResponseEntity<>("Los datos estan vacios.", HttpStatus.FORBIDDEN);
        }

        if (cardNumber.length() != 16){
            return new ResponseEntity<>("Faltan números o alguna gilada.", HttpStatus.FORBIDDEN);
        }

        if (cvv.length() != 3){
            return new ResponseEntity<>("Revisá los números, rey.", HttpStatus.FORBIDDEN);
        }

        Client clientInSession = this.clientRepository.findByEmail(authentication.getName());
        ShoppingCart shoppingCart = clientInSession.getShoppingCart();

        if (shoppingCart.getPurchaseOrders().isEmpty()){
            return new ResponseEntity<>("No tienes ordenes de compra para facturar", HttpStatus.FORBIDDEN);
        }

        Bill newBill = new Bill(shoppingCart, WayToPayType.CARD);
        billRepository.save(newBill);

        Set<PurchaseOrder> purchaseOrders= shoppingCart.getPurchaseOrders();



        purchaseOrders.forEach(purchaseOrder -> {
            Product product = purchaseOrder.getProduct();
            product.setStock(purchaseOrder.getQuantity()-product.getStock());
            productRepository.save(product);
        });


        purchaseOrRepository.deleteAll(clientInSession.getShoppingCart().getPurchaseOrders());
        clientRepository.save(clientInSession);


        ShoppingCart newShoppingCart = new ShoppingCart(clientInSession);
        shoppingCartRepository.save(newShoppingCart);

        return new ResponseEntity<>("Factura Creada", HttpStatus.CREATED);


    }


}
