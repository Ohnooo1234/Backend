package com.vti.dto;
import java.util.Date;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartItemDTO extends RepresentationModel<CartItemDTO> {
	private int id;
	
	private int quantity;
	
	private int product_id;
	
	private String productName;
	
	private String cart_id;
	
}
