package com.melo.restapi;

import com.melo.restapi.model.Product;
import com.melo.restapi.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestapiApplication implements CommandLineRunner{

	private ProductRepo productRepository;

	@Autowired
	public void productRepository(ProductRepo productRepository) {
		this.productRepository = productRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(RestapiApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {


		Product testProduct = new Product();
		testProduct.setName("Simple Product");
		testProduct.setDescription("This is a tester product");
		testProduct.setType("CUSTOM");
		testProduct.setCategory("SPECIAL");

		productRepository.save(testProduct);

	}
}
