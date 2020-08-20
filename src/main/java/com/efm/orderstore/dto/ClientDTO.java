package com.efm.orderstore.dto;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.efm.orderstore.domains.Client;
import com.efm.orderstore.services.validations.ClientUpdate;

@ClientUpdate
public class ClientDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;

	@NotEmpty(message="This field cannot be empty")
	@Length(min = 5, max = 120, message="Minimum size 5, Max size 120")
	private String name;
	@NotEmpty(message="This field cannot be empty")
	@Email(message="Invalid email fields")
	private String email;

	public ClientDTO() {
	}

	public ClientDTO(Client obj) {
		this.id = obj.getId();
		this.name = obj.getName();
		this.email = obj.getEmail();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	};

}
