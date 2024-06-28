package com.vti.form;

import com.vti.entity.Category;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryFormForCreating {

	private String name;
	
	private String description;

	public Category toEntity() {
		return new Category(name, description);
	}

}
