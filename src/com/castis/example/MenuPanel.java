package com.castis.example;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {
	MenuModel model = new MenuModel();
	private ArrayList<String> arrayTitle = model.getArrTitle(); 
	private BufferedImage bg_2dep;
	private BufferedImage bg_1dep;
	private BufferedImage logo;
	private BufferedImage bg_2dep_2;
	private BufferedImage bg_listline;
	private BufferedImage main_banner;
	private ImageIcon imgIcon;
	private BufferedImage bg_listline_2;
	private BufferedImage bg_focus_indi;
	private BufferedImage focus_main;
	
	@Override
	protected void paintComponent(Graphics g) {
		int y = 148;
		int y_2 = 222;
		super.paintComponent(g);
		try {
			bg_2dep = ImageIO.read(new File("./resource/image/bg_2dep.png"));
			bg_1dep = ImageIO.read(new File("./resource/image/bg_1dep.png"));
			logo = ImageIO.read(new File("./resource/image/load_01.png"));
			bg_2dep_2 = ImageIO.read(new File("./resource/image/bg_2dep.png"));
			bg_listline = ImageIO.read(new File("./resource/image/bg_listline.png"));
			main_banner = ImageIO.read(new File("./resource/image/bg_banner_fix.png"));
			bg_listline_2 = ImageIO.read(new File("./resource/image/bg_listline_2.png"));
			bg_focus_indi = ImageIO.read(new File("./resource/image/bg_focus_indi.png"));
			focus_main = ImageIO.read(new File("./resource/image/focus_main.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		g.drawImage(bg_2dep, 0, 0, 40, getHeight(), this);
		g.drawImage(bg_1dep, 40, 0, 207, getHeight(), this);
		g.drawImage(logo, 40, 0, 207, 100, this);
		g.drawImage(bg_2dep_2, 247, 0, 207, getHeight(), this);
		for (int i = 0; i < 7; i++) {
			g.drawImage(bg_listline, 40, y, 207, getHeight(), this);
			y+=42;
		}
		g.drawImage(main_banner, 256, 94, 189, 90, this);
		for (int i = 0; i < 5; i++) {
			g.drawImage(bg_listline_2, 247, y_2, 207, getHeight(), this);
			y_2+=42;
		}
		/*
		 * drawString 부분
		 */
		g.setColor(Color.white);
		g.setFont(new Font("HY중고딕", Font.PLAIN, 18));
		g.drawString(model.getDateString(), 266, 85);
		g.drawImage(focus_main, 40, 100, 207, 35, this);    //초기 포커스 주기
		g.drawImage(bg_focus_indi, 226, 103, 17, 31, this);
		drawMenuString(g);                					//메뉴 글씨 그리기
		
	}
	
	
	
	public Graphics drawMenuString(Graphics g){
		int y = 123;
		for (int i = 0; i < model.getViewEndIndex(); i++) {
			g.drawString(arrayTitle.get(i), 63, y);
			y+=42;
		}
		return g;
	}
	
}
