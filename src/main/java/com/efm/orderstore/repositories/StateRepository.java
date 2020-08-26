package com.efm.orderstore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.efm.orderstore.domains.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

	@Transactional(readOnly=true)
	List<State> findAllByOrderByName();
	
}
