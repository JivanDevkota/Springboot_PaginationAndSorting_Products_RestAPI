package com.nsu.springbootpaginationandsorting.repository;

import com.nsu.springbootpaginationandsorting.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
