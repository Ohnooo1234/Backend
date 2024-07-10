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
import com.vti.form.OrderFormForCreating;
import com.vti.form.OrderFormForUpdating;
import com.vti.dto.OrderDTO;
import com.vti.dto.OrderDetailDTO;
import com.vti.entity.Order;
import com.vti.entity.OrderDetail;
import com.vti.service.IOrderDetailService;
import com.vti.service.IOrderService;


@RestController
@RequestMapping(value = "api/v1/orders")
public class OrderController {

	@Autowired
	private IOrderService service;
	
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping()
    public Page<OrderDTO> getAllOrders(Pageable pageable, @RequestParam(required = false) String search) {
        Page<Order> entityPages = service.getAllOrders(pageable, search);

        // convert entities --> dtos
        List<OrderDTO> dtos = modelMapper.map(entityPages.getContent(), new TypeToken<List<OrderDTO>>() {
        }.getType());

        Page<OrderDTO> dtoPages = new PageImpl<>(dtos, pageable, entityPages.getTotalElements());

        return dtoPages;
    }

	@PostMapping()
	public void createOrder(@RequestBody OrderFormForCreating form) {
		service.createOrder(form);
	}

	@GetMapping(value = "/{id}")
	public OrderDTO getOrderByID(@PathVariable(name = "id") int id) {
		Order entity = service.getOrderByID(id);

		// convert entity to dto
		OrderDTO dto = modelMapper.map(entity, OrderDTO.class);

		dto.add(linkTo(methodOn(OrderController.class).getOrderByID(id)).withSelfRel());

		return dto;
	}

	@PutMapping(value = "/{id}")
	public void updateOrder(@PathVariable(name = "id") int id, @RequestBody OrderFormForUpdating form) {
		form.setId(id);
		service.updateOrder(form);
	}

}
