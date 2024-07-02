package com.vti.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import com.vti.entity.Product;
import com.vti.entity.Category;
import com.vti.form.CategoryFormForCreating;
import com.vti.form.CategoryFormForUpdating;
import com.vti.repository.CategoryRepository;
import com.vti.repository.ProductRepository;
import com.vti.specification.CategorySpecificationBuilder;

@Service
public class CategoryService implements ICategoryService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CategoryRepository repository;
	
	@Autowired
	private ProductRepository productRepository;
	
	public Page<Category> getAllCategorys(Pageable pageable, String search) {

		CategorySpecificationBuilder specification = new CategorySpecificationBuilder(search);

		return repository.findAll(specification.build(), pageable);
	}	

	public Category getCategoryByID(int id) {
		return repository.findById(id).get();
	}

	@Transactional
	public void createCategory(CategoryFormForCreating form) {
		
		// convert form to entity
		Category categoryEntity = modelMapper.map(form, Category.class);

		// create department
		Category category  = repository.save(categoryEntity);
		
		
		// create accounts
		List<Product> productEntities = new ArrayList<Product>();
		List<CategoryFormForCreating.Product> products = form.getProducts();
		for (CategoryFormForCreating.Product product : products) {
			int id = product.getId();
			Product acc = productRepository.findById(id).get();
			acc.setCategory(category);
			productEntities.add(acc);
		}
		productRepository.saveAll(productEntities);

	}

	@Transactional
	public void updateCategory(CategoryFormForUpdating form) {

		// convert form to entity
		Category category = modelMapper.map(form, Category.class);

		Category dep = repository.findById(form.getId()).get();
		category.setName(dep.getName());
		category.setDescription(dep.getDescription());
		repository.save(category);
		
	}
	
	@Transactional
	public void deleteCategorys(List<Integer> ids) {
		for (Integer id : ids) {
            List<Product> products = productRepository.findByCategory_Id(id);
            productRepository.deleteAll(products);
        }
        repository.deleteByIdIn(ids);
    }

	public boolean isCategoryExistsByName(String name) {
		return repository.existsByName(name);
	}

}
