package com.castis.example;

import java.util.ArrayList;

public class menu2Model {
	private ArrayList<String> menuString;
	private Category parent;
	private Category[] subCategory;
	private MenuModel model = new MenuModel();
	
	public menu2Model() {
		menuString.add("서브메뉴1");
		menuString.add("서브메뉴2");
		menuString.add("서브메뉴3");
		menuString.add("서브메뉴4");
		menuString.add("서브메뉴5");
		menuString.add("서브메뉴6");
		menuString.add("서브메뉴7");
		menuString.add("서브메뉴8");
		menuString.add("서브메뉴9");
		menuString.add("서브메뉴10");
		menuString.add("서브메뉴11");
		menuString.add("서브메뉴12");
		menuString.add("서브메뉴13");
		
		parent = model.getRoot();
		
		
//		subCategory = new Category[menuString.size()];
//		for (int i = 0; i < menuString.size(); i++) {
//			subCategory[i] = new Category(menuString.get(i), String.valueOf(i));
//		}
//		parent.setSubCategory(subCategory);
	}
}
