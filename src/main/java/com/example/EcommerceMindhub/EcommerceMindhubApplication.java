package com.example.EcommerceMindhub;

import com.example.EcommerceMindhub.models.Client;
import com.example.EcommerceMindhub.repositories.ClientRepository;
import com.example.EcommerceMindhub.repositories.ShoppingCartRepository;
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
									  ShoppingCartRepository shoppingCartRepository)  {
		return (args) ->{

			Client client1= new Client("Lucia","Saederup","luciasaederup@gmail.com","Av. siempre viva 123","1234");

			clientRepository.save(client1);

		};
	};

}