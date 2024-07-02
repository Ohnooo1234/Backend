package com.vti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vti.form.ProductFormForCreating;
import com.vti.form.ProductFormForUpdating;
import com.vti.dto.filter.ProductFilter;
import com.vti.entity.Product;
import com.vti.repository.ProductRepository;
import com.vti.specification.ProductSpecificationBuilder;

@Service
public class ProductService implements IProductService {

	@Autowired
	private ProductRepository repository;

	public Page<Product> getAllProducts(Pageable pageable, ProductFilter filter, String search) {

		ProductSpecificationBuilder specification = new ProductSpecificationBuilder(filter, search);

		return repository.findAll(specification.build(), pageable);
	}

	public boolean isProductExistsByName(String name) {
		return repository.existsByName(name);
	}

	public void createProduct(ProductFormForCreating form) {
//		repository.save(form.toEntity());
	}

	public Product getProductByID(int id) {
		return repository.findById(id).get();
	}

	public void updateProduct(int id, ProductFormForUpdating form) {
//		Product entity = repository.findById(id).get();
//		entity.setCategoryname(form.getCategoryname());
//		entity.setName(form.getName());
//		entity.setNumber_of_products(form.getNumber_of_products());
//		entity.setPrice(form.getPrice());
//		entity.setThumbnailUrl(form.getThumbnailUrl());
//		entity.setDescription(form.getDescription());
//		repository.save(entity);
	}

	@Transactional
	public void deleteProducts(List<Integer> ids) {
		repository.deleteByIdIn(ids);	
	}

}
