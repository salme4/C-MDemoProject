package com.castis.winter.navigator;

import java.io.Serializable;

import com.castis.winter.util.Logger;

public abstract class NavigatorModel implements Serializable {
    public static final int CIRCULATION_NONE = 0;
    public static final int CIRCULATION_UP_TO_DOWN = 1;
    public static final int CIRCULATION_DOWN_TO_UP = 2;
    public static final int CIRCULATION_BOTH = 3;
    public static final int MOVE_WINDOW_PAGEWISE = 0;
    public static final int MOVE_WINDOW_LINEWISE = 1;
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    public static final int LEFT_JUMP = 4;
    public static final int RIGHT_JUMP = 5;
    NavigatorModelData data;

    public NavigatorModel(NavigatorModelData data) {
	this.data = data;
	if (data != null)
	    initialize();
    }

    public void initialize() {
	if (data.moveWindowMode == MOVE_WINDOW_PAGEWISE) {
	    data.totalPageCount = ((int) (Math.ceil((double) data.totalItemCount / (double) (data.lineCount * data.rowCount))));
	    setPageStartLine(data.pageStartLine);
	} else if (data.circulationMode == CIRCULATION_NONE) {
	    data.totalPageCount = (getTotalLineCount() - (data.rowCount - 1));
	    setPageStartLine(data.pageStartLine);
	} else {
	    data.totalPageCount = getTotalLineCount();
	    setPageStartLine(data.pageStartLine);
	}
	// isSelected = true;
    }

    public void initData() {
	this.data = null;
    }

    public void setNavigatorData(NavigatorModelData data) {
	this.data = data;
	initialize();
    }

    public NavigatorModelData getNavigatorData() {
	return this.data;
    }

    public Object[] getObjects() {
	if (this.data == null)
	    return null;
	return this.data.getObjects();
    }

    public void setObjects(Object[] object) {
	if (this.data == null) {
	    Logger.E(this, "NavigatorData is NULL");
	} else {
	    this.data.setObjects(object);
	}
    }

    public int viewableDataSize(int dataSize) {
	return Math.min(dataSize, this.data.getObjects().length);
    }

    public int getObjectsSize() {
	if (this.data.getObjects() != null)
	    return this.data.getObjects().length;
	return 0;
    }

    public void moveFocus(int moveMode) {
	int nextFocus = 0;
	switch (moveMode) {
	case UP:
	    if (data.circulationMode == CIRCULATION_DOWN_TO_UP || data.circulationMode == CIRCULATION_BOTH) {
		data.currentFocus = (data.currentFocus - 1 + data.totalItemCount) % data.totalItemCount;
	    } else {
		if (data.currentFocus > 0)
		    data.currentFocus = data.currentFocus - 1;
	    }
	    break;
	case DOWN:
	    if (data.circulationMode == CIRCULATION_UP_TO_DOWN || data.circulationMode == CIRCULATION_BOTH) {
		data.currentFocus = (data.currentFocus + 1) % data.totalItemCount;
	    } else {
		if (data.currentFocus < data.totalItemCount - 1)
		    data.currentFocus = data.currentFocus + 1;
	    }
	    break;
	case LEFT:
	    if (data.circulationMode == CIRCULATION_DOWN_TO_UP || data.circulationMode == CIRCULATION_BOTH) {
		nextFocus = (data.currentFocus - data.lineCount + (data.lineCount * getTotalLineCount())) % (data.lineCount * getTotalLineCount());
		if (nextFocus <= data.totalItemCount - 1)
		    data.currentFocus = nextFocus;
		else
		    data.currentFocus = data.totalItemCount - 1;
	    } else {
		nextFocus = data.currentFocus - data.lineCount;
		if (nextFocus >= 0)
		    this.data.currentFocus = nextFocus;
	    }
	    break;
	case RIGHT:
	    if (data.circulationMode == CIRCULATION_UP_TO_DOWN || data.circulationMode == CIRCULATION_BOTH) {
		nextFocus = (data.currentFocus + data.lineCount) % (data.lineCount * getTotalLineCount());
		if (nextFocus <= data.totalItemCount - 1)
		    data.currentFocus = nextFocus;
		else
		    data.currentFocus = data.totalItemCount - 1;
	    } else {
		nextFocus = data.currentFocus + data.lineCount;
		if (nextFocus <= data.totalItemCount - 1)
		    data.currentFocus = nextFocus;
		else if (nextFocus < data.lineCount * getTotalLineCount())
		    data.currentFocus = data.totalItemCount - 1;
	    }
	    break;
	case LEFT_JUMP:
	    if (data.circulationMode == CIRCULATION_DOWN_TO_UP || data.circulationMode == CIRCULATION_BOTH) {
		if (data.moveWindowMode == MOVE_WINDOW_PAGEWISE)
		    nextFocus = (data.currentFocus - (data.lineCount * data.rowCount) + (data.lineCount * data.rowCount * data.totalPageCount))
			    % (data.lineCount * data.rowCount * data.totalPageCount);
		else
		    nextFocus = (data.currentFocus - (data.lineCount * data.rowCount) + (data.lineCount * getTotalLineCount()))
			    % (data.lineCount * getTotalLineCount());
		if (nextFocus <= data.totalItemCount - 1)
		    data.currentFocus = nextFocus;
		else
		    data.currentFocus = data.totalItemCount - 1;
	    } else {
		nextFocus = data.currentFocus - (data.lineCount * data.rowCount);
		if (nextFocus >= 0)
		    data.currentFocus = nextFocus;
		else
		    this.data.currentFocus = 0;
	    }
	    break;
	case RIGHT_JUMP:
	    if (data.circulationMode == CIRCULATION_UP_TO_DOWN || data.circulationMode == CIRCULATION_BOTH) {
		if (data.moveWindowMode == MOVE_WINDOW_PAGEWISE)
		    nextFocus = (data.currentFocus + (data.lineCount * data.rowCount)) % (data.lineCount * data.rowCount * data.totalPageCount);
		else {
		    nextFocus = (data.currentFocus + (data.lineCount * data.rowCount)) % (data.lineCount * getTotalLineCount());
		}
		if (nextFocus <= data.totalItemCount - 1) {
		    data.currentFocus = nextFocus;
		} else {
		    data.currentFocus = data.totalItemCount - 1;
		}
	    } else {
		nextFocus = data.currentFocus + (data.lineCount * data.rowCount);
		if (nextFocus <= data.totalItemCount - 1)
		    data.currentFocus = nextFocus;
		else
		    data.currentFocus = data.totalItemCount - 1;
	    }
	    break;
	}
    }

    public void movePage(int page_move_count) {
	if (page_move_count > 0) {
	    if (data.circulationMode == CIRCULATION_UP_TO_DOWN || data.circulationMode == CIRCULATION_BOTH) {
		data.previousPage = data.currentPage;
		data.currentPage = (data.currentPage + page_move_count) % data.totalPageCount;
	    } else {
		data.previousPage = data.currentPage;
		data.currentPage = data.currentPage + page_move_count;
		if (data.currentPage > data.totalPageCount - 1) {
		    data.currentPage = data.totalPageCount - 1;
		}
	    }
	} else if (page_move_count < 0) {
	    if (data.circulationMode == CIRCULATION_DOWN_TO_UP || data.circulationMode == CIRCULATION_BOTH) {
		data.previousPage = data.currentPage;
		data.currentPage = (data.currentPage + page_move_count + data.totalPageCount) % data.totalPageCount;
	    } else {
		data.previousPage = data.currentPage;
		data.currentPage = data.currentPage + page_move_count;
		if (data.currentPage < 0)
		    data.currentPage = 0;
	    }
	}
    }

    public void setPageStartLine(int pageStartLine) {
	if (data.moveWindowMode == MOVE_WINDOW_PAGEWISE)
	    data.currentPage = pageStartLine / data.rowCount;
	else
	    data.currentPage = pageStartLine;
    }

    public void setCurrentFocus(int currentFocus) {
	if (currentFocus < 0 || currentFocus > data.totalItemCount - 1) {
	    currentFocus = -1;
	}
	data.previousFocus = this.data.currentFocus;
	this.data.currentFocus = currentFocus;
    }

    public int getPageStartLine() {
	if (data.moveWindowMode == MOVE_WINDOW_PAGEWISE)
	    return data.currentPage * data.rowCount;
	else
	    return data.currentPage;
    }

    public int getPreviousPageStartLine() {
	if (data.moveWindowMode == MOVE_WINDOW_PAGEWISE)
	    return data.previousPage * data.rowCount;
	else
	    return data.previousPage;
    }

    public int calculateLineIndex(int index) {
	return index / this.data.lineCount;
    }

    public int getCurrentPageNumber() {
	return data.currentPage + 1;
    }

    public int getTotalLineCount() {
	return (int) (Math.ceil((double) data.totalItemCount / (double) data.lineCount));
    }

    public int getIndexOfNthInPage(int nth) {
	int index = (getPageStartLine() * data.lineCount) + nth;
	if (data.circulationMode != CIRCULATION_NONE && data.moveWindowMode == MOVE_WINDOW_LINEWISE)
	    index = index % (data.lineCount * getTotalLineCount());
	if (index > data.totalItemCount - 1) {
	    return -1;
	}
	return index;
    }

    public int calculateIndexInPage(int index) {
	int startIndexInPage = getIndexOfNthInPage(0);
	if (startIndexInPage == -1)
	    return -1;
	int indexInPage;
	if (index >= startIndexInPage) {
	    indexInPage = index - startIndexInPage;
	} else {
	    if (data.moveWindowMode == MOVE_WINDOW_PAGEWISE)
		indexInPage = index - startIndexInPage + (data.lineCount * data.rowCount * data.totalPageCount);
	    else
		indexInPage = index - startIndexInPage + (data.lineCount * getTotalLineCount());
	}
	if (indexInPage > data.lineCount * data.rowCount - 1)
	    return -1;
	return indexInPage;
    }

    public int calculateIndexInLine(int index) {
	int indexInPage = calculateIndexInPage(index);
	if (indexInPage == -1)
	    return -1;
	return indexInPage % this.data.lineCount;
    }

    int calculateLineIndexInPage(int index) {
	int indexInPage = calculateIndexInPage(index);
	if (indexInPage == -1)
	    return -1;
	return indexInPage / data.lineCount;
    }

    public int getLineCount() {
	return data.lineCount;
    }

    public int getRowCount() {
	return data.rowCount;
    }

    public int getTotalItemCount() {
	return data.totalItemCount;
    }

    public int getCurrentFocus() {
	    if(data!=null)
	return data.currentFocus;
	    else return -1;
    }

    public int getCurrentFocusInPage() {
	return calculateIndexInPage(data.currentFocus);
    }

    public int getPreviousFocus() {
	return data.previousFocus;
    }

    public int getCirculationMode() {
	return data.circulationMode;
    }

    public int getMoveWindowMode() {
	return data.moveWindowMode;
    }

    public int getPageTransitionThresholdToPlus() {
	return data.pageTransitionThresholdToPlus;
    }

    public int getPageTransitionThresholdToMinus() {
	return data.pageTransitionThresholdToMinus;
    }

    public int getTotalPageCount() {
	return data.totalPageCount;
    }

    public int getTotalPageNumber() {
	return data.totalPageCount + 1;
    }

    public int getCurrentPage() {
	return data.currentPage;
    }

    public int getPreviousPage() {
	return data.previousPage;
    }

    public Object getCurrentObject() {
	int focusIndex = getCurrentFocus();
	if (data.objects == null || focusIndex == -1 || focusIndex > getObjectsSize() - 1)
	    return null;
	return data.objects[focusIndex];
    }

    public Object getCurrentObjectInPage() {
	int focusIndex = calculateIndexInPage(getCurrentFocus());
	if (data.objects == null || focusIndex == -1 || focusIndex > getObjectsSize() - 1)
	    return null;
	return data.objects[focusIndex];
    }

    public Object getFirstObject() {
	return getObjectAt(0);
    }

    public Object getObjectAt(int index) {
	if (index < 0 || index >= getTotalItemCount())
	    return null;
	return data.objects[index];
    }

    public String getDisplayStringAt(int index) {
	Object object = getObjectAt(index);
	if (object == null)
	    return null;
	return object.toString();
    }

    public void setPreviousFocus(int focus) {
	data.previousFocus = focus;
    }
}
