package com.efm.orderstore.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.efm.orderstore.domains.OrderCli;

public abstract class AbstractEmailService implements EmailService{
	
	@Value("${default.sender}")
	private String sender;

	@Override
	public void sendOrderConfirmationEmail(OrderCli orderCli) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromOrderCli(orderCli);
		sendEmail(sm);
		
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromOrderCli(OrderCli orderCli) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(orderCli.getClient().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido confirmado! Codigo: "+ orderCli.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(orderCli.toString());
		return sm;
	}

	

	
}
