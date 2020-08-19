package com.efm.orderstore.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.efm.orderstore.domains.Category;
import com.efm.orderstore.dto.CategoryDTO;
import com.efm.orderstore.repositories.CategoryRepository;
import com.efm.orderstore.services.exceptions.DataIntegrityException;
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
	
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public Category insert(Category obj) {
		obj.setId(null);
		return categoryRepository.save(obj);
	}

	public Category update(Category obj) {
		findById(obj.getId());
		return categoryRepository.save(obj);

	}

	public void delete(Integer id) {
		findById(id);
		try {
		categoryRepository.deleteById(id);
		}catch (DataIntegrityViolationException e){
			throw new DataIntegrityException("It is not possible to delete a category with associated products");
		}
	}
	
	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return categoryRepository.findAll(pageRequest);
	}
	
	public Category fromDTO(CategoryDTO objDTO) {
		return new Category(objDTO.getId(), objDTO.getName());
	}
}
