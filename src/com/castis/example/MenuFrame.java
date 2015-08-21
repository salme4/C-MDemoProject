package com.castis.example;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuFrame extends JFrame implements Observer{
	MenuModel model = new MenuModel();
	MenuPanel panel = new MenuPanel(model);
	public MenuFrame() {
		setTitle("메뉴 프레임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(950, 685);
		add(panel);
		
		setVisible(true);
	}
	
	public void setListener(KeyListener listener){
		panel.addKeyListener(listener);
	}

	@Override
	public void update(int currentIndex) {
		panel.setCurrentIndex(currentIndex);
		panel.repaint();
	}
}
