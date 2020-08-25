package com.efm.orderstore.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.efm.orderstore.domains.Client;
import com.efm.orderstore.domains.OrderCli;

public abstract class AbstractEmailService implements EmailService {

	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private TemplateEngine templateEngine;

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
		sm.setSubject("Pedido confirmado! Codigo: " + orderCli.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(orderCli.toString());
		return sm;
	}

	protected String htmlFromTemplateOrderCli(OrderCli orderCli) {
		Context context = new Context();
		context.setVariable("pedido", orderCli);
		return templateEngine.process("email/orderConfirmation", context);

	}
	
	@Override
	public void sendOrderConfirmationHtmlEmail(OrderCli orderCli) {
		try {
		MimeMessage mm = prepareMimeMessageFromOrderCli(orderCli);
		sendHtmlEmail(mm);
		}catch (MessagingException e){
			sendOrderConfirmationEmail(orderCli);
		}
	}

	protected MimeMessage prepareMimeMessageFromOrderCli(OrderCli orderCli) throws MessagingException {
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage,true);
		mmh.setTo(orderCli.getClient().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Pedido confirmado! Código: "+ orderCli.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplateOrderCli(orderCli),true);
		return mimeMessage;
	}
	
	@Override
	public void sendNewPasswordEmail(Client client, String newPass) {
		SimpleMailMessage sm = prepareNewPasswordEmail(client, newPass);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareNewPasswordEmail(Client client, String newPass) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(client.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Solicitação de nova senha");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Nova senha: "+newPass);
		return sm;
	}

}
