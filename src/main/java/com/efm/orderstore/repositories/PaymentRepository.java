package com.efm.orderstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.efm.orderstore.domains.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
