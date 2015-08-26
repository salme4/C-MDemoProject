package com.castis.example;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MenuModel {
	private int startIndex = 0; 									   // 배열의 시작 인덱스
	private int endIndex; 								    	       // 배열의 마지막 인덱스
	private int viewStartIndex = 0; 								   // 화면에 보여질 시작 인덱스
	private int viewEndIndex = 7;  								  	   // 화면에 보여질 마지막 인덱스
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
	private ArrayList<String> menuString;
	
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

	public MenuModel() {
		
		//HAS ip :  
		//socket? 
		//인증
		//getCategory
		//가져온 데이터 메모리에 저장
		//or 1depth 씩 가져오는 방법
		//
		
		
		arrayTitle.add("오늘의 추천");
		arrayTitle.add("영화");
		arrayTitle.add("인기 케이블 / 미드");
		arrayTitle.add("TV 다시보기");
		arrayTitle.add("애니메이션 / 키즈");
		arrayTitle.add("교육 / EBS");
		arrayTitle.add("Joy&Life");
		arrayTitle.add("마이 TV");
		arrayTitle.add("테스트 1");
		arrayTitle.add("테스트 2");
		arrayTitle.add("테스트 3");
		arrayTitle.add("테스트 4");
		arrayTitle.add("테스트 5");
		
		
		
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
}
