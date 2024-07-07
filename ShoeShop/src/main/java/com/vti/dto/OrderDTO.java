package com.vti.dto;
import java.util.Date;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vti.entity.OrderDetail;
import com.vti.entity.Transaction;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDTO extends RepresentationModel<OrderDTO> {
	private int id;
	
	private String address;
	
	private String orderstatus;
	
	private int total_amount;
	
	private int user_id;
	
	private List<OrderDetailDTO> orderDetails;
	
	@Data
	@NoArgsConstructor
	public static class OrderDetailDTO extends RepresentationModel<OrderDTO> {	
		private int id;
	}
	
	private List<TransactionDTO> transactions;
	
	@Data
	@NoArgsConstructor
	public static class TransactionDTO extends RepresentationModel<OrderDTO> {
		private int id;
	}
	
	
}
