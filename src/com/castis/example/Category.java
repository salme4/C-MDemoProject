package com.castis.example;

import org.json.me.JSONException;
import org.json.me.JSONObject;


public class Category {
	private Category[] subCategory;
	private JSONObject item;
	
	public void setItem(JSONObject item){
		this.item = item;
	}
	
	public JSONObject getItem(){
		return item;
	}
	
	public Category getCategoryAt(int i){
		return this.subCategory[i];
	}
	
	public String getItemName() throws JSONException {
		return (String)item.getString("categoryName");
	
	}
	
	public String getid() throws JSONException {
		return (String)item.getString("categoryId");
	}
	
	public String getParentId() throws JSONException{
		return (String)item.getString("parentCategoryId");
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
