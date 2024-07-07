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

import com.vti.form.CartItemFormForCreating;
import com.vti.form.CartItemFormForUpdating;
import com.vti.dto.CartItemDTO;
import com.vti.dto.filter.CartItemFilter;
import com.vti.entity.CartItem;
import com.vti.service.ICartItemService;


@RestController
@RequestMapping(value = "api/v1/cartitems")
public class CartItemController {

	@Autowired
	private ICartItemService service;
	
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping()
	public Page<CartItemDTO> getAllCartItems(Pageable pageable,CartItemFilter filter, @RequestParam(required = false) String search) {
		Page<CartItem> entityPages = service.getAllCartItems(pageable, filter, search);

		// convert entities --> dtos
				List<CartItemDTO> dtos = modelMapper.map(entityPages.getContent(), new TypeToken<List<CartItemDTO>>() {
				}.getType());

				Page<CartItemDTO> dtoPages = new PageImpl<>(dtos, pageable, entityPages.getTotalElements());

				return dtoPages;
	}

	@PostMapping()
	public void createCartItem(@RequestBody CartItemFormForCreating form) {
		service.createCartItem(form);
	}

	@GetMapping(value = "/{id}")
	public CartItemDTO getCartItemByID(@PathVariable(name = "id") int id) {
		CartItem entity = service.getCartItemByID(id);

		// convert entity to dto
		CartItemDTO dto = modelMapper.map(entity, CartItemDTO.class);

		dto.add(linkTo(methodOn(CartItemController.class).getCartItemByID(id)).withSelfRel());

		return dto;
	}

	@PutMapping(value = "/{id}")
	public void updateCartItem(@PathVariable(name = "id") int id, @RequestBody CartItemFormForUpdating form) {
		form.setId(id);
		service.updateCartItem(form);
	}

}