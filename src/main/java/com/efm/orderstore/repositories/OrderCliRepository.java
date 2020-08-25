package com.efm.orderstore.repositories;



import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.efm.orderstore.domains.Client;
import com.efm.orderstore.domains.OrderCli;

@Repository
public interface OrderCliRepository extends JpaRepository<OrderCli, Integer> {

	@Transactional(readOnly = true)
	Page<OrderCli> findByClient(Client client, Pageable pageRequest);
	
}
