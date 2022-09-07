package com.example.EcommerceMindhub;

import com.example.EcommerceMindhub.models.Client;
import com.example.EcommerceMindhub.models.Product;
import com.example.EcommerceMindhub.models.PurchaseOrder;
import com.example.EcommerceMindhub.models.ShoppingCart;
import com.example.EcommerceMindhub.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class EcommerceMindhubApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceMindhubApplication.class, args);
	}



	@Autowired
	private PasswordEncoder passwordEncoder;
	@Bean
	//Instanciamos el repositorio
	public CommandLineRunner initData(ClientRepository clientRepository,
									  ShoppingCartRepository shoppingCartRepositories,
									  PurchaseOrRepository purchaseOrRepository,
									  ProductRepository productRepository,
									  BillRepository billRepository)  {
		return (args) ->{
			Product product1=new Product("Ball",200.00,10 );
			Product product2=new Product("Raqueta",100.00,5 );
			Product product3=new Product("Green Ball",150.00,15 );

			productRepository.save(product1);
			productRepository.save(product2);
			productRepository.save(product3);

			Client client1=new Client("Lucia", "Saederup", "lucia@gmail.com", "Av. Siempre Viva 123", passwordEncoder.encode("1234"));
			Client client2=new Client("Gabriel", "Cuello", "Gabriel@gmail.com", "Av. Siempre Viva 130", passwordEncoder.encode("1234"));
			Client client3=new Client("Ibrian", "Festorazzi", "Ibrian@gmail.com", "Av. Siempre Viva 145", passwordEncoder.encode("1234"));
			Client client4=new Client("Nadia", "Matsumoto", "Nadia@gmail.com", "Av. Siempre Viva 150", passwordEncoder.encode("1234"));

			clientRepository.save(client1);
			clientRepository.save(client2);
			clientRepository.save(client3);
			clientRepository.save(client4);

			ShoppingCart carrito1=new ShoppingCart(client1);
			ShoppingCart carrito2=new ShoppingCart(client2);
			ShoppingCart carrito3=new ShoppingCart(client3);
			ShoppingCart carrito4=new ShoppingCart(client4);

			shoppingCartRepositories.save(carrito1);
			shoppingCartRepositories.save(carrito2);
			shoppingCartRepositories.save(carrito3);
			shoppingCartRepositories.save(carrito4);




		};

	};

}
