package com.efm.orderstore.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.efm.orderstore.domains.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
