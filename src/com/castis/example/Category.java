package com.castis.example;

public class Category {
	private Category[] subCategory;
	private String name;
	private int id;
	
	public Category[] getSubCategory() {
		return subCategory;
	}
	
	public void setSubCategory(Category[] subCategory) {
		this.subCategory = subCategory;
	}
	
	class subCategory{
		private Category subCategory;
		
	}
}
