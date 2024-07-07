package com.vti.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.vti.dto.filter.ProductFilter;
import com.vti.entity.Product;

public class ProductSpecificationBuilder {

	private ProductFilter filter;
	private String search;

	public ProductSpecificationBuilder(ProductFilter filter, String search) {
		this.filter = filter;
		this.search = search;
	}

	@SuppressWarnings("deprecation")
	public Specification<Product> build() {

		SearchCriteria seachCriteria = new SearchCriteria("name", "Like", search);
		SearchCriteria minPriceCriteria = new SearchCriteria("price", ">=", filter.getMinPrice());
		SearchCriteria maxPriceCriteria = new SearchCriteria("price", "<=", filter.getMaxPrice());

		Specification<Product> where = null;

		// search
		if (!StringUtils.isEmpty(search)) {
			where = new ProductSpecification(seachCriteria);
		}

		// min Price filter
		if (filter.getMinPrice() != 0) {
			if (where != null) {
				where = where.and(new ProductSpecification(minPriceCriteria));
			} else {
				where = new ProductSpecification(minPriceCriteria);
			}
		}

		// max Price filter
		if (filter.getMaxPrice() != 0) {
			if (where != null) {
				where = where.and(new ProductSpecification(maxPriceCriteria));
			} else {
				where = new ProductSpecification(maxPriceCriteria);
			}
		}

		return where;
	}
}
