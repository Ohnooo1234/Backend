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
		
		// convert form to entity
		CartItem cartitemEntity = modelMapper.map(form, CartItem.class);

		// create category
		CartItem cartitem  = repository.save(cartitemEntity);
		
		
		Integer product_id = cartitem.getProduct().getId();		
		Product product = productRepository.findById(product_id).get();
		product.setId(product.getId());
		productRepository.save(product);

	}

	@Transactional
	public void updateCartItem(CartItemFormForUpdating form) {

		// convert form to entity
		CartItem cartitem = modelMapper.map(form, CartItem.class);

		CartItem cart = repository.findById(form.getId()).get();
		cart.setQuantity(cartitem.getQuantity());
		repository.save(cart);	
	}

}
