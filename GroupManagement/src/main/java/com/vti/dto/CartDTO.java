package com.vti.dto;
import java.util.Date;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartDTO extends RepresentationModel<CartDTO> {
	private int id;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date created_at;
	
	private int user_id;
	
	private String username;
	
	private List<CartItemDTO> cartItems;
	
	@Data
	@NoArgsConstructor
	public static class CartItemDTO extends RepresentationModel<CartDTO>{
		private short id;
		
		private short quantity;
		
	}
}
