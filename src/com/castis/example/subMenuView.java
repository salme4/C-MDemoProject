package com.castis.example;

import java.awt.Graphics;

import org.ocap.ui.event.OCRcEvent;

import com.castis.winter.event.KeyEvent;
import com.castis.winter.navigator.NavigatorController;
import com.castis.winter.navigator.NavigatorModel;
import com.castis.winter.navigator.NavigatorView;
import com.castis.winter.window.WComposite;

public class subMenuView extends NavigatorView{

	public subMenuView(NavigatorModel model) {
		super(model);
	}

	@Override
	public void initialize() {
		if(controller == null){
			controller = new subMenuController(this);
		}
		this.addKeyListener(controller);
	}

	@Override
	public void drawPaint(Graphics g) {
		
	}

	
	public class subMenuController extends NavigatorController{

		public subMenuController(NavigatorView view) {
			super(view);
		}

		public void keyPressed(KeyEvent e) {
			switch (e.keyCode) {
			case OCRcEvent.VK_DOWN:
				processNavigation(NavigatorModel.DOWN);
				break;
			case OCRcEvent.VK_UP:
				processNavigation(NavigatorModel.UP);
				break;
			}
			e.wComponent.repaint();
		}

		public void keyReleased(KeyEvent e) {
		}
		
	}
}
