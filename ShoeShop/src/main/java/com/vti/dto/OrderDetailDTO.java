package com.vti.dto;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDetailDTO extends RepresentationModel<OrderDetailDTO> {
	private int id;
	
	private int quantity;
	
	private float price;
	
	private float total_price = quantity * price;
	
	private int product_id;
	
	private int order_id;

}
