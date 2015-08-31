package com.castis.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class Category {
	private Category[] subCategory;
	private String name;
	private String id;
	private JSONObject item;
	
	public void setItem(JSONObject item){
		this.item = item;
	}
	
	public JSONObject getItem(){
		return item;
	}
	
	
	public String getName() {
		return name;
	}
	
	public Category getCategoryAt(int i){
		return this.subCategory[i];
	}
	
	public String getItemName(){
		return (String)item.get("categoryName");
	}
	
//	public String getItemNameWithParent(){
//		JSONArray jArray = (JSONArray)item.get(0);
//		return null;
//	}
	
	public String getid() {
		return (String)item.get("categoryId");
	}
	
	public String getParentId(){
		return (String)item.get("parentCategoryId");
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
