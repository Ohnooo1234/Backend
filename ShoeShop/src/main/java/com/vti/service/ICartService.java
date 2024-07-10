package com.vti.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vti.entity.Cart;
import com.vti.form.CartFormForCreating;
import com.vti.form.CartFormForUpdating;

public interface ICartService {
	Page<Cart> getAllCarts(Pageable pageable, String search);
	
	void createCart(CartFormForCreating form);

	public Cart getCartByID(int id);

	public void updateCart(CartFormForUpdating form);

}
