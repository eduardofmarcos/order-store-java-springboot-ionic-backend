package com.efm.orderstore.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.efm.orderstore.domains.Category;
import com.efm.orderstore.domains.OrderCli;
import com.efm.orderstore.dto.CategoryDTO;
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
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody OrderCli obj) {
	
		obj = orderCliService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
