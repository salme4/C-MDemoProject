package com.castis.example;

public class Menu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//MenuFrame menuFrame = new MenuFrame();
		MenuModel menuModel = new MenuModel();
		MenuFrame menuFrame = new MenuFrame();
		menuModel.registerObserver(menuFrame);
		MenuController menuController = new MenuController(menuFrame, menuModel);
	}
}
