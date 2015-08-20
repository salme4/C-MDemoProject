package com.castis.example;

import javax.swing.JFrame;

public class MenuFrame extends JFrame{
	public MenuFrame() {
		setTitle("메뉴 프레임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(950, 685);
		MenuPanel panel = new MenuPanel();
		add(panel);
		
		setVisible(true);
	}
}
