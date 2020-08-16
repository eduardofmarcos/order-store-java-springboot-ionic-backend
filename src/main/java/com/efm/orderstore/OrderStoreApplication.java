package com.efm.orderstore;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.efm.orderstore.domains.Category;
import com.efm.orderstore.repositories.CategoryRepository;

@SpringBootApplication
public class OrderStoreApplication implements CommandLineRunner{
	
	@Autowired
	private CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(OrderStoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Category category1 = new Category(null, "Informatica");
		Category category2 = new Category(null, "Eletronica");

		categoryRepository.saveAll(Arrays.asList(category1,category2));
	}

}
