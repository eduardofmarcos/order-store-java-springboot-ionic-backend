package com.efm.orderstore.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.efm.orderstore.domains.Client;
import com.efm.orderstore.dto.ClientDTO;
import com.efm.orderstore.dto.ClientNewDTO;
import com.efm.orderstore.services.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

	@Autowired
	ClientService clientService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(clientService.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody ClientNewDTO objDTO) {
		Client obj = clientService.fromDTO(objDTO);
		obj = clientService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody ClientDTO objDTO) {
		Client obj = clientService.fromDTO(objDTO);
		obj.setId(id);
		obj = clientService.update(obj);
		return ResponseEntity.noContent().build();
	}
	@PostAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		clientService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PostAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<ClientDTO>> findAll() {
		List<Client> objList = clientService.findAll();
		List<ClientDTO> categoryDTOList = objList.stream().map(el -> new ClientDTO(el))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(categoryDTOList);
	}

	@PostAuthorize("hasAnyRole('ADMIN')")
	@GetMapping(value = "/page")
	public ResponseEntity<Page<ClientDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Client> objList = clientService.findPage(page, linesPerPage, orderBy, direction);
		Page<ClientDTO> categoryDTOList = objList.map(el -> new ClientDTO(el));
		return ResponseEntity.ok().body(categoryDTOList);
	}

}
