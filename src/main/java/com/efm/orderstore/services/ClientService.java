package com.efm.orderstore.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.efm.orderstore.domains.Client;
import com.efm.orderstore.dto.ClientDTO;
import com.efm.orderstore.repositories.ClientRepository;
import com.efm.orderstore.services.exceptions.DataIntegrityException;
import com.efm.orderstore.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	public Client findById(Integer id) {
		Optional<Client> obj = clientRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Object not found. Id: " + id + ", Type: " + Client.class.getName()));
		}
	
	public List<Client> findAll() {
		return clientRepository.findAll();
	}

//	public Client insert(Client obj) {
//		obj.setId(null);
//		return clientRepository.save(obj);
//	}

	public Client update(Client obj) {
		Client newObj = findById(obj.getId());
		updateDate(newObj, obj);
		return clientRepository.save(newObj);

	}

	private void updateDate(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
		
}

	public void delete(Integer id) {
		findById(id);
		try {
		clientRepository.deleteById(id);
		}catch (DataIntegrityViolationException e){
			throw new DataIntegrityException("It is not possible to delete a category with associated products");
		}
	}
	
	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clientRepository.findAll(pageRequest);
	}
	
	public Client fromDTO(ClientDTO objDTO) {
		return new Client(objDTO.getId(),objDTO.getName(),objDTO.getEmail(),null,null);
	}
}
