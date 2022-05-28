package com.dattt.miniassignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dattt.miniassignment.dto.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
