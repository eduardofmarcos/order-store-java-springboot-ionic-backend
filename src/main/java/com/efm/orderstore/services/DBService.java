package com.efm.orderstore.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.efm.orderstore.domains.Address;
import com.efm.orderstore.domains.Category;
import com.efm.orderstore.domains.City;
import com.efm.orderstore.domains.Client;
import com.efm.orderstore.domains.CreditCardPayment;
import com.efm.orderstore.domains.OrderCli;
import com.efm.orderstore.domains.OrderItem;
import com.efm.orderstore.domains.Payment;
import com.efm.orderstore.domains.PaymentSlip;
import com.efm.orderstore.domains.Product;
import com.efm.orderstore.domains.State;
import com.efm.orderstore.domains.enums.ClientProfile;
import com.efm.orderstore.domains.enums.ClientType;
import com.efm.orderstore.domains.enums.PaymentStatus;
import com.efm.orderstore.repositories.AddressRepository;
import com.efm.orderstore.repositories.CategoryRepository;
import com.efm.orderstore.repositories.CityRepository;
import com.efm.orderstore.repositories.ClientRepository;
import com.efm.orderstore.repositories.OrderCliRepository;
import com.efm.orderstore.repositories.OrderItemRepository;
import com.efm.orderstore.repositories.PaymentRepository;
import com.efm.orderstore.repositories.ProductRepository;
import com.efm.orderstore.repositories.StateRepository;

@Service
public class DBService {
	
	@Autowired
	private BCryptPasswordEncoder pe;

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
	@Autowired
	private OrderCliRepository orderRepository;
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;

	public void instantiateDatabase() throws ParseException {
		Category category1 = new Category(null, "Informatica");
		Category category2 = new Category(null, "Eletronica");
		Category category3 = new Category(null, "Cama");
		Category category4 = new Category(null, "Mesa");
		Category category5 = new Category(null, "Banho");
		Category category6 = new Category(null, "Perfumes");
		Category category7 = new Category(null, "Roupas");
		Category category8 = new Category(null, "Acessorios");
		Category category9 = new Category(null, "Sofas");
		Category category10 = new Category(null, "Bazar");
	

		Product p1 = new Product(null, "Computador", 2000.0);
		Product p2 = new Product(null, "Mouse", 80.0);
		Product p3 = new Product(null, "TV", 1500.0);
		Product p4 = new Product(null, "Mesa", 2000.0);
		Product p5 = new Product(null, "Toalha", 80.0);
		Product p6 = new Product(null, "Cafeteira", 1500.0);
		Product p7 = new Product(null, "Maquina de lavar", 2000.0);
		Product p8 = new Product(null, "Sofa", 80.0);
		Product p9 = new Product(null, "Cama", 1500.0);
		Product p10 = new Product(null, "Bule", 2000.0);

		category1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		category2.getProducts().addAll(Arrays.asList(p2, p4));
		category3.getProducts().addAll(Arrays.asList(p5, p6));
		category4.getProducts().addAll(Arrays.asList(p6, p7, p8));

		p1.getCategories().addAll(Arrays.asList(category1));
		p2.getCategories().addAll(Arrays.asList(category1, category2));
		p3.getCategories().addAll(Arrays.asList(category1));
		p4.getCategories().addAll(Arrays.asList(category2));
		p5.getCategories().addAll(Arrays.asList(category3));
		p6.getCategories().addAll(Arrays.asList(category3));
		p7.getCategories().addAll(Arrays.asList(category4));
		p8.getCategories().addAll(Arrays.asList(category4));

		categoryRepository.saveAll(Arrays.asList(category1, category2, category3, category4, category5, category6,
				category7, category8, category9, category10));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8));

		State s1 = new State(null, "Minas Gerais");
		State s2 = new State(null, "Sao paulo");

		City c1 = new City(null, "Uberlandia", s1);
		City c2 = new City(null, "Sao Paulo", s2);
		City c3 = new City(null, "Campinas", s2);

		s1.getCities().addAll(Arrays.asList(c1));
		s2.getCities().addAll(Arrays.asList(c2, c3));

		stateRepository.saveAll(Arrays.asList(s1, s2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));

		Client cli1 = new Client(null, "Maria Silva", "danesburrito@gmail.com", "1234234423", ClientType.PESSOAFISICA, pe.encode("batata"));
		cli1.getPhoneList().addAll(Arrays.asList("88842388", "42398749"));
		cli1.setPerfil(ClientProfile.ADMIN);
		Client cli2 = new Client(null, "Carlos Silva", "joao@gmail.com", "1234234423", ClientType.PESSOAFISICA, pe.encode("ameixa"));
		cli2.getPhoneList().addAll(Arrays.asList("23423432", "435345534"));
		cli2.setPerfil(ClientProfile.ADMIN);

		Address addr1 = new Address(null, "Flores", "300", "asd", "saddsa", "8880280", cli1, c1);
		Address addr2 = new Address(null, "Matos", "350", "dfg", "saddsgdfgda", "88802100", cli1, c2);
		Address addr3 = new Address(null, "Matos", "350", "dfg", "saddsgdfgda", "88802100", cli2, c2);

		cli1.getAddresses().addAll(Arrays.asList(addr1, addr2));
		cli2.getAddresses().addAll(Arrays.asList(addr3));

		clientRepository.saveAll(Arrays.asList(cli1));
		clientRepository.saveAll(Arrays.asList(cli2));

		addressRepository.saveAll(Arrays.asList(addr1, addr2,addr3));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		OrderCli o1 = new OrderCli(null, sdf.parse("30/09/2018 10:32"), cli1, addr1);
		OrderCli o2 = new OrderCli(null, sdf.parse("10/09/2018 15:32"), cli1, addr2);

		Payment pay1 = new CreditCardPayment(null, PaymentStatus.PAID, o1, 5);
		o1.setPayment(pay1);

		Payment pay2 = new PaymentSlip(null, PaymentStatus.PENDING, o2, sdf.parse("20/10/2017 00:00"), null);
		o2.setPayment(pay2);

		cli1.getOrders().addAll(Arrays.asList(o1, o2));

		orderRepository.saveAll(Arrays.asList(o1, o2));
		paymentRepository.saveAll(Arrays.asList(pay1, pay2));

		OrderItem oi1 = new OrderItem(o1, p1, 0.00, 1, 2.000);
		OrderItem oi2 = new OrderItem(o1, p3, 0.00, 2, 80.00);
		OrderItem oi3 = new OrderItem(o2, p2, 100.00, 1, 800.00);

		o1.getOrderItems().addAll(Arrays.asList(oi1, oi2));
		o2.getOrderItems().addAll(Arrays.asList(oi3));

		p1.getOrderItems().addAll(Arrays.asList(oi1));
		p2.getOrderItems().addAll(Arrays.asList(oi3));
		p3.getOrderItems().addAll(Arrays.asList(oi2));

		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3));
	}
}
