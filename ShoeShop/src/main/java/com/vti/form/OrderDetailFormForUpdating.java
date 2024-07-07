package com.vti.form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDetailFormForUpdating {
	private int id; 
	
	private int order_id;
	
	private int product_id;
	
	private int quantity;
	
	private float price;
	
	private float total_price = quantity * price;

}