package com.efm.orderstore.services;

import org.springframework.mail.SimpleMailMessage;

import com.efm.orderstore.domains.OrderCli;

public interface EmailService {

	void sendOrderConfirmationEmail(OrderCli orderCli);
	
	void sendEmail(SimpleMailMessage msg);
	
}
