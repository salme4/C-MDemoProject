package com.castis.example;

public class Menu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//MenuFrame menuFrame = new MenuFrame();
		MenuModel menuModel = new MenuModel();
		MenuPanel menuPanel = new MenuPanel();
		MenuController menuController = new MenuController(menuPanel, menuModel);
		new MenuFrame();
	}
}
