package com.castis.example;

import java.awt.Graphics;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuFrame extends JFrame{
	public MenuFrame() {
		setTitle("메뉴 프레임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(950, 685);
		MenuPanel panel = new MenuPanel();
		MenuController controller = new MenuController();
		panel.addKeyListener(controller);
		add(panel);
		
		setVisible(true);
	}
}
