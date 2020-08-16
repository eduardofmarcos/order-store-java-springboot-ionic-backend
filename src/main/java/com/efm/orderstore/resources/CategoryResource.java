package com.efm.orderstore.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.efm.orderstore.services.CategoryService;

@RestController
@RequestMapping(value="/categories")
public class CategoryResource {
	
	@Autowired
	CategoryService categoryService;

	@GetMapping(value = "/{id}")
	private ResponseEntity<?> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(categoryService.findById(id));
	}
	
}
