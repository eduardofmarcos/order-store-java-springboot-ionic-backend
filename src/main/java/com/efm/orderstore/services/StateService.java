package com.efm.orderstore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.efm.orderstore.domains.State;
import com.efm.orderstore.repositories.StateRepository;

@Service
public class StateService {
	
	@Autowired
	private StateRepository stateRepository;

	public List<State> findAllByOrderByName() {
		return stateRepository.findAllByOrderByName();
	}
	
}
