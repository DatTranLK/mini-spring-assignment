package com.dattt.miniassignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dattt.miniassignment.dto.Category;
import com.dattt.miniassignment.repository.CategoryRepository;

import antlr.collections.List;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	public java.util.List<Category> getCate(){
		return categoryRepository.findAll();
	}
}
