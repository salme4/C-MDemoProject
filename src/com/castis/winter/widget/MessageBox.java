package com.castis.winter.widget;

import java.awt.Label;

import org.ocap.ui.event.OCRcEvent;

import com.castis.winter.event.Event;
import com.castis.winter.event.KeyEvent;
import com.castis.winter.navigator.NavigatorController;
import com.castis.winter.navigator.NavigatorModel;
import com.castis.winter.navigator.NavigatorModelData;
import com.castis.winter.navigator.NavigatorView;
import com.castis.winter.util.Logger;
import com.castis.winter.window.WComposite;

public class MessageBox {

    protected MessageBoxView messageBoxView;
    protected MessageBoxModel messageBoxModel;

    public MessageBox() {
    }

    public MessageBox(WComposite composite) {
	messageBoxModel = new MessageBoxModel(null);
	messageBoxView = new MessageBoxView(composite, messageBoxModel);
	addListener();
    }

    /*public void create(DialogParam dialogParam) {
	create(dialogParam, null);
    }*/

//    public void create(DialogParam dialogParam, DialogParam subDialogParam) {
//	messageBoxModel.initData();
//	if (dialogParam.buttons != null)
//	    messageBoxModel.setNavigatorData(new NavigatorModelData(dialogParam.buttons));
//	messageBoxModel.setDialogParam(dialogParam);
//	if (subDialogParam != null)
//	    messageBoxModel.setSubDialogParam(subDialogParam);
//	else
//	    messageBoxModel.setSubDialogParam(null);
//
//	if (dialogParam.sizeType == SIZE_TYPE.H209) {
//	    messageBoxView.setBounds(193, 121, 334, 209);
//	} else if (dialogParam.sizeType == SIZE_TYPE.H269) {
//	    messageBoxView.setBounds(193, 106, 334, 269);
//	} else if (dialogParam.sizeType == SIZE_TYPE.H302) {
//	    messageBoxView.setBounds(169, 89, 382, 302);
//	}
//	
//    }
    
//    public void update(DialogParam dialogParam){
//	messageBoxModel.setDialogParam(dialogParam);
//    }

    private void addListener() {
	buttonController = new MessageBoxViewController(messageBoxView);
	messageBoxView.addKeyListener(buttonController);
    }

    public Label open() throws InterruptedException{
	Label returnButton = null;
	show();
	returnButton = handleDialogButtons();
	close();
	return returnButton;
    }

    public void show() {
	setEnabled(true);
	setVisible(true);
    }

    public void close() {
	setEnabled(false);
	setVisible(false);
    }

    public void setVisible(boolean visible) {
	messageBoxView.setVisible(visible);
    }

    public void setEnabled(boolean enabled) {
	messageBoxView.setEnabled(enabled);
    }
    
    public boolean getVisible() {
	return messageBoxView.isVisible();
    }
    
    MessageBoxViewController buttonController;

    private Label handleDialogButtons() throws InterruptedException {
	
	synchronized (messageBoxView) {
	    messageBoxView.wait();
	}
	
	return selectedButton;
    }
    private Label selectedButton = null;

    private final class MessageBoxViewController extends NavigatorController {

	final NavigatorView view;

	MessageBoxViewController(NavigatorView view) {
	    super(view);
	    this.view = view;
	}

	// 메시지막에서 라벨이 없는 팝업일 때 처리는 어떻게 할것 인가?
	public void keyPressed(KeyEvent e) {
	    Logger.I(this, "e.keyCode :: " + e.keyCode);
	    synchronized (view) {
		switch (e.keyCode) {
		case OCRcEvent.VK_LEFT:
		    processNavigation(NavigatorModel.LEFT);
		    break;
		case OCRcEvent.VK_RIGHT:
		    processNavigation(NavigatorModel.RIGHT);
		    break;
		case OCRcEvent.VK_ENTER:
		    selectedButton = (Label) model.getCurrentObject();
		    view.notifyAll();
		    break;
		case OCRcEvent.VK_LAST:
		case OCRcEvent.VK_EXIT:
		    //selectedButton = Label.NONE;
		    view.notifyAll();
		    break;
		}
		e.wComponent.repaint();
	    }
	}

	public void keyReleased(KeyEvent e) {
	}

    }

    private class MessageBoxTimer extends Thread {
	private Thread myself = null;
	private final int sleepTimer = 2000;

	public void timerStart() {
	    timerStop();
	    myself = new Thread(this, "MessageBox Timer Thread");
	    myself.start();
	}

	public void timerStop() {
	    Logger.I(this, "MessageBox Timer Stop");
	    if (myself != null && myself.isAlive()) {
		myself.interrupt();
	    }
	    myself = null;
	}

	public void run() {
	    try {
		Logger.I(this, "MessageBox Timer run");
		Thread.sleep(sleepTimer);
		Event event = new Event();
		event.wComponent = messageBoxView;
		event.keyCode = OCRcEvent.VK_LAST;
		buttonController.keyPressed(new KeyEvent(event));
	    } catch (InterruptedException e) {
		Logger.I(this, "PMessageBox Timer interrupted");
	    }
	}
    }
}
