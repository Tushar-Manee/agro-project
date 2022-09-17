package com.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@NoArgsConstructor
public class SubCategoryRequest {

	/*
	 * "subCatgName":"rice", "description":"good quality of rice", "category":1,
	 * "totalStock": 2000, "subCatgImage":""
	 */
	private String subCatgName;
	
	private String description;
	
	//@NonNull
	private long categoryId;
	
	private long totalStock;
	
	private byte[] subCatgImage;
	
}
