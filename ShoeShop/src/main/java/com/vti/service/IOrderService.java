package com.vti.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vti.entity.Order;
import com.vti.form.OrderFormForCreating;
import com.vti.form.OrderFormForUpdating;

public interface IOrderService {
	Page<Order> getAllOrders(Pageable pageable, String search);
	
	void createOrder(OrderFormForCreating form);

	public Order getOrderByID(int id);

	public void updateOrder(OrderFormForUpdating form);

}
