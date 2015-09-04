package com.castis.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.json.me.JSONArray;
import org.json.me.JSONException;
import org.json.me.JSONObject;

import com.castis.winter.util.Logger;

public class MenuModel {
	private int startIndex = 0; 									   // 배열의 시작 인덱스
	private int endIndex; 								    	       // 배열의 마지막 인덱스
	private int currentIndex = 0;   								   // 현재 선택되어진 인덱스
	private ArrayList<String> arrayTitle = new ArrayList<String>();
	private ArrayList<String> arrayCategoryId = new ArrayList<String>();
	private ArrayList<String> subArrayTitle = new ArrayList<String>();
	private ArrayList<String> subArrayCategoryId = new ArrayList<String>();
	private String[] week = { "일", "월", "화", "수", "목", "금", "토" };
	private int[] focusPosition = {100, 142, 184, 226, 268, 310, 352, 394};
	private int[] indiPosition = {103, 145, 187, 229, 271, 313, 355, 397};
	private ArrayList<Observer> list = new ArrayList<Observer>();
	private int pageSize = 8;
	private int arraySize;
	JSONArray array;
	Category categorys[];
	Category subCategorys[];
	Category category;
	Category subCategory;
	JSONArray jsonArray;
	
	public Category[] getCategorys() {
		return categorys;
	}

	public void setCategorys(Category[] categorys) {
		this.categorys = categorys;
	}

	public MenuModel() {
		//2depth로 포커스 이동 시 view Change

		String jsonInfo_2 = callURL("http://103.21.200.200:8080/HApplicationServer/getCategoryTree.json?version=1&terminalKey=127F75265D478470CFC9764F29604A32&categoryId=0&depth=3");
		ArrayList<JSONObject> jObjects = new ArrayList<JSONObject>();
		try {
			JSONObject jsonObject = new JSONObject(jsonInfo_2);
			jsonArray = jsonObject.getJSONArray("categoryList");
			for (int i = 0; i < jsonArray.length(); i++) {
				if(jsonArray.getJSONObject(i).getString("parentCategoryId").equals("0")){
					jObjects.add(jsonArray.getJSONObject(i));
				}
			}
			
			arraySize = jObjects.size();
			categorys = new Category[arraySize];
			for (int i = 0; i < categorys.length; i++) {
				category = new Category();
				category.setItem(jObjects.get(i));
				categorys[i] = category;
			}
			
			ArrayList<JSONObject> jSubObject = new ArrayList<JSONObject>();

			//2depth 메뉴 진입 
			for (int i = 0; i < categorys.length; i++) {
				String parentCategoryId = categorys[i].getCategoryId();
				Logger.I(this, "=================" + parentCategoryId + "=================");
				for (int j = 0; j < jsonArray.length(); j++) {
					String categoryId = jsonArray.getJSONObject(j).getString("parentCategoryId");
					if(parentCategoryId.equals(categoryId) && !jsonArray.getJSONObject(j).getString("categoryName").equals("")){
						jSubObject.add(jsonArray.getJSONObject(j));
						Logger.I(this, jsonArray.getJSONObject(j).getString("categoryName"));
					}
				}
				subCategorys = new Category[jSubObject.size()];
				for (int k = 0; k < subCategorys.length; k++) {
					subCategory = new Category();
					subCategory.setItem(jSubObject.get(k));
					subCategorys[k] = subCategory;
				}
				categorys[i].setSubCategory(subCategorys);
				subCategorys = null;
				jSubObject.clear();
			}
			
		} catch (JSONException e) {
			Logger.E(this, "json Parsing Or HAS Connection Error!!");
		}
		endIndex = arraySize;
	}
	
	public Category getCategoryAt(int index){
		return this.category.getCategoryAt(index);
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int[] getFocusPosition() {
		return focusPosition;
	}

	public int[] getIndiPosition() {
		return indiPosition;
	}

	public void setIndiPosition(int[] indiPosition) {
		this.indiPosition = indiPosition;
	}

	public void setFocusPosition(int[] focusPosition) {
		this.focusPosition = focusPosition;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public int getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}

	public ArrayList<String> getArrTitle() {
		return arrayTitle;
	}

	public void setArrTitle(ArrayList<String> arrayTitle) {
		this.arrayTitle = arrayTitle;
	}
	
	public void addCurrentIndex(){
		this.currentIndex = this.currentIndex + 1;
		if (currentIndex > endIndex - 1){
			this.currentIndex = 0;
		}
	}
	
	public void subtractCurrentIndex(){
		this.currentIndex = this.currentIndex - 1;
		if (currentIndex < startIndex) {
			this.currentIndex = endIndex - 1;
		}
	}
	
	public void registerObserver(Observer o){
		list.add(o);
	}
	
	public void notifyObservers() {
		for(Observer o : list){
			Logger.I(this, "currentIndex : " + currentIndex);
			o.update(currentIndex);
		}
	}
	
	public String getDateString() { 
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		String date = String.valueOf(cal.get(Calendar.MONDAY) + 1) + "월 ";
		date += String.valueOf(cal.get(Calendar.DATE)) + "일 ";
		date += "(" + week[cal.get(Calendar.DAY_OF_WEEK) - 1] + ") ";
		date += dateFormat.format(cal.getTime());
	  	return date;
	}
	
	public String callURL(String myURL){
		Logger.I(this, "Requested URL : " + myURL);
		StringBuilder sb = new StringBuilder();
		URLConnection urlConn = null;
		InputStreamReader in = null;
		
		try{
			URL url = new URL(myURL);
			urlConn = url.openConnection();
			if(urlConn != null){
				urlConn.setReadTimeout(60 * 1000);
			}
			if (urlConn != null && urlConn.getInputStream() != null) {
				in = new InputStreamReader(urlConn.getInputStream(),Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);
				if (bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);
					}
					bufferedReader.close();
				}
			}
		in.close();
		}catch(Exception e){
			throw new RuntimeException("Exception while calling URL : " + myURL, e);
		}
		return sb.toString();
	}
}
