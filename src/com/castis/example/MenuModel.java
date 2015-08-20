package com.castis.example;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MenuModel {
	private int startIndex = 0; 									   // 배열의 시작 인덱스
	private int endIndex; 								    	   // 배열의 마지막 인덱스
	private int viewStartIndex = 0; 								   // 화면에 보여질 시작 인덱스
	private int viewEndIndex = 8;  								  	   // 화면에 보여질 마지막 인덱스
	private int currentIndex = 0;   								   // 현재 선택되어진 인덱스
	private ArrayList<String> arrayTitle = new ArrayList<String>();
	String[] week = { "일", "월", "화", "수", "목", "금", "토" };
	int[] focusPosition = {100, 142, 184, 226, 268, 310, 352, 394};
	
	public MenuModel() {
		// TODO Auto-generated constructor stub
		arrayTitle.add("오늘의 추천");
		arrayTitle.add("영화");
		arrayTitle.add("인기 케이블 / 미드");
		arrayTitle.add("TV 다시보기");
		arrayTitle.add("애니메이션 / 키즈");
		arrayTitle.add("교육 / EBS");
		arrayTitle.add("Joy&Life");
		arrayTitle.add("마이 TV");
		endIndex = arrayTitle.size();
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
		System.out.println(currentIndex);
	}
	
	public void minusCurrentIndex(){
		this.currentIndex = this.currentIndex - 1;
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
	
}
