package com.vti.form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartItemFormForUpdating {
	private int id;

//	private int cart_id;
	
	private int product_id;
	
	private int quantity;

}
