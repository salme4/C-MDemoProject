package com.castis.winter.widget;

import java.awt.Label;

import org.ocap.ui.event.OCRcEvent;

import com.castis.winter.Winter.EventType;
import com.castis.winter.event.ActionListener;
import com.castis.winter.event.Event;
import com.castis.winter.event.KeyEvent;
import com.castis.winter.navigator.NavigatorController;
import com.castis.winter.navigator.NavigatorModel;
import com.castis.winter.navigator.NavigatorView;
import com.castis.winter.util.Logger;
import com.castis.winter.window.WComposite;

public class NumberInputBox extends MessageBox {
    protected NumberInputBoxView inputBoxView;
    //protected NumberInputModel inputBoxModel;

//    public NumberInputBox(WComposite composite, NumberInputModel inputModel) {
//	super();
//	inputBoxModel = inputModel;
//	inputBoxView = new NumberInputBoxView(composite, inputBoxModel);
//	inputBoxView.initialize();
//	messageBoxModel = new MessageBoxModel(null);
//	messageBoxView = new MessageBoxView(composite, messageBoxModel);
//	addListener();
//    }

//    public void create(DialogParam dialogParam) {
//	create(dialogParam, null);
//    }
//
//    public void create(DialogParam dialogParam, DialogParam subDialogParam) {
//	super.create(dialogParam, subDialogParam);
//
//	inputBoxModel.init(false);
//	if (dialogParam.sizeType == SIZE_TYPE.H209) {
//	    inputBoxView.setBounds(272, 240, 183, 35);
//	} else if (dialogParam.sizeType == SIZE_TYPE.H269) {
//	    inputBoxView.setBounds(272, 275, 183, 35);
//	} else if (dialogParam.sizeType == SIZE_TYPE.H302) {
//	}
//    }
//
//    private void addListener() {
//	inputBoxView.addActionListener(new InputBoxViewController());
//	MessageBoxViewController messageBoxViewController = new MessageBoxViewController(messageBoxView);
//	messageBoxView.addKeyListener(messageBoxViewController);
//	messageBoxView.addActionListener(messageBoxViewController);
//    }
//
//    public void show() {
//	close();
//	inputBoxView.setEnabled(true);
//	setVisible(true);
//    }

    public Label open() throws InterruptedException {
	Label returnButton = null;
	show();
	returnButton = handleDialogButtons();
	close();
	return returnButton;
    }

    public void close() {
	setVisible(false);
	//inputBoxView.setEnabled(false);
	messageBoxView.setEnabled(false);
    }

    public void setVisible(boolean visible) {
	messageBoxView.setVisible(visible);
	//inputBoxView.setVisible(visible);
    }

    private Label handleDialogButtons() throws InterruptedException {
	synchronized (messageBoxView) {
	    messageBoxView.wait();
	}

	return selectedButton;
    }

    private Label selectedButton = null;

    private final class InputBoxViewController implements ActionListener {
	public void occurredEvent(Event e) {
	    Logger.I(this, "occurredEvent : " + e.type);
	    synchronized (messageBoxView) {
		switch (e.type) {
		case EventType.NumberFull: {
//		    // messageBoxModel.getDialogParam().buttonEnable = true;
//		    messageBoxModel.setCurrentFocus(0);
//		    inputBoxModel.setFocusState(NumberInputModel.BUTTON);
//		    inputBoxView.setEnabled(false);
//		    if (inputBoxModel.isFull()) {
//			messageBoxModel.setCurrentFocus(0);
//		    } else {
//			messageBoxModel.setCurrentFocus(1);
//		    }
		    messageBoxView.setEnabled(true);
		    messageBoxView.repaint();
		    break;
		}
		case EventType.Enter: {
//		    if (inputBoxModel.isFull()) {
//			messageBoxModel.setCurrentFocus(0);
//		    } else {
//			messageBoxModel.setCurrentFocus(1);
//		    }
//		    inputBoxModel.setFocusState(NumberInputModel.BUTTON);
//		    inputBoxView.setEnabled(false);
		    messageBoxView.setEnabled(true);
		    messageBoxView.repaint();
		    break;
		}
		case EventType.Back:
		case EventType.Exit: {
//		    selectedButton = Label.NONE;
		    messageBoxView.notifyAll();
		    break;
		}
		}
	    }
	}
    }

    private final class MessageBoxViewController extends NavigatorController implements ActionListener {

	final NavigatorView view;

	MessageBoxViewController(NavigatorView view) {
	    super(view);
	    this.view = view;
	}

	public void keyPressed(KeyEvent e) {
	    synchronized (view) {
		switch (e.keyCode) {
		case OCRcEvent.VK_0:
		case OCRcEvent.VK_1:
		case OCRcEvent.VK_2:
		case OCRcEvent.VK_3:
		case OCRcEvent.VK_4:
		case OCRcEvent.VK_5:
		case OCRcEvent.VK_6:
		case OCRcEvent.VK_7:
		case OCRcEvent.VK_8:
		case OCRcEvent.VK_9:
		    sendActionEvent(EventType.Fake);
		    break;

		case OCRcEvent.VK_LEFT:
//		    if (inputBoxModel.isFull())
//			processNavigation(NavigatorModel.LEFT);
		    break;
		case OCRcEvent.VK_RIGHT:
//		    if (inputBoxModel.isFull())
//			processNavigation(NavigatorModel.RIGHT);
		    break;
		case OCRcEvent.VK_ENTER:
		    selectedButton = (Label) model.getCurrentObject();
//		    if (selectedButton.equals(Label.OK)) {
////			if (inputBoxModel.isFull() && EpgAgent.getInstance().checkPinCode(inputBoxModel.getStrValue())) {
////			    view.notifyAll();
////			} else {
////			    inputBoxModel.init(false);
////			    if (messageBoxModel.getDialogParam() == DialogRepository.SEENLIST_ADULT_AUTHENTICATION)
////				messageBoxModel.setDialogParam(DialogRepository.SEENLIST_ADULT_AUTHENTICATION_FAIL);
////			    else if (messageBoxModel.getDialogParam() == DialogRepository.PURCHASELIST_ADULT_AUTHENTICATION)
////				messageBoxModel.setDialogParam(DialogRepository.PURCHASELIST_ADULT_AUTHENTICATION_FAIL);
////			    else if (messageBoxModel.getDialogParam() == DialogRepository.RATING)
////				messageBoxModel.setDialogParam(DialogRepository.RATING_FAIL);
////			    messageBoxModel.setCurrentFocus(1);
////			    inputBoxModel.setFocusState(NumberInputModel.PASSWORD);
////			    inputBoxView.setEnabled(true);
////			    messageBoxView.setEnabled(false);
////			}
////		    } else {
////			view.notifyAll();
////		    }
//		    break;

		case OCRcEvent.VK_UP:
		    sendActionEvent(EventType.FocusInputBox);
		    break;
		case OCRcEvent.VK_LAST:
		case OCRcEvent.VK_BACK:
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

	public void occurredEvent(Event e) {
	    switch (e.type) {
	    case EventType.FocusInputBox: {
//		inputBoxModel.setFocusState(NumberInputModel.PASSWORD);
//		inputBoxModel.initValue();
//		inputBoxView.setEnabled(true);
		messageBoxView.setEnabled(false);
		break;
	    }
	    }
	}
    }

}
