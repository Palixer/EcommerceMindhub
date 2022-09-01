package com.example.EcommerceMindhub;

import com.example.EcommerceMindhub.models.Product;
import com.example.EcommerceMindhub.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EcommerceMindhubApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceMindhubApplication.class, args);
	}



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
		};
	};

}
