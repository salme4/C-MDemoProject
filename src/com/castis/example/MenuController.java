package com.castis.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MenuController implements KeyListener{
	private MenuFrame view;
	private MenuModel model;
	
	public MenuController(MenuFrame menuFrame, MenuModel menuModel) {
		this.view = menuFrame;
		this.model = menuModel;
		view.setListener(this);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (KeyEvent.VK_LEFT == e.getKeyCode()){
			System.out.println("왼쪽");
		}else if(KeyEvent.VK_RIGHT == e.getKeyCode()){
			System.out.println("오른쪽");
		}else if (KeyEvent.VK_DOWN == e.getKeyCode()){
			moveToDown();
		}else if(KeyEvent.VK_UP == e.getKeyCode()){
			moveToUp();
		}else{
			return;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
//		System.out.println("key Released");
	}
	
	public void moveToDown(){
		System.out.println("아래");
		model.pulsCurrentIndex();
		//model.viewPlus();
//		view.repaint(model.getCurrentIndex());
		model.notifyObservers();
	}
	
	public void moveToUp(){
		System.out.println("위");
		model.minusCurrentIndex();
		//model.viewMinus();
		model.notifyObservers();
	}
	
}
