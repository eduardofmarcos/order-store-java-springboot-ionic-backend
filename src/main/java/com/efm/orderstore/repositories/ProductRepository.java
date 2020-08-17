package com.efm.orderstore.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.efm.orderstore.domains.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
