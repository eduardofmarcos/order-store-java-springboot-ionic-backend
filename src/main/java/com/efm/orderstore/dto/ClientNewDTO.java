package com.efm.orderstore.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.efm.orderstore.services.validations.ClientInsert;

@ClientInsert
public class ClientNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "This field cannot be empty")
	@Length(min = 5, max = 120, message = "Minimum size 5, Max size 120")
	private String name;
	
	@NotEmpty(message = "This field cannot be empty")
	@Email(message = "Invalid email")
	private String email;
	@NotEmpty(message = "This field cannot be empty")
	private String cpfOrCnpj;
	private Integer clientType;
	@NotEmpty(message = "This field cannot be empty")
	private String password;

	@NotEmpty(message = "This field cannot be empty")
	private String street;
	@NotEmpty(message = "This field cannot be empty")
	private String number;
	private String complement;
	@NotEmpty(message = "This field cannot be empty")
	private String district;
	@NotEmpty(message = "This field cannot be empty")
	private String cep;
	@NotEmpty(message = "This field cannot be empty")
	private String phone1;
	private String phone2;
	private String phone3;

	private Integer cityId;

	public ClientNewDTO() {
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

	public String getCpfOrCnpj() {
		return cpfOrCnpj;
	}

	public void setCpfOrCnpj(String cpfOrCnpj) {
		this.cpfOrCnpj = cpfOrCnpj;
	}

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	};

}
