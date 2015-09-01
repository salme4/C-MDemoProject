package com.castis.winter.util;

public class Logger {

    public static final String CCA = "CCA";

    public static boolean isLog = true;

    public static boolean isLog() {
	return isLog;
    }

    public static void setLog(boolean isLog) {
	Logger.isLog = isLog;
	//CiUtils.setDebugMessage(isLog);
    }

    private static String getPrefix() {
	// return "CASTIS,RVOD," + DateUtil.getDateString("yyyy-MM-dd") + "," +
	// DateUtil.getDateString("HH:mm:ss.SSS") + ",";
	return CCA + ", ";
    }

    public static void E(Object object, String message) {
	String name = object.getClass().getName();
	System.err.println(getPrefix() + "ERROR," + name + " | " + message);
    }

    public static void W(Object object, String message) {
	String name = object.getClass().getName();
	print("WARNING," + name + " | " + message);
    }

    public static void F(Object object, String message) {
	String name = object.getClass().getName();
	print("FAIL," + name + " | " + message);
    }

    public static void I(Object object, String message) {
	if (isLog()) {
	    String name = object.getClass().getName();
	    print("INFO, " + name + " | " + message);
	}
    }

    public static void T(String message) {
	System.out.println(getPrefix() + "TIME " + DateUtil.getDateString("yyyy-MM-dd") + "," + DateUtil.getDateString("HH:mm:ss.SSS") + " | " + message);
    }

    private static void print(String message) {
	System.out.println(getPrefix() + message);
    }

}