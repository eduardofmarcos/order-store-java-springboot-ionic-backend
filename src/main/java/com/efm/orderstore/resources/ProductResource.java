package com.efm.orderstore.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.efm.orderstore.domains.Product;
import com.efm.orderstore.dto.ProductDTO;
import com.efm.orderstore.resources.utils.URL;
import com.efm.orderstore.services.ProductService;

@RestController
@RequestMapping(value="/products")
public class ProductResource {
	
	@Autowired
	ProductService productService;

	@GetMapping(value = "/{id}")
	private ResponseEntity<?> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(productService.findById(id));
	}
	
	@GetMapping
	private ResponseEntity<Page<ProductDTO>> findPage(
			@RequestParam(value="name", defaultValue="") String name,
			@RequestParam(value="categories", defaultValue="") String categories,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="name")String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC")String direction) {
		String nameDecoded = URL.decodeParam(name);
		List<Integer> ids = URL.decodeIntList(categories);
		Page<Product> objList = productService.search(nameDecoded,ids, page, linesPerPage, orderBy, direction);
		Page<ProductDTO> productDTOList = objList.map(el-> new ProductDTO(el));
		return ResponseEntity.ok().body(productDTOList);
	}
	
}
