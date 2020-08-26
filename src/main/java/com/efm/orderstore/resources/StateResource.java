package com.efm.orderstore.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.efm.orderstore.domains.City;
import com.efm.orderstore.domains.State;
import com.efm.orderstore.dto.CityDTO;
import com.efm.orderstore.dto.StateDTO;
import com.efm.orderstore.services.CityService;
import com.efm.orderstore.services.StateService;

@RestController
@RequestMapping(value = "/states")
public class StateResource {

	@Autowired
	private CityService cityService;

	@Autowired
	private StateService stateService;

	// DTO restringe dados pra retornar na busca
	@GetMapping
	public ResponseEntity<List<StateDTO>> findAll() {
		List<State> objList = stateService.findAllByOrderByName();
		List<StateDTO> stateDTOList = objList.stream().map(el -> new StateDTO(el)).collect(Collectors.toList());
		return ResponseEntity.ok().body(stateDTOList);
	}

	@GetMapping(value = "{id}/cities")
	public ResponseEntity<List<CityDTO>> findAll(@PathVariable Integer id) {
		List<City> filteredCities = cityService.findAll().stream().filter(el -> el.getState().getId().equals(id)).collect(Collectors.toList());
		List<CityDTO> cityDTOList = filteredCities.stream().map(el -> new CityDTO(el)).collect(Collectors.toList());
		return ResponseEntity.ok().body(cityDTOList);
	}

}
