package com.efm.orderstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.efm.orderstore.domains.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

}
