package com.efm.orderstore.services;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.efm.orderstore.domains.Client;
import com.efm.orderstore.repositories.ClientRepository;
import com.efm.orderstore.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	public Client findById(Integer id) {
		Optional<Client> obj = clientRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Object not found. Id: " + id + ", Type: " + Client.class.getName()));
		}
	
}
