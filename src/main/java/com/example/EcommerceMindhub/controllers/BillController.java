package com.example.EcommerceMindhub.controllers;

import com.example.EcommerceMindhub.dtos.BillDTO;
import com.example.EcommerceMindhub.dtos.PurchaseOrderDTO;
import com.example.EcommerceMindhub.models.*;
import com.example.EcommerceMindhub.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
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
    public BillDTO getBillById (@PathVariable Long id){

        return billRepository.findById(id).map(BillDTO::new).orElse(null);
    }
    @PostMapping("/shoppingCart/current/bills")
    public ResponseEntity<Object> postBillDTO(Authentication authentication){
        Client clientInSession= this.clientRepository.findByEmail(authentication.getName());
        ShoppingCart shoppingCart= clientInSession.getShoppingCart();


        if (shoppingCart.getPurchaseOrders().isEmpty()){
            return new ResponseEntity<>("No tienes ordenes de compra para facturar", HttpStatus.FORBIDDEN);
        }

        Bill newBill=new Bill(shoppingCart);
        billRepository.save(newBill);

        Set<PurchaseOrder> purchaseOrders= shoppingCart.getPurchaseOrders();



        purchaseOrders.forEach(purchaseOrder -> {
            Product product = purchaseOrder.getProduct();
            product.setStock(purchaseOrder.getQuantity()-product.getStock());
            productRepository.save(product);
        });



        purchaseOrRepository.deleteAll(clientInSession.getShoppingCart().getPurchaseOrders());
        clientRepository.save(clientInSession);

        return new ResponseEntity<>("Factura Creada", HttpStatus.CREATED);

}

    @PostMapping("/shoppingCart/current/bills/pay")
    public ResponseEntity<Object> payBill(Authentication authentication, WayToPayType wayToPayType){
        Client clientInSession= this.clientRepository.findByEmail(authentication.getName());
        ShoppingCart shoppingCart= clientInSession.getShoppingCart();
        Set<Bill> billEncontradas= shoppingCart.getBills();

        if (billEncontradas.isEmpty()){
            return new ResponseEntity<>("No tienes facturas para abonar", HttpStatus.FORBIDDEN);
        }


        return new ResponseEntity<>("Factura Abonada",HttpStatus.OK);

    }
}
