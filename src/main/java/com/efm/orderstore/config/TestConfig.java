package com.efm.orderstore.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.efm.orderstore.services.DBService;
import com.efm.orderstore.services.EmailService;
import com.efm.orderstore.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	DBService dbService;

	@Bean
	public boolean instantiateDatabase() throws ParseException{
		dbService.instantiateDatabase();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}
	
}
