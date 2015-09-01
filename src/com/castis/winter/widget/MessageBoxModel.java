/**
 * 
 */
package com.castis.winter.widget;

import java.awt.FontMetrics;
import java.util.ArrayList;

import com.castis.winter.navigator.NavigatorModel;
import com.castis.winter.navigator.NavigatorModelData;
import com.castis.winter.util.StringUtil;

public class MessageBoxModel extends NavigatorModel {
//    private DialogParam dialogParam;
//    private DialogParam subDialogParam;

    public MessageBoxModel(NavigatorModelData data) {
	super(data);
    }

//    public DialogParam getDialogParam() {
//	return dialogParam;
//    }
//
//    public void setDialogParam(DialogParam param) {
//	this.dialogParam = param;
//    }

//    public DialogParam getSubDialogParam() {
//	return subDialogParam;
//    }
//
//    public void setSubDialogParam(DialogParam subDialogParam) {
//	this.subDialogParam = subDialogParam;
//    }
//
//    public static class SubDialogParam extends DialogParam {
//	public SubDialogParam(String contentName, StyleText textStyle1Line) {
//	    super();
//	    this.textStyle = new StyleText[] { textStyle1Line };
//	    this.text = new String[] { contentName };
//	    this.icon = null;
//	    this.title = null;
//	    this.buttons = null;
//	}
//
//	public SubDialogParam(String contentName, StyleText textStyle1Line, StyleText[] textStyle2Line, FontMetrics fontMetrics) {
//	    super();
//	    ArrayList arrayList = StringUtil.getFitStrings(contentName, 304, fontMetrics);
//	    if (arrayList.size() > 2) {
//		this.textStyle = textStyle2Line;
//	    } else {
//		this.textStyle = new StyleText[] { textStyle1Line };
//	    }
//	    this.text = new String[arrayList.size()];
//	    arrayList.toArray(this.text);
//	    this.icon = null;
//	    this.title = null;
//	    this.buttons = null;
//	}
//    }
//
//    public boolean hasButton() {
//	return (dialogParam != null && dialogParam.hasButton());
//    }
}