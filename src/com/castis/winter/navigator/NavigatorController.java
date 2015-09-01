package com.castis.winter.navigator;

import com.castis.winter.Winter.EventType;
import com.castis.winter.event.KeyEvent;
import com.castis.winter.event.KeyListener;
import com.castis.winter.util.Logger;

public abstract class NavigatorController implements KeyListener {
    protected NavigatorView view;
    protected NavigatorModel model;

    public NavigatorController(NavigatorView view) {
	this.view = view;
	this.model = view.model;
    }

    protected int processNavigation(int mode) {
	if (model.getCurrentFocus() == -1) {
	    return -1;
	}
	if (mode == NavigatorModel.UP || mode == NavigatorModel.DOWN || mode == NavigatorModel.LEFT || mode == NavigatorModel.RIGHT) {
	    model.setPreviousFocus(model.getCurrentFocus());
	    model.moveFocus(mode);
	    focusChanged();
	    if (model.getPreviousFocus() == model.getCurrentFocus())
		return 0;
	    int previousFocusLineIndex = model.calculateLineIndex(model.getPreviousFocus());
	    int currentFocusLineIndex = model.calculateLineIndex(model.getCurrentFocus());
	    if (previousFocusLineIndex == currentFocusLineIndex)
		return 0;
	    int pageStartLine = model.getPageStartLine();
	    int previousLineDiff = -1;
	    if (previousFocusLineIndex < pageStartLine) {
		previousLineDiff = previousFocusLineIndex - pageStartLine + model.getTotalLineCount();
	    } else {
		previousLineDiff = previousFocusLineIndex - pageStartLine;
	    }
	    if ((mode == NavigatorModel.DOWN || mode == NavigatorModel.RIGHT)
		    && (previousLineDiff == model.getPageTransitionThresholdToPlus() || (model.getMoveWindowMode() == NavigatorModel.MOVE_WINDOW_PAGEWISE && previousFocusLineIndex == model
			    .getTotalLineCount() - 1))) {
		model.movePage(1);
		if (model.getTotalPageCount() != 1)
		    pageChanged();
		return 0;
	    } else if ((mode == NavigatorModel.UP || mode == NavigatorModel.LEFT) && (previousLineDiff == model.getPageTransitionThresholdToMinus())) {
		model.movePage(-1);
		if (model.getTotalPageCount() != 1)
		    pageChanged();
		return 0;
	    } else {
		return 0;
	    }
	} else if (mode == NavigatorModel.LEFT_JUMP || mode == NavigatorModel.RIGHT_JUMP) {
	    int previousPage = model.getCurrentPage();
	    int pageMoveTryCount = 0;
	    if (model.getMoveWindowMode() == NavigatorModel.MOVE_WINDOW_PAGEWISE) {
		pageMoveTryCount = 1;
	    } else {
		pageMoveTryCount = model.getRowCount();
	    }
	    if (mode == NavigatorModel.LEFT_JUMP) {
		pageMoveTryCount = (-1) * pageMoveTryCount;
	    }
	    model.movePage(pageMoveTryCount);
	    pageChanged();
	    if (previousPage == model.getCurrentPage()) {
		return 0;
	    } else {
		// page changed //
		model.setPreviousFocus(model.getCurrentFocus());
		model.moveFocus(mode);
		focusChanged();
		return 0;
	    }
	} else
	    return -1;
    }

    public void pageChanged() {
	Logger.I(this, "super PageChanged");
	sendActionEvent(EventType.PageChange, true);
    }

    public void focusChanged() {
	sendActionEvent(EventType.FocusChange, true);
    }

    public void sendActionEvent(int actionEventType) {
	view.sendActionEvent(actionEventType);
    }

    public void sendActionEvent(int actionEventType, boolean send) {
	view.sendActionEvent(actionEventType, send);
    }

    public abstract void keyPressed(KeyEvent e);

    public abstract void keyReleased(KeyEvent e);
}
