package com.castis.example;

public class Menu {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		MenuModel menuModel = new MenuModel();
		menuView menuView = new menuView();
		menuModel.registerObserver(menuView);
		MenuController menuController = new MenuController(menuView, menuModel);
		menuView.setVisible(true);
	}
}
