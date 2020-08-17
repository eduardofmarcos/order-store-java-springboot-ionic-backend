package com.efm.orderstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.efm.orderstore.domains.OrderCli;

@Repository
public interface OrderRepository extends JpaRepository<OrderCli, Integer> {

}
