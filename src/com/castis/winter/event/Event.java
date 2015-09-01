package com.castis.winter.event;

import com.castis.winter.window.WComponent;

public class Event {
    public int keyCode; // system에서 전달 받은 event와 동일한 KeyCode 처음에는
    public WComponent wComponent;
    public int type;

//    public int secondIntValue;
//    public int firstIntValue;
//    public Object secondObjectValue;
//    public Object firstObjectValue;

    public String toString() {
	return "Event {type=" + type + " keyCode=" + keyCode + "}";
    }
}
