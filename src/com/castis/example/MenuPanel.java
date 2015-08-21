package com.castis.example;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
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
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuPanel extends JPanel{
	MenuModel model;
	private ArrayList<String> arrayTitle;
	private BufferedImage bg_2dep;
	private BufferedImage bg_1dep;
	private BufferedImage logo;
	private BufferedImage bg_2dep_2;
	private BufferedImage bg_listline;
	private BufferedImage main_banner;
	private BufferedImage bg_listline_2;
	private BufferedImage bg_focus_indi;
	private BufferedImage focus_main;
	private int[] focusPosition;
	private int[] indiPosition;
	private int currentIndex;
	private int startIndex;
	private int endIndex;
	
	public int getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}

	public MenuPanel(MenuModel model) {
		this.model = model;
		this.setFocusable(true);
		//this.setBounds(new Rectangle(0, 0, 960, 540));
		this.requestFocusInWindow();
		arrayTitle = model.getArrTitle();
		focusPosition = model.getFocusPosition();
		indiPosition = model.getIndiPosition();
	}
	
	@Override
	public void paint(Graphics g) {
		int y = 148;
		int y_2 = 222;
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

		g.setColor(Color.white);
		g.setFont(new Font("HY중고딕", Font.PLAIN, 18));
		g.drawString(model.getDateString(), 266, 85);
		System.out.println("cur : " + currentIndex);
		drawMenuString(g, currentIndex);                					//메뉴 글씨 그리기
	}
	
	public void drawMenuString(Graphics g, int currentIndex){
		int y = 123;
		/*
		 *  보여줄 item보다 데이터가 적을 경우 처리
		 */
		if(model.getViewEndIndex() > model.getEndIndex()){
			endIndex = model.getEndIndex();
		}else{
			endIndex = model.getViewEndIndex();
		}
		
		/*
		 * current가 viewEndIndex를 넘을때
		 * current가 viewStartIndex보다 작을 때
		 * 
		 */
		
		//포커스인가?painting
		for (int i = startIndex; i <= endIndex; i++) {
			if(currentIndex == i){
				int focusing = currentIndex;
				if (focusing > model.getViewEndIndex()){
					focusing = model.getViewEndIndex();
				}
				g.setFont(new Font("HY중고딕", Font.PLAIN, 19));
				g.drawImage(focus_main, 40, focusPosition[focusing], 207, 35, this);    //초기 포커스 주기
				g.drawImage(bg_focus_indi, 226, indiPosition[focusing], 17, 31, this);
				g.drawString(arrayTitle.get(i), 63, y);
			}else{
				g.setFont(new Font("HY중고딕", Font.PLAIN, 18));
			}
			g.drawString(arrayTitle.get(i), 63, y);
			y+=42;
		}
	}
}
