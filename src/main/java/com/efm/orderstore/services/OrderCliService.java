package com.efm.orderstore.services;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.efm.orderstore.domains.OrderCli;
import com.efm.orderstore.repositories.OrderCliRepository;
import com.efm.orderstore.services.exceptions.ObjectNotFoundException;

@Service
public class OrderCliService {
	
	@Autowired
	private OrderCliRepository orderCliRepository;
	
	public OrderCli findById(Integer id) {
		Optional<OrderCli> obj = orderCliRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Object not found. Id: " + id + ", Type: " + OrderCli.class.getName()));
		}
	
}
