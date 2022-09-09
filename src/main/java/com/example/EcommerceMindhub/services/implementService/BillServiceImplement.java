package com.example.EcommerceMindhub.services.implementService;

import com.example.EcommerceMindhub.dtos.BillDTO;
import com.example.EcommerceMindhub.models.*;
import com.example.EcommerceMindhub.repositories.*;
import com.example.EcommerceMindhub.services.BillService;
import com.example.EcommerceMindhub.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class BillServiceImplement implements BillService {

    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PurchaseOrRepository purchaseOrRepository;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private ProductService productService;

    @Override
    public List<BillDTO> findAll() {
        return billRepository.findAll().stream().map(bill -> new BillDTO(bill)).collect(Collectors.toList());

    }

    @Override
    public BillDTO getBillById(Long id) {
        return billRepository.findById(id).map(BillDTO::new).orElse(null);

    }
    @Override
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
        purchaseOrders.forEach(purchaseOrder -> {
            Product product = purchaseOrder.getProduct();
            if(product.getStock() == 0){
                productService.deleteProduct(product.getName());
            }

        });
        clientRepository.save(clientInSession);


        ShoppingCart newShoppingCart = new ShoppingCart(clientInSession);
        shoppingCartRepository.save(newShoppingCart);

        return new ResponseEntity<>("Factura Creada", HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<Object> paymentCard(Authentication authentication, String cardNumber, String cvv) {
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
