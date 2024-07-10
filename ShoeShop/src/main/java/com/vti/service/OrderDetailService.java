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
	    // Convert form to entity
	    OrderDetail orderDetailEntity = modelMapper.map(form, OrderDetail.class);

	    // Create orderdetail
	    OrderDetail savedOrderDetail = repository.save(orderDetailEntity);

	    // Calculate and set the total price
	    savedOrderDetail.setTotal_price(savedOrderDetail.getQuantity() * savedOrderDetail.getProduct().getPrice());
	    repository.save(savedOrderDetail);

	    // Update the product's number of products
	    Product product = savedOrderDetail.getProduct();
	    product.setNumber_of_products(product.getNumber_of_products() - savedOrderDetail.getQuantity());
	    productRepository.save(product);
	}


	@Transactional
	public void updateOrderDetail(OrderDetailFormForUpdating form) {
	    // Convert form to entity
	    OrderDetail orderDetail = modelMapper.map(form, OrderDetail.class);

	    // Get the existing OrderDetail
	    OrderDetail existingOrderDetail = repository.findById(orderDetail.getId()).get();

	    // Update the product's number of products
	    Product oldProduct = existingOrderDetail.getProduct();
	    oldProduct.setNumber_of_products(oldProduct.getNumber_of_products() + existingOrderDetail.getQuantity());
	    productRepository.save(oldProduct);

	    Product newProduct = productRepository.findById(form.getProduct_id()).get();
	    newProduct.setNumber_of_products(newProduct.getNumber_of_products() - orderDetail.getQuantity());
	    productRepository.save(newProduct);

	    // Save the updated OrderDetail
	    orderDetail.setTotal_price(orderDetail.getQuantity() * newProduct.getPrice());
	    repository.save(orderDetail);
	}


}
