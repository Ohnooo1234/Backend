package com.vti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vti.form.CategoryFormForCreating;
import com.vti.form.CategoryFormForUpdating;
import com.vti.entity.Category;
import com.vti.repository.CategoryRepository;
import com.vti.specification.CategorySpecificationBuilder;

@Service
public class CategoryService implements ICategoryService {

	@Autowired
	private CategoryRepository repository;

	public Page<Category> getAllCategorys(Pageable pageable, String search) {

		CategorySpecificationBuilder specification = new CategorySpecificationBuilder(search);

		return repository.findAll(specification.build(), pageable);
	}

	public boolean isCategoryExistsByName(String name) {
		return repository.existsByName(name);
	}

	public void createCategory(CategoryFormForCreating form) {
		repository.save(form.toEntity());
	}

	public Category getCategoryByID(int id) {
		return repository.findById(id).get();
	}

	public void updateCategory(int id, CategoryFormForUpdating form) {
		Category entity = repository.findById(id).get();
		entity.setName(form.getName());
		entity.setDescription(form.getDescription());
		repository.save(entity);
	}

	@Transactional
	public void deleteCategorys(List<Integer> ids) {
		repository.deleteByIdIn(ids);	
	}

}
