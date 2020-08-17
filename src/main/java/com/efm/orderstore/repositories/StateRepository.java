package com.efm.orderstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.efm.orderstore.domains.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

}
