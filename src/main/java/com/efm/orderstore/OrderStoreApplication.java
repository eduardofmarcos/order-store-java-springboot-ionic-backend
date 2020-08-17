package com.efm.orderstore;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.efm.orderstore.domains.Category;
import com.efm.orderstore.domains.Product;
import com.efm.orderstore.repositories.CategoryRepository;
import com.efm.orderstore.repositories.ProductRepository;

@SpringBootApplication
public class OrderStoreApplication implements CommandLineRunner{
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(OrderStoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Category category1 = new Category(null, "Informatica");
		Category category2 = new Category(null, "Eletronica");
		
		Product p1 = new Product(null, "Computador", 2000.0);
		Product p2 = new Product(null, "Mouse", 80.0);
		Product p3 = new Product(null, "TV", 1500.0);

		category1.getProducts().addAll(Arrays.asList(p1,p2,p3));
		category2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategories().addAll(Arrays.asList(category1));
		p2.getCategories().addAll(Arrays.asList(category1,category2));
		p3.getCategories().addAll(Arrays.asList(category1));
		
		categoryRepository.saveAll(Arrays.asList(category1,category2));
		productRepository.saveAll(Arrays.asList(p1,p2,p3));
	}

}
