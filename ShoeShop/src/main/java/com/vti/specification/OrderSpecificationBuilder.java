package com.vti.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import com.vti.entity.Order;

public class OrderSpecificationBuilder {
    private String search;

    public OrderSpecificationBuilder(String search) {
        this.search = search;
    }

    @SuppressWarnings("deprecation")
    public Specification<Order> build() {
        SearchCriteria searchCriteria = new SearchCriteria("id", "Like", search);
        Specification<Order> where = null;

        if (!StringUtils.isEmpty(search)) {
            where = new OrderSpecification(searchCriteria);
        }

        return where;
     

	}
}
