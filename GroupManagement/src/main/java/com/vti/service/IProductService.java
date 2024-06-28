package com.vti.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vti.form.ProductFormForCreating;
import com.vti.form.ProductFormForUpdating;
import com.vti.dto.filter.ProductFilter;
import com.vti.entity.Product;

public interface IProductService {

	Page<Product> getAllProducts(Pageable pageable, ProductFilter filter, String search);

	boolean isProductExistsByName(String name);

	void createProduct(ProductFormForCreating form);

	Product getProductByID(int id);

	void updateProduct(int id, ProductFormForUpdating form);

	void deleteProducts(List<Integer> ids);

}
