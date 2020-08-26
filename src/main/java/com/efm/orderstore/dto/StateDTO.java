package com.efm.orderstore.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.efm.orderstore.domains.State;



public class StateDTO implements Serializable{

	private static final long serialVersionUID = 1L;


	private Integer id;
	private String name;
	
	public StateDTO() {}
	
	public StateDTO(State state) {
		this.id = state.getId();
		this.name = state.getName();
	}
	
	public Integer getId() {
		return id;
	}

	public String getState() {
		return name;
	}

	public void setState(String state) {
		this.name = state;
	};
	
}
