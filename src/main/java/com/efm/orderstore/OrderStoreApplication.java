package com.efm.orderstore;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
import com.efm.orderstore.domains.enums.ClientType;
import com.efm.orderstore.domains.enums.PaymentStatus;
import com.efm.orderstore.repositories.AddressRepository;
import com.efm.orderstore.repositories.CategoryRepository;
import com.efm.orderstore.repositories.CityRepository;
import com.efm.orderstore.repositories.ClientRepository;
import com.efm.orderstore.repositories.OrderItemRepository;
import com.efm.orderstore.repositories.OrderCliRepository;
import com.efm.orderstore.repositories.PaymentRepository;
import com.efm.orderstore.repositories.ProductRepository;
import com.efm.orderstore.repositories.StateRepository;
import com.efm.orderstore.services.S3Service;

@SpringBootApplication
public class OrderStoreApplication implements CommandLineRunner {

	@Autowired
	private S3Service s3service;

	public static void main(String[] args) {
		SpringApplication.run(OrderStoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		s3service.uploadFile("/home/eduardo/Desktop/bourez-surf-teahupoo.jpeg");
	}

}
