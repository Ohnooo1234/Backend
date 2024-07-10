package com.vti.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import com.vti.entity.Product;
import com.vti.dto.filter.CartItemFilter;
import com.vti.entity.CartItem;
import com.vti.entity.OrderDetail;
import com.vti.form.CartItemFormForCreating;
import com.vti.form.CartItemFormForUpdating;
import com.vti.repository.CartItemRepository;
import com.vti.repository.ProductRepository;
import com.vti.specification.CartItemSpecificationBuilder;

@Service
public class CartItemService implements ICartItemService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CartItemRepository repository;
	
	@Autowired
	private ProductRepository productRepository;
	
	public Page<CartItem> getAllCartItems(Pageable pageable, CartItemFilter filter, String search) {

		CartItemSpecificationBuilder specification = new CartItemSpecificationBuilder(filter, search);

		return repository.findAll(specification.build(), pageable);
	}	

	public CartItem getCartItemByID(int id) {
		return repository.findById(id).get();
	}

	@Transactional
	public void createCartItem(CartItemFormForCreating form) {
		
		// Convert form to entity
		CartItem cartItemEntity = modelMapper.map(form, CartItem.class);

	    // Create cartItem
		CartItem savedCartItem = repository.save(cartItemEntity);
	    repository.save(savedCartItem);

	    // Update the product's number of products
	    Product product = savedCartItem.getProduct();
	    product.setNumber_of_products(product.getNumber_of_products() - savedCartItem.getQuantity());
	    productRepository.save(product);

	}

	@Transactional
	public void updateCartItem(CartItemFormForUpdating form) {

		// Convert form to entity
	    CartItem cartItem = modelMapper.map(form, CartItem.class);

	    // Get the existing CartItem
	    CartItem existingCartItem = repository.findById(cartItem.getId()).get();

	    // Update the product's number of products
	    Product oldProduct = existingCartItem.getProduct();
	    oldProduct.setNumber_of_products(oldProduct.getNumber_of_products() + existingCartItem.getQuantity());
	    productRepository.save(oldProduct);

	    Product newProduct = productRepository.findById(form.getProduct_id()).get();
	    newProduct.setNumber_of_products(newProduct.getNumber_of_products() - cartItem.getQuantity());
	    productRepository.save(newProduct);

	    repository.save(cartItem);
	}

}
