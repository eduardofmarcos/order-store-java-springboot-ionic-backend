package com.efm.orderstore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.efm.orderstore.domains.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
	@Transactional(readOnly=true)
	List<City> findAll();
	
}
