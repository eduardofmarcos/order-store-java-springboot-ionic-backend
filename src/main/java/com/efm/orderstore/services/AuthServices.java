package com.efm.orderstore.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.efm.orderstore.domains.Client;
import com.efm.orderstore.repositories.ClientRepository;
import com.efm.orderstore.services.exceptions.ObjectNotFoundException;

@Service
public class AuthServices {
	
	private Random rand = new Random();

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private EmailService emailService;
	
	public void sendNewPassword(String email) {
	
	Client client = clientRepository.findByEmail(email);
	System.out.println("Email new password: "+client);
	if(client==null) {
		throw new ObjectNotFoundException("client not found");
	}
	
	String newPass = newPassword();
	client.setPassword(pe.encode(newPass));
	
	clientRepository.save(client);
	emailService.sendNewPasswordEmail(client, newPass);
	
	
	}

	private String newPassword() {
	 char[] vet= new char[10];
	 for(int i =0; i<10;i++) {
		 vet[i] = randomChar();
	 }
	 return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if(opt==0) {
			//gera um digito
			return (char) (rand.nextInt(10)+48);
		}else if(opt ==1){
			//gera letra maiuscula
			return (char) (rand.nextInt(26)+65);			
		}else {
			//gera letra minuscula
			return (char) (rand.nextInt()+65);
		}
	}
}
