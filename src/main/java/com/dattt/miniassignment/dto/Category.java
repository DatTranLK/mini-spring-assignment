package com.dattt.miniassignment.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	private Set<Product> listProduct = new HashSet<>();
	public Category() {
	}
	
	public Category(Long id, String name, Set<Product> listProduct) {
		super();
		this.id = id;
		this.name = name;
		this.listProduct = listProduct;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Set<Product> getListProduct() {
		return listProduct;
	}

	public void setListProduct(Set<Product> listProduct) {
		this.listProduct = listProduct;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", listProduct=" + listProduct + "]";
	}

	
}
