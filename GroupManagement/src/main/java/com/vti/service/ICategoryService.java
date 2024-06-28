package com.vti.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vti.form.CategoryFormForCreating;
import com.vti.form.CategoryFormForUpdating;
import com.vti.entity.Category;

public interface ICategoryService {

	Page<Category> getAllCategorys(Pageable pageable, String search);

	boolean isCategoryExistsByName(String name);

	void createCategory(CategoryFormForCreating form);

	Category getCategoryByID(int id);

	void updateCategory(int id, CategoryFormForUpdating form);

	void deleteCategorys(List<Integer> ids);

}
