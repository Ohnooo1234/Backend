package com.vti.dto;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartDTO extends RepresentationModel<CartDTO> {
	private int id;
	
	private String user_id;
	
	private List<CartItemDTO> cartItems;
	
	@Data
	@NoArgsConstructor
	public static class CartItemDTO extends RepresentationModel<CartDTO>{
		private short id;
		
	}
}
