package com.efm.orderstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.efm.orderstore.domains.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
