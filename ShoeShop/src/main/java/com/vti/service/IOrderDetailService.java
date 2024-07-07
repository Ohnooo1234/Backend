package com.vti.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vti.entity.OrderDetail;
import com.vti.form.OrderDetailFormForCreating;
import com.vti.form.OrderDetailFormForUpdating;

public interface IOrderDetailService {
	Page<OrderDetail> getAllOrderDetails(Pageable pageable, String search);
	
	void createOrderDetail(OrderDetailFormForCreating form);

	public OrderDetail getOrderDetailByID(int id);

	public void updateOrderDetail(OrderDetailFormForUpdating form);

}
