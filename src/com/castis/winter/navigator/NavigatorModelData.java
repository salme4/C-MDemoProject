package com.castis.winter.navigator;

import java.io.Serializable;

public class NavigatorModelData implements Serializable {
    int lineCount;
    int rowCount;
    int totalItemCount;
    int currentFocus;
    int previousFocus;
    int circulationMode;
    int moveWindowMode;
    int pageTransitionThresholdToPlus;
    int pageTransitionThresholdToMinus;
    int totalPageCount;
    int currentPage;
    int previousPage;
    int pageStartLine;
    Object[] objects;

    public void setObjects(Object[] object) {
	this.objects = object;
    }

    public void setObject(Object object, int index) {
	this.objects[index] = object;
    }

    public Object[] getObjects() {
	return this.objects;
    }

    public NavigatorModelData(Object[] objects, int lineCount, int rowCount, int totalItemCount, int currentFocus, int circulationMode, int moveWindowMode,
	    int pageStartLine, int pageTransitionThresholdToMinus, int pageTransitionThresholdToPlus) {
	this.objects = objects;
	this.lineCount = lineCount;
	this.rowCount = rowCount;
	this.totalItemCount = totalItemCount;
	this.currentFocus = currentFocus;
	this.circulationMode = circulationMode;
	this.moveWindowMode = moveWindowMode;
	this.pageStartLine = pageStartLine;
	this.pageTransitionThresholdToMinus = pageTransitionThresholdToMinus;
	this.pageTransitionThresholdToPlus = pageTransitionThresholdToPlus;
    }

    public NavigatorModelData(Object[] objects) {
	this.objects = objects;
	this.lineCount = 1;
	this.rowCount = objects.length;
	this.totalItemCount = objects.length;
	this.currentFocus = 0;
	this.circulationMode = NavigatorModel.CIRCULATION_BOTH;
	this.moveWindowMode = NavigatorModel.MOVE_WINDOW_PAGEWISE;
	this.pageStartLine = 0;
	this.pageTransitionThresholdToMinus = 0;
	this.pageTransitionThresholdToPlus = objects.length - 1;
    }

    public NavigatorModelData(Object[] objects, int minLength) {
	this.objects = objects;
	this.lineCount = 1;
	this.rowCount = Math.min(objects.length, minLength);
	this.totalItemCount = objects.length;
	this.currentFocus = 0;
	this.circulationMode = NavigatorModel.CIRCULATION_BOTH;
	this.moveWindowMode = NavigatorModel.MOVE_WINDOW_PAGEWISE;
	this.pageStartLine = 0;
	this.pageTransitionThresholdToMinus = 0;
	this.pageTransitionThresholdToPlus = Math.min(objects.length, minLength) - 1;
    }
}