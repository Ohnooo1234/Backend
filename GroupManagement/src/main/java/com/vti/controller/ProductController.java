package com.vti.controller;

import java.util.List;

import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vti.form.ProductFormForCreating;
import com.vti.form.ProductFormForUpdating;
import com.vti.dto.ProductDTO;
import com.vti.dto.filter.ProductFilter;
import com.vti.entity.Product;
import com.vti.service.IProductService;

@RestController
@RequestMapping(value = "api/v1/products")
public class ProductController {

	@Autowired
	private IProductService service;

	@GetMapping()
	public ResponseEntity<?> getAllProducts(
			Pageable pageable, 
			ProductFilter filter,
			@RequestParam(required = false) 
			String search) {
		Page<Product> entities = service.getAllProducts(pageable, filter, search);
		return new ResponseEntity<>(entities, HttpStatus.OK);
	}


	@GetMapping(value = "/name/{name}")
	public ResponseEntity<?> existsProductByName(@PathVariable(name = "name") String name) {
		return new ResponseEntity<>(service.isProductExistsByName(name), HttpStatus.OK);
	}

	@PostMapping()
//	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public ResponseEntity<?> createProduct(@RequestBody ProductFormForCreating form) {
		service.createProduct(form);
		return new ResponseEntity<String>("Create successfully!", HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getProductByID(@PathVariable(name = "id") int id) {
		return new ResponseEntity<>(service.getProductByID(id), HttpStatus.OK);
	}

	@PutMapping(value = "/update/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable(name = "id") int id, @RequestBody ProductFormForUpdating form) {
		service.updateProduct(id, form);
		return new ResponseEntity<String>("Update successfully!", HttpStatus.OK);
	}

	@DeleteMapping(value = "/{ids}")
	public ResponseEntity<?> deleteProducts(@PathVariable(name = "ids") List<Integer> ids) {
		service.deleteProducts(ids);
		return new ResponseEntity<String>("Delete successfully!", HttpStatus.OK);
	}
}
