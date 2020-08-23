package com.efm.orderstore.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.efm.orderstore.domains.OrderCli;

public interface EmailService {

	void sendOrderConfirmationEmail(OrderCli orderCli);

	void sendEmail(SimpleMailMessage msg);

	void sendOrderConfirmationHtmlEmail(OrderCli orderCli);

	void sendHtmlEmail(MimeMessage msg);

}
