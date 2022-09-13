package com.example.EcommerceMindhub.services.implementService;

import com.example.EcommerceMindhub.dtos.PurchaseOrderDTO;
import com.example.EcommerceMindhub.models.Client;
import com.example.EcommerceMindhub.models.Product;
import com.example.EcommerceMindhub.models.PurchaseOrder;
import com.example.EcommerceMindhub.repositories.ClientRepository;
import com.example.EcommerceMindhub.repositories.ProductRepository;
import com.example.EcommerceMindhub.repositories.PurchaseOrRepository;
import com.example.EcommerceMindhub.services.ProductService;
import com.example.EcommerceMindhub.services.PurchaseOrderService;
import com.example.EcommerceMindhub.utils.EmailSenderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

@Service
public class PurchaseOrderServiceImplement implements PurchaseOrderService {

    @Autowired
    private PurchaseOrRepository purchaseOrRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private EmailSenderService emailSenderService;

    @Override
    public List<PurchaseOrderDTO> findAll() {
        return purchaseOrRepository.findAll().stream().map(purchaseOrder -> new PurchaseOrderDTO(purchaseOrder)).collect(Collectors.toList());

    }

    @Override
    public PurchaseOrderDTO getPurchaseOrderById(Long id) {
        return purchaseOrRepository.findById(id).map(PurchaseOrderDTO::new).orElse(null);

    }

    @Override
    public ResponseEntity<Object> createPurchaseOrder(String name, Integer quantity, Authentication authentication) {
        Client clientInSession = this.clientRepository.findByEmail(authentication.getName());
        Product productFind = productRepository.findByName(name.toLowerCase());


        if (name.isEmpty() || quantity <= 0) {

            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        if (productRepository.findByName(name.toLowerCase()) == null) {

            return new ResponseEntity<>("El nombre no existe", HttpStatus.FORBIDDEN);
        }

        if (quantity > productFind.getStock()) {
            return new ResponseEntity<>("No podés comprar más del stock", HttpStatus.FORBIDDEN);
        }

        PurchaseOrder newPurchaseOrder = new PurchaseOrder(quantity, productFind.getPrice() * quantity, clientInSession.getShoppingCart(), productFind);
        purchaseOrRepository.save(newPurchaseOrder);

        return new ResponseEntity<>("Orden de compra creada", HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<Object> deletePurchaseOrder(Long id) {
        Client clientFind = clientRepository.findById(id).orElse(null);
        if (clientFind == null) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }
        Set<PurchaseOrder> ordenesEncontradas = clientFind.getShoppingCart().getPurchaseOrders();
        if (!ordenesEncontradas.isEmpty()) {
            purchaseOrRepository.deleteById(id);
        }

        purchaseOrRepository.deleteById(id);

        return new ResponseEntity<>("Orden de compra borrada correctamente", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> purchaseReminder(Authentication authentication) {
        Client clientSession = this.clientRepository.findByEmail(authentication.getName());
        String emailClient = clientSession.getEmail();

        if(clientSession.getShoppingCart().getPurchaseOrders().size() == 0){
            return new ResponseEntity<>("No tenés ordenes de compra a cancelar.",HttpStatus.FORBIDDEN);
        }

        EmailSenderUtils sender = new EmailSenderUtils();
        sender.sendEmail(emailSenderService, emailClient);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
