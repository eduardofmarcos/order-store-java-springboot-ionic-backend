package com.efm.orderstore;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.efm.orderstore.domains.Address;
import com.efm.orderstore.domains.Category;
import com.efm.orderstore.domains.City;
import com.efm.orderstore.domains.Client;
import com.efm.orderstore.domains.Product;
import com.efm.orderstore.domains.State;
import com.efm.orderstore.domains.enums.ClientType;
import com.efm.orderstore.repositories.AddressRepository;
import com.efm.orderstore.repositories.CategoryRepository;
import com.efm.orderstore.repositories.CityRepository;
import com.efm.orderstore.repositories.ClientRepository;
import com.efm.orderstore.repositories.ProductRepository;
import com.efm.orderstore.repositories.StateRepository;

@SpringBootApplication
public class OrderStoreApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private ClientRepository clientRepository;

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

		category1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		category2.getProducts().addAll(Arrays.asList(p2));

		p1.getCategories().addAll(Arrays.asList(category1));
		p2.getCategories().addAll(Arrays.asList(category1, category2));
		p3.getCategories().addAll(Arrays.asList(category1));

		categoryRepository.saveAll(Arrays.asList(category1, category2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));

		State s1 = new State(null, "Minas Gerais");
		State s2 = new State(null, "Sao paulo");

		City c1 = new City(null, "Uberlandia", s1);
		City c2 = new City(null, "Sao Paulo", s2);
		City c3 = new City(null, "Campinas", s2);

		s1.getCities().addAll(Arrays.asList(c1));
		s2.getCities().addAll(Arrays.asList(c2, c3));

		stateRepository.saveAll(Arrays.asList(s1, s2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));

		Client cli1 = new Client(null, "Maria Silva", "maria@gmail.com", "1234234423", ClientType.PESSOAFISICA);
		cli1.getPhoneList().addAll(Arrays.asList("88842388", "42398749"));

		Address addr1 = new Address(null, "Flores", "300", "asd", "saddsa", "8880280", cli1, c1);
		Address addr2 = new Address(null, "Matos", "350", "dfg", "saddsgdfgda", "88802100", cli1, c2);

		cli1.getAddresses().addAll(Arrays.asList(addr1, addr2));

		clientRepository.saveAll(Arrays.asList(cli1));

		addressRepository.saveAll(Arrays.asList(addr1, addr2));

	}

}
