package com.efm.orderstore.services;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.efm.orderstore.domains.Category;
import com.efm.orderstore.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public Category findById(Integer id) {
		Optional<Category> obj = categoryRepository.findById(id);
		return obj.orElse(null);
	}
	
}
