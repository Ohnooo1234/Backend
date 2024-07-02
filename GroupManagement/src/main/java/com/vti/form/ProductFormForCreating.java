package com.vti.form;

import com.vti.entity.Product;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductFormForCreating {
	
	private String categoryname;
	
	private String name;
	
	private int number_of_products;
	
	private float price;
	
	private String thumbnailUrl;
	
	private String description;

}
