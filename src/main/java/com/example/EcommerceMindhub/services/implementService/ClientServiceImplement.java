package com.example.EcommerceMindhub.services.implementService;

import com.example.EcommerceMindhub.dtos.BillDTO;
import com.example.EcommerceMindhub.dtos.ClientDTO;
import com.example.EcommerceMindhub.models.Client;
import com.example.EcommerceMindhub.models.PurchaseOrder;
import com.example.EcommerceMindhub.models.ShoppingCart;
import com.example.EcommerceMindhub.repositories.ClientRepository;
import com.example.EcommerceMindhub.repositories.PurchaseOrRepository;
import com.example.EcommerceMindhub.repositories.ShoppingCartRepository;
import com.example.EcommerceMindhub.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class ClientServiceImplement implements ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PurchaseOrRepository purchaseOrRepository;

    @Override
    public List<ClientDTO> findAll() {
        return clientRepository.findAll().stream().map(client -> new ClientDTO(client)).collect(Collectors.toList());
    }

    @Override
    public ClientDTO getClientById(Long id) {
        return clientRepository.findById(id).map(ClientDTO::new).orElse(null);
    }

    @Override
    public ClientDTO getClient(Authentication authentication) {
        Client client = this.clientRepository.findByEmail(authentication.getName());

        return new ClientDTO(client);
    }
    @Override
    public Client findByEmail(String email) {
        Client client = this.clientRepository.findByEmail(email);

        return client ;
    }

    @Override
    public ResponseEntity<Object> createNewClient(String firstName, String lastName, String email, String address, String password){
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {

            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        if (clientService.findByEmail(email) !=  null) {

            return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);
        }

        Client newClient=new Client(firstName, lastName, email, address, passwordEncoder.encode(password));

        clientRepository.save(newClient);

        ShoppingCart newShoppingCart = new ShoppingCart(newClient);

        shoppingCartRepository.save(newShoppingCart);

        return new ResponseEntity<>("Cliente creado correctamente",HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> deleteClient(Long id) {
        Client clientFind = clientRepository.findById(id).orElse(null);
        if (clientFind==null){
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);}
        Set<PurchaseOrder> ordenesEncontradas= clientFind.getShoppingCart().getPurchaseOrders();
        if (!ordenesEncontradas.isEmpty()){
            purchaseOrRepository.deleteAll(ordenesEncontradas);}

        ShoppingCart shoppingCartFind = clientFind.getShoppingCart();
        shoppingCartRepository.deleteById(id);
        clientService.deleteClient(id);



        return new ResponseEntity<>("Cliente Borrado correctamente",HttpStatus.OK);

    }


    }
