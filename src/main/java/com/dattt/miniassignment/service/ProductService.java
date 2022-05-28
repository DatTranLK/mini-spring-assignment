package com.dattt.miniassignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dattt.miniassignment.dto.Product;
import com.dattt.miniassignment.repository.ProductRepository;

import antlr.collections.List;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	public java.util.List<Product> getAllProduct(){
		return productRepository.findAll();
	}
	public Product getProduct(Long id) {
		return productRepository.getById(id);
	}
	public void addProduct(Product product) {
		productRepository.save(product);
	}
}
