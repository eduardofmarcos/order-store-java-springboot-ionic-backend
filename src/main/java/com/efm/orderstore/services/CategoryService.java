package com.efm.orderstore.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.efm.orderstore.domains.Category;
import com.efm.orderstore.repositories.CategoryRepository;
import com.efm.orderstore.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public Category findById(Integer id) {
		Optional<Category> obj = categoryRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found. Id: " + id + ", Type: " + Category.class.getName()));
	}

	public Category insert(Category obj) {
		obj.setId(null);
		return categoryRepository.save(obj);
	}

	public Category update(Category obj) {
		findById(obj.getId());
		return categoryRepository.save(obj);

	}
}
