package com.vti.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import com.vti.entity.Product;
import com.vti.entity.OrderDetail;
import com.vti.form.OrderDetailFormForCreating;
import com.vti.form.OrderDetailFormForUpdating;
import com.vti.repository.OrderDetailRepository;
import com.vti.repository.ProductRepository;
import com.vti.specification.OrderDetailSpecificationBuilder;

@Service
public class OrderDetailService implements IOrderDetailService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private OrderDetailRepository repository;
	
	@Autowired
	private ProductRepository productRepository;
	
	public Page<OrderDetail> getAllOrderDetails(Pageable pageable, String search) {

		OrderDetailSpecificationBuilder specification = new OrderDetailSpecificationBuilder(search);

		return repository.findAll(specification.build(), pageable);
	}	

	public OrderDetail getOrderDetailByID(int id) {
		return repository.findById(id).get();
	}

	@Transactional
	public void createOrderDetail(OrderDetailFormForCreating form) {
		
		// convert form to entity
		OrderDetail orderdetailEntity = modelMapper.map(form, OrderDetail.class);

		// create category
		OrderDetail orderdetail  = repository.save(orderdetailEntity);
		
		
		Integer product_id = orderdetail.getProduct().getId();		
		Product product = productRepository.findById(product_id).get();
		product.setId(product.getId());
		productRepository.save(product);

	}

	@Transactional
	public void updateOrderDetail(OrderDetailFormForUpdating form) {

		// convert form to entity
		OrderDetail orderdetail = modelMapper.map(form, OrderDetail.class);

		OrderDetail ord = repository.findById(form.getId()).get();
		ord.setQuantity(orderdetail.getQuantity());
		ord.setPrice(orderdetail.getPrice());
		ord.setTotal_price(orderdetail.getQuantity() * orderdetail.getPrice());
		repository.save(ord);	
	}

}
