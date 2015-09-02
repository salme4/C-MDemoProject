package com.castis.example;

import java.awt.Component;
import java.awt.Container;
import java.awt.Window;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.json.me.JSONArray;
import org.json.me.JSONException;
import org.json.me.JSONObject;

import com.castis.winter.navigator.NavigatorModelData;
import com.castis.winter.window.WComposite;

public class Menu {
	private static int arraySize;
	JSONArray array;
	static Category categorys[];
	
	public static void main(String[] args) throws Exception{
		MenuModel menuModel = new MenuModel();
		menuView menuView = new menuView();
		menuModel.registerObserver(menuView);
		MenuController menuController = new MenuController(menuView, menuModel);
		menuView.setVisible(true);
		
		
		
//		String jsonInfo_2 = callURL("http://103.21.200.200:8080/HApplicationServer/getCategoryTree.json?version=1&terminalKey=127F75265D478470CFC9764F29604A32&categoryId=0&depth=3");
//		ArrayList<JSONObject> jObjects = new ArrayList<JSONObject>();
//		try {
//			JSONObject jsonObject = new JSONObject(jsonInfo_2);
//			JSONArray jsonArray = jsonObject.getJSONArray("categoryList");
//			for (int i = 0; i < jsonArray.length(); i++) {
//				if(jsonArray.getJSONObject(i).getString("parentCategoryId").equals("0")){
//					jObjects.add(jsonArray.getJSONObject(i));
//				}
//			}
//			
//			arraySize = jObjects.size();
//			categorys = new Category[arraySize];
//			for (int i = 0; i < categorys.length; i++) {
//				Category category = new Category();
//				category.setItem(jObjects.get(i));
//				categorys[i] = category;
//			}
//			
//			
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		
//		NavigatorModelData data = new NavigatorModelData(categorys);
//		subMenuModel model = new subMenuModel(data);
//		subMenuView view = new subMenuView(parent, model);
//		view.setVisible(true);
	}
	
//	public static String callURL(String myURL){
//		System.out.println("Requested URL : " + myURL);
//		StringBuilder sb = new StringBuilder();
//		URLConnection urlConn = null;
//		InputStreamReader in = null;
//		
//		try{
//			URL url = new URL(myURL);
//			urlConn = url.openConnection();
//			if(urlConn != null){
//				urlConn.setReadTimeout(60 * 1000);
//			}
//			if (urlConn != null && urlConn.getInputStream() != null) {
//				in = new InputStreamReader(urlConn.getInputStream(),Charset.defaultCharset());
//				BufferedReader bufferedReader = new BufferedReader(in);
//				if (bufferedReader != null) {
//					int cp;
//					while ((cp = bufferedReader.read()) != -1) {
//						sb.append((char) cp);
//					}
//					bufferedReader.close();
//				}
//			}
//		in.close();
//		}catch(Exception e){
//			throw new RuntimeException("Exception while calling URL : " + myURL, e);
//		}
//		return sb.toString();
//	}
}
