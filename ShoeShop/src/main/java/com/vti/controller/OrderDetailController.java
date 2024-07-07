package com.vti.controller;

import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.modelmapper.ModelMapper;

import com.vti.form.OrderDetailFormForCreating;
import com.vti.form.OrderDetailFormForUpdating;
import com.vti.dto.OrderDetailDTO;
import com.vti.entity.OrderDetail;
import com.vti.service.IOrderDetailService;


@RestController
@RequestMapping(value = "api/v1/orderdetails")
public class OrderDetailController {

	@Autowired
	private IOrderDetailService service;
	
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping()
	public Page<OrderDetailDTO> getAllOrderDetails(Pageable pageable, @RequestParam(required = false) String search) {
		Page<OrderDetail> entityPages = service.getAllOrderDetails(pageable, search);

		// convert entities --> dtos
				List<OrderDetailDTO> dtos = modelMapper.map(entityPages.getContent(), new TypeToken<List<OrderDetailDTO>>() {
				}.getType());

				Page<OrderDetailDTO> dtoPages = new PageImpl<>(dtos, pageable, entityPages.getTotalElements());

				return dtoPages;
	}

	@PostMapping()
	public void createOrderDetail(@RequestBody OrderDetailFormForCreating form) {
		service.createOrderDetail(form);
	}

	@GetMapping(value = "/{id}")
	public OrderDetailDTO getOrderDetailByID(@PathVariable(name = "id") int id) {
		OrderDetail entity = service.getOrderDetailByID(id);

		// convert entity to dto
		OrderDetailDTO dto = modelMapper.map(entity, OrderDetailDTO.class);

		dto.add(linkTo(methodOn(OrderDetailController.class).getOrderDetailByID(id)).withSelfRel());

		return dto;
	}

	@PutMapping(value = "/{id}")
	public void updateOrderDetail(@PathVariable(name = "id") int id, @RequestBody OrderDetailFormForUpdating form) {
		form.setId(id);
		service.updateOrderDetail(form);
	}

}
