package com.vti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.vti.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

	public Product findByName(String name);

	public boolean existsByName(String name);

	public void deleteByIdIn(List<Integer> ids);
	
	public List<Product> findByCategory_Id(Integer id);
}
