package com.efm.orderstore.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.efm.orderstore.domains.Category;

public class CategoryDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	
	private Integer id;
	@NotEmpty(message = "This field cannot be empty")
	@Length(min = 5, max = 80, message="Minimum size 5, Max size 80")
	private String name;

	public CategoryDTO() {
	}
	
	public CategoryDTO(Category category) {
		id = category.getId();
		name = category.getName();
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	};

}
