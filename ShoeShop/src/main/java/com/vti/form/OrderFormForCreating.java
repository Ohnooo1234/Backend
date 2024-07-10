package com.vti.form;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

public class OrderFormForCreating {
	private int user_id;
	
	private String address;
	
	private String status;
	
	private List<OrderDetail> orderdetails;
	
	@Data
	@NoArgsConstructor
	public static class OrderDetail {
		private Integer id;	
	}

}
