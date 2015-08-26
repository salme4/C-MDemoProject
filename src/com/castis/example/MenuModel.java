package com.castis.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MenuModel {
	private int startIndex = 0; 									   // 배열의 시작 인덱스
	private int endIndex; 								    	       // 배열의 마지막 인덱스
	private int viewStartIndex = 0; 								   // 화면에 보여질 시작 인덱스
	private int viewEndIndex = 7; 						  	  		   // 화면에 보여질 마지막 인덱스
	private int currentIndex = 0;   								   // 현재 선택되어진 인덱스
	private ArrayList<String> arrayTitle = new ArrayList<String>();
	private String[] week = { "일", "월", "화", "수", "목", "금", "토" };
	private int[] focusPosition = {100, 142, 184, 226, 268, 310, 352, 394};
	private int[] indiPosition = {103, 145, 187, 229, 271, 313, 355, 397};
	private ArrayList<Observer> list = new ArrayList<Observer>();
	private int itemSize;
	private int pageSize = 8;
	private Category category;
	private Category[] subCategory;
	private Category root;
	
	public MenuModel() {
		//HAS ip :  
		//urlConnection
		//인증
		//getCategory
		//가져온 데이터 메모리에 저장
		//or 1depth 씩 가져오는 방법
		String jsonInfo_2 = callURL("http://103.21.200.200:8080/HApplicationServer/getCategoryTree.json?version=1&terminalKey=127F75265D478470CFC9764F29604A32&categoryId=0");
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject)jsonParser.parse(jsonInfo_2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		JSONArray jsonArray = (JSONArray)jsonObject.get("categoryList");
		
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonCategory = (JSONObject)jsonArray.get(i);
			if (jsonCategory.get("parentCategoryId").equals("0")){
				arrayTitle.add((String) jsonCategory.get("categoryName"));
			}
		}

		root = new Category();
		subCategory = new Category[arrayTitle.size()];
		for (int i = 0; i < arrayTitle.size(); i++) {
			subCategory[i] = new Category(arrayTitle.get(i), String.valueOf(i));
		}
		root.setSubCategory(subCategory);
//		subCategory[0].setSubCategory(subCategory);
		
		endIndex = arrayTitle.size() - 1;
		itemSize = arrayTitle.size();
	}
	
	public Category getRoot() {
		return root;
	}

	public void setRoot(Category root) {
		this.root = root;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getItemSize() {
		return itemSize;
	}

	public void setItemSize(int itemSize) {
		this.itemSize = itemSize;
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

	public int getViewStartIndex() {
		return viewStartIndex;
	}

	public void setViewStartIndex(int viewStartIndex) {
		this.viewStartIndex = viewStartIndex;
	}

	public int getViewEndIndex() {
		return viewEndIndex;
	}

	public void setViewEndIndex(int viewEndIndex) {
		this.viewEndIndex = viewEndIndex;
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
	
	public void pulsCurrentIndex(){
		this.currentIndex = this.currentIndex + 1;
		if (currentIndex > endIndex){
			this.currentIndex = 0;
		}
	}
	
	public void minusCurrentIndex(){
		this.currentIndex = this.currentIndex - 1;
		if (currentIndex < startIndex) {
			this.currentIndex = endIndex;
		}
	}
	
	public void registerObserver(Observer o){
		list.add(o);
	}
	
	
	public void notifyObservers() {
		for(Observer o : list){
			System.out.println("currentIndex : " + currentIndex);
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

	public void viewPlus() {
		this.viewStartIndex++;
		this.viewEndIndex++;
		if(viewEndIndex > endIndex){
			this.viewStartIndex = startIndex;
//			this.viewEndIndex = endIndex;
		}
	}

	public void viewMinus() {
		this.viewStartIndex--;
		this.viewEndIndex--;
		if(viewStartIndex < 0){
			this.viewStartIndex = endIndex - viewEndIndex;
//			this.viewEndIndex = endIndex;
		}
	}
	
	public String callURL(String myURL){
		System.out.println("Requested URL : " + myURL);
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
