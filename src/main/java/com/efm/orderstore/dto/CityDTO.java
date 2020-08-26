package com.efm.orderstore.dto;

import com.efm.orderstore.domains.City;

public class CityDTO {

	private Integer id;
	private String name;
	
	public CityDTO() {};
	
	public CityDTO(City city) {
		this.setId(city.getId());
		this.setName(city.getName());
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
	}
	
	
	
}
