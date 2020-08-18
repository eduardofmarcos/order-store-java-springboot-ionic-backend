package com.efm.orderstore.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.efm.orderstore.services.OrderCliService;

@RestController
@RequestMapping(value="/orders")
public class OrderCliResource {
	
	@Autowired
	OrderCliService orderCliService;

	@GetMapping(value = "/{id}")
	private ResponseEntity<?> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(orderCliService.findById(id));
	}
	
}
