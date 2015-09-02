package com.castis.example;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import org.json.me.JSONException;

public class menuView extends JFrame implements Observer{
	MenuModel model = new MenuModel();
	private Category[] menu;
	private Category[] subMenu;
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
	private int startIndex = 0;
	private int endIndex;
	private int pageSize;
	private int pageCount;
	private int totalCount;
	private int currentPageGroup;
	private Category[] subCategory;
	private Category root;
	
	public menuView() throws Exception{
		setTitle("메뉴 프레임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(950, 685);
		this.setFocusable(true);
		this.requestFocusInWindow();
//		root = model.getRoot();
		menu = model.getCategorys();
		focusPosition = model.getFocusPosition();
		indiPosition = model.getIndiPosition();
		pageSize = model.getPageSize();
		totalCount = model.getEndIndex();
		
		pageCount = (int)((totalCount / pageSize) + ((totalCount % pageSize > 0)?1:0));
		
		bg_2dep = ImageIO.read(new File("./resource/image/bg_2dep.png"));
		bg_1dep = ImageIO.read(new File("./resource/image/bg_1dep.png"));
		logo = ImageIO.read(new File("./resource/image/load_01.png"));
		bg_2dep_2 = ImageIO.read(new File("./resource/image/bg_2dep.png"));
		bg_listline = ImageIO.read(new File("./resource/image/bg_listline.png"));
		main_banner = ImageIO.read(new File("./resource/image/bg_banner_fix.png"));
		bg_listline_2 = ImageIO.read(new File("./resource/image/bg_listline_2.png"));
		bg_focus_indi = ImageIO.read(new File("./resource/image/bg_focus_indi.png"));
		focus_main = ImageIO.read(new File("./resource/image/focus_main.png"));
	}
	
	@Override
	public void paint(Graphics g) {
		int y = 148;
		int y_2 = 222;
		
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
		try{
		drawMenuString(g, currentIndex);
		}catch(Exception e){}
		drawSubMenuString(g, currentIndex);
	}
	
	public void drawSubMenuString(Graphics g, int currenIndex){
//		subMenu = menu[currenIndex].getSubCategory();
//		try {
//			for (int i = 0; i < subMenu.length; i++) {
//				if(!subMenu[i].getItemName().equals("")){
//					g.drawString(subMenu[i].getItemName(), 267, 208 + (i*42));
//				}
//			}
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	
	public void drawMenuString(Graphics g, int currentIndex) throws Exception{
		int y = 123;
		
		currentPageGroup = (int)Math.ceil((double)(currentIndex+1)/pageSize);
		if (currentPageGroup > 1){
			startIndex = (currentPageGroup-1)*pageSize;
			endIndex = startIndex + pageSize - 1;
			if (endIndex > totalCount){
				endIndex = totalCount - 1;
			}
		}else{
			startIndex = 0;
			endIndex = pageSize - 1;
		}
		
		for (int i = startIndex; i <= endIndex; i++) {
			if(currentIndex == i){
				g.setFont(new Font("HY중고딕", Font.PLAIN, 19));
				g.drawImage(focus_main, 40, focusPosition[currentIndex-startIndex], 207, 35, this);    //초기 포커스 주기
				g.drawImage(bg_focus_indi, 226, indiPosition[currentIndex-startIndex], 17, 31, this);
				g.drawString(menu[i].getItemName(), 63, y);
			}else{
				g.setFont(new Font("HY중고딕", Font.PLAIN, 18));
			}
			g.drawString(menu[i].getItemName(), 63, y);
			y+=42;
		}
	}

	public void setListener(KeyListener listener){
		this.addKeyListener(listener);
	}
	
	@Override
	public void update(int currentIndex) {
		this.currentIndex = currentIndex;
//		this.startIndex = model.getStartIndex();
//		this.endIndex = model.getEndIndex();
		this.repaint();
	}

}
