package com.vti.dto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class OrderStatusDTO {
	private String orderValue;
	
	private String orderStatusName;

	public OrderStatusDTO(String orderValue, String orderStatusName) {
		super();
		this.orderValue = orderValue;
		this.orderStatusName = orderStatusName;
	}

}
