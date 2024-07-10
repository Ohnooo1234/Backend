package com.vti.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import com.vti.entity.User;
import com.vti.entity.Cart;
import com.vti.form.CartFormForCreating;
import com.vti.form.CartFormForUpdating;
import com.vti.repository.CartRepository;
import com.vti.repository.UserRepository;
import com.vti.specification.CartSpecificationBuilder;

@Service
public class CartService implements ICartService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CartRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Page<Cart> getAllCarts(Pageable pageable, String search) {

		CartSpecificationBuilder specification = new CartSpecificationBuilder(search);

		return repository.findAll(specification.build(), pageable);
	}	

	public Cart getCartByID(int id) {
		return repository.findById(id).get();
	}

	@Transactional
	public void createCart(CartFormForCreating form) {
		
		// convert form to entity
		Cart cartEntity = modelMapper.map(form, Cart.class);

		// create category
		Cart cart  = repository.save(cartEntity);
		
		
		Integer user_id = cart.getUser().getId();		
		User user = userRepository.findById(user_id).get();
		user.setId(user.getId());
		userRepository.save(user);

	}

	@Transactional
	public void updateCart(CartFormForUpdating form) {

		// fetch existing product
		Cart cart = repository.findById(form.getId())
				.orElseThrow(() -> new RuntimeException("Cart not found"));

		// update fields
		modelMapper.map(form, cart);

		// save updated product
		repository.save(cart);
	}

}
