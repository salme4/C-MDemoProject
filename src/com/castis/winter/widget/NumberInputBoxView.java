package com.castis.winter.widget;

import java.awt.Graphics;

import org.ocap.ui.event.OCRcEvent;

import com.castis.winter.event.KeyEvent;
import com.castis.winter.window.ImageResource;
import com.castis.winter.window.WComposite;

public class NumberInputBoxView /*extends NumberInputView*/ {

//    public NumberInputBoxView(WComposite parent, NumberInputModel model) {
//	super(parent, model);
//    }

    public void initialize() {
//	if (controller == null) {
//	    controller = new NumberController(this);
//	}
//	this.addKeyListener(controller);
    }

    public void drawPaint(Graphics g) {
//	super.drawPaint(g);
//	if (model.getFocusState() == NumberInputModel.PASSWORD)
//	    g.drawImage(ImageResource.getImage("pop_input_bar_01.png"), ((NumberInputModel) model).getLocationX() + 56 + (model.getIndex() * 19),
//		    ((NumberInputModel) model).getLocationY() + 9, 2, 17, this);
    }

    public void paint(Graphics g) {
	drawPaint(g);
    }

//    private final class NumberController extends NumberInputController {
//	public NumberController(NumberInputView var) {
//	    super(var);
//	}

//	public void keyPressed(KeyEvent e) {
//	    switch (e.keyCode) {
//	    case OCRcEvent.VK_UP:
//		break;
//	    case OCRcEvent.VK_DOWN:
//		sendEnterEvent();
//		break;
//	    default:
//		super.keyPressed(e);
//		break;
//	    }
//	    e.wComponent.repaint();
//	}
//
//	public void keyReleased(KeyEvent e) {
//	}
//
//    }
}
