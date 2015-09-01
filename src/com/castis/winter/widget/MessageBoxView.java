package com.castis.winter.widget;

import java.awt.Graphics;

import com.castis.winter.Winter.ColorType;
import com.castis.winter.Winter.FontType;
import com.castis.winter.navigator.NavigatorModel;
import com.castis.winter.navigator.NavigatorView;
import com.castis.winter.window.ImageResource;
import com.castis.winter.window.WComposite;

public class MessageBoxView extends NavigatorView {

    public MessageBoxView(WComposite parent, NavigatorModel model) {
	super(parent, model);
    }

    public void initialize() {
    }
//
//    DialogParam getDialogParam() {
//	if (model != null)
//	    return ((MessageBoxModel) model).getDialogParam();
//	else
//	    return null;
//    }
//
//    DialogParam getSubDialogParam() {
//	if (model != null)
//	    return ((MessageBoxModel) model).getSubDialogParam();
//	else
//	    return null;
//    }
//
//    public void drawPaint(Graphics g) {
//	drawBackground(g);
//	drawIcon(g);
//	drawTitle(g);
//	drawDialog(g);
//	if (getSubDialogParam() != null)
//	    drawSubDialog(g);
//	if (((MessageBoxModel) model).hasButton())
//	    drawButton(g);
//    }
//
//    private void drawBackground(Graphics g) {
//	int bgWidth = this.getBounds().width;
//	int bgHeight = this.getBounds().height;
//
//	g.setColor(ColorType.C245_245_245);
//	g.fillRect(0, 0, bgWidth, bgHeight);
//
//	g.setColor(ColorType.c_172_174_152);
//	g.fillRect(15, 49, bgWidth - 30, 2);
//
//	if (getDialogParam() != null && getDialogParam().hasButton()) {
//	    g.setColor(ColorType.C60_60_52);
//	    g.fillRect(0, bgHeight - 45, bgWidth, 45);
//	}
//    }
//
//    private void drawIcon(Graphics g) {
//	if (getDialogParam() != null)
//	    g.drawImage(ImageResource.getImage(getDialogParam().icon), 15, 9, 40, 37, this);
//    }
//
//    private void drawTitle(Graphics g) {
//	if (getDialogParam() != null && getDialogParam().title != null) {
//	    g.setFont(FontType.F24P);
//	    g.setColor(ColorType.C120_135_0);
//	    g.drawString(getDialogParam().title, 63, 34);
//	}
//    }
//
//    private int getCenterX() {
//	return (this.getBounds().width / 2);
//    }
//
//    private void drawDialog(Graphics g) {
//	if (getDialogParam() != null && getDialogParam().text != null && getDialogParam().textStyle != null) {
//	    StyleText[] styleTextArr = getDialogParam().textStyle;
//	    String[] text = getDialogParam().text;
//	    for (int i = 0; i < styleTextArr.length; i++) {
//		if (getDialogParam().text[i] != null) {
//		    g.setColor(styleTextArr[i].c);
//		    g.setFont(styleTextArr[i].f);
//		    g.drawString(text[i], alignCenter.format(g.getFontMetrics().stringWidth(text[i]), getCenterX()), styleTextArr[i].y);
//		}
//	    }
//	}
//    }
//
//    private void drawSubDialog(Graphics g) {
//	StyleText[] styleTextArr = getSubDialogParam().textStyle;
//	String[] text = getSubDialogParam().text;
//	if (getDialogParam().text != null)
//	    for (int i = 0; i < styleTextArr.length; i++) {
//		if (getDialogParam().text[i] != null) {
//		    g.setColor(styleTextArr[i].c);
//		    g.setFont(styleTextArr[i].f);
//		    g.drawString(text[i], alignCenter.format(g.getFontMetrics().stringWidth(text[i]), getCenterX()), styleTextArr[i].y);
//		}
//	    }
//    }

    private void drawButton(Graphics g) {
	int bgHeight = this.getBounds().height;
	int bgWidth = this.getBounds().width;
	int buttonImagePosY = bgHeight - 37;
	int buttonTextPosY = bgHeight - 17;
	final int buttonCount = model.getTotalItemCount();
	switch (buttonCount) {
	case 1:
	    int buttonWidth = bgWidth - (88 * 2);
	    g.setColor(ColorType.C192_220_0);
	    g.fillRect(88, buttonImagePosY, buttonWidth, 30);

	    g.setFont(FontType.F15P);
	    g.setColor(ColorType.C0_0_0);
	    String text = model.getDisplayStringAt(0);
	    //g.drawString(text, alignCenter.format(g.getFontMetrics().stringWidth(text), getCenterX()), buttonTextPosY);
	    break;
	case 2:
	    for (int i = 0; i < buttonCount; i++) {
		g.setFont(FontType.F15P);
		String buttonText = model.getDisplayStringAt(i);
		if (i == model.getCurrentFocus() && getEnabled()) {
		    g.setColor(ColorType.C192_220_0);
		    g.fillRect(15 + (i * 155), buttonImagePosY, 149, 30);

		    g.setColor(ColorType.C0_0_0);
		} else {
		    g.setColor(ColorType.C39_39_34);
		    g.fillRect(15 + (i * 155), buttonImagePosY, 149, 30);

		    g.setColor(ColorType.C204_210_163);
		}
		g.drawString(buttonText, alignCenter.format(g.getFontMetrics().stringWidth(buttonText), 15 + (i * 155) + (149 / 2)), buttonTextPosY);
	    }
	    break;
	case 3:
	    for (int i = 0; i < buttonCount; i++) {
		g.setFont(FontType.F15P);
		String buttonText = model.getDisplayStringAt(i);
		if (i == model.getCurrentFocus()) {
		    g.setColor(ColorType.C192_220_0);
		    g.fillRect(15 + (i * 103), buttonImagePosY, 98, 30);

		    g.setColor(ColorType.C0_0_0);
		} else {
		    g.setColor(ColorType.C39_39_34);
		    g.fillRect(15 + (i * 103), buttonImagePosY, 98, 30);

		    g.setColor(ColorType.C204_210_163);
		}
		g.drawString(buttonText, alignCenter.format(g.getFontMetrics().stringWidth(buttonText), 15 + (i * 103) + (98 / 2)), buttonTextPosY);

	    }
	    break;
	default:
	    break;
	}
    }

    public void paint(Graphics g) {
	drawPaint(g);
    }

	@Override
	public void drawPaint(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
