package com.vti.dto;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartItemDTO extends RepresentationModel<CartItemDTO> {
	private int id;
	
	private int quantity;
	
	private int product_id;
	
	private int cart_id;
	
}
