package com.vti.form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductFormForUpdating {

	private String categoryname;
	
	private String name;
	
	private int number_of_products;
	
	private float price;
	
	private String thumbnailUrl;
	
	private String description;
	
	
}
