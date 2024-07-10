package com.vti.dto;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDetailDTO extends RepresentationModel<OrderDetailDTO> {
	private int id;
	
	private int quantity;
	
	private int product_id;
	
	private int price;
	
	private int order_id;
	
	private float total_price = quantity * price;

}
