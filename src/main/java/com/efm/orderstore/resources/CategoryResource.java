package com.efm.orderstore.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/categories")
public class CategoryResource {

	@GetMapping
	private String listar() {
		return "REST esta funcionando";
	}
	
}
