package com.vti.form;

import lombok.Data;

@Data
public class OrderFormForUpdating {
	private int id;
	
	private int user_id;
	
	private String address;
	
	private String status;
	
}
