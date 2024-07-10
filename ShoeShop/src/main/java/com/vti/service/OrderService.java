package com.vti.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.vti.entity.User;
import com.vti.entity.Order;
import com.vti.form.OrderFormForCreating;
import com.vti.form.OrderFormForUpdating;
import com.vti.repository.OrderRepository;
import com.vti.repository.UserRepository;
import com.vti.specification.OrderSpecificationBuilder;

@Service
public class OrderService implements IOrderService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	public Page<Order> getAllOrders(Pageable pageable, String search) {

		OrderSpecificationBuilder specification = new OrderSpecificationBuilder(search);
		return repository.findAll(specification.build(), pageable);
	}	
//	public Page<Order> getAllOrders(Pageable pageable, String search) {
//        Specification<Order> specification = new OrderSpecificationBuilder(search).build();
//        return repository.findAll(specification, pageable);
//    }
	public Order getOrderByID(int id) {
		return repository.findById(id).get();
	}

	@Transactional
	public void createOrder(OrderFormForCreating form) {
		
		// convert form to entity
		Order orderEntity = modelMapper.map(form, Order.class);

		// create order
		Order order  = repository.save(orderEntity);
		
		
		Integer user_id = order.getUser().getId();		
		User user = userRepository.findById(user_id).get();
		user.setId(user.getId());
		userRepository.save(user);

	}

	@Transactional
	public void updateOrder(OrderFormForUpdating form) {

		// fetch existing product
		Order order = repository.findById(form.getId())
				.orElseThrow(() -> new RuntimeException("Order not found"));

		// update fields
		modelMapper.map(form, order);

		// save updated product
		repository.save(order);
	}

}
