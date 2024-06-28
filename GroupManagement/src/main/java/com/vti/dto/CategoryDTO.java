package com.vti.dto;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.vti.entity.Product;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDTO extends RepresentationModel<CategoryDTO> {
	private int id;
	
	private String name;
	
	private String description;
	
	private List<Product> products;
	
	@Data
	@NoArgsConstructor
	public static class ProductDTO extends RepresentationModel<CategoryDTO>{
		private short id;
		
		private String name;
		
	}

	
}