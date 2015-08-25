package com.castis.example;

public class Category {
	private Category[] subCategory;
	private String name;
	private String id;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Category(String name, String id) {
		this.name = name;
		this.id = id;
	}
	
	public Category() {

	}
	
	public Category[] getSubCategory() {
		return subCategory;
	}
	
	public void setSubCategory(Category[] subCategory) {
		this.subCategory = subCategory;
	}
}
