package com.efm.orderstore.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.efm.orderstore.domains.Category;
import com.efm.orderstore.dto.CategoryDTO;
import com.efm.orderstore.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

	@Autowired
	CategoryService categoryService;

	@GetMapping(value = "/{id}")
	private ResponseEntity<?> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(categoryService.findById(id));
	}

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody Category obj) {
		obj = categoryService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody Category obj) {
		obj.setId(id);
		obj = categoryService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/{id}")
	private ResponseEntity<?> delete(@PathVariable Integer id) {
		categoryService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	private ResponseEntity<List<CategoryDTO>> findAll() {
		List<Category> objList = categoryService.findAll();
		List<CategoryDTO> categoryDTOList = objList.stream().map(el->new CategoryDTO(el)).collect(Collectors.toList());
		return ResponseEntity.ok().body(categoryDTOList);
	}
}
