package com.efm.orderstore.domains;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.efm.orderstore.domains.enums.ClientProfile;
import com.efm.orderstore.domains.enums.ClientType;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String email;
	private String cpfOrCnpj;
	private Integer clientType;
	
	@JsonIgnore
	private String password;

	 // //@JsonManagedReference pega os clients e exibe os referidos endereços dos clientes, os endereços e
							// os clientes Clientes>endereços nao Endereços>clientes
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	private List<Address> addresses = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name = "phone")
	private Set<String> phoneList = new HashSet<>();
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "profile")
	private Set<Integer> profiles = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy="client")
	private List<OrderCli> orders = new ArrayList<>();

	public Client() {
		setPerfil(ClientProfile.CLIENT);
	}

	public Client(Integer id, String name, String email, String cpfOrCnpj, ClientType clientType, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.cpfOrCnpj = cpfOrCnpj;
		this.clientType = (clientType==null) ? null : clientType.getCode();
		this.password = password;
		setPerfil(ClientProfile.CLIENT);
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

	public ClientType getClientType() {
		return ClientType.toEnum(clientType);
	}

	public void setClientType(ClientType clientType) {
		this.clientType = clientType.getCode();
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Set<String> getPhoneList() {
		return phoneList;
	}

	public void setPhoneList(Set<String> phoneList) {
		this.phoneList = phoneList;
	}

	public List<OrderCli> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderCli> orders) {
		this.orders = orders;
	};
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set<ClientProfile> getProfile(){
		return profiles.stream().map(el->ClientProfile.toEnum(el)).collect(Collectors.toSet());
	}
	
	public void setPerfil(ClientProfile clientProfile) {
		profiles.add(clientProfile.getCode());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
