package com.efm.orderstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.efm.orderstore.domains.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

}
