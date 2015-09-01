package com.castis.winter.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class LocalBroadcastUtil {

    private static final LocalBroadcastUtil instance = new LocalBroadcastUtil();

    public static LocalBroadcastUtil getInstance() {
	return instance;
    }

    private Properties versionProperties;

//    private LocalBroadcastData localBroadcastData;

//    public LocalBroadcastData getLocalBroadcastData(){
//	return this.localBroadcastData;
//	
//    }
    
    public boolean readFromFile() {
	versionProperties = new Properties();
//	try {

//		FileInputStream inputStream = new FileInputStream(STBInfoAgent.getInstance().getRootDirectory() + File.separator + "epg/local2.properties");
//		versionProperties.load(inputStream);
//		inputStream.close();
//		setLocalBroadcastData();
//	    } catch (Exception localException1) {
//		localException1.printStackTrace();
//		return false;
//	    }
	return true;
    }

    private void setLocalBroadcastData() {
//	localBroadcastData= new LocalBroadcastData();
//	localBroadcastData.setMenuName(versionProperties.getProperty("MENU_TEXT"));
//	String[] link = StringUtil.split(versionProperties.getProperty("LINK").trim(), ",");
//	localBroadcastData.setLinkType(link[0]);
//	localBroadcastData.setLinkData(link[1]);
    }
}
