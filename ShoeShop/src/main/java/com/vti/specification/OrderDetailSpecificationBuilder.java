package com.vti.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.vti.entity.OrderDetail;

public class OrderDetailSpecificationBuilder {
	private String search;

	public OrderDetailSpecificationBuilder(String search) {
		this.search = search;
	}

	@SuppressWarnings("deprecation")
	public Specification<OrderDetail> build() {

		SearchCriteria seachCriteria = new SearchCriteria("name", "Like", search);
	
		Specification<OrderDetail> where = null;

		// search
		if (!StringUtils.isEmpty(search)) {
			where = new OrderDetailSpecification(seachCriteria);
		}

		return where;
	}
}
