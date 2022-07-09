package com.example.demo.vo;

import org.springframework.stereotype.Component;

@Component
public class DataVO {
	private String location;
	private String date;
	private int year;
	private int month;
	private int day;
	private int hour;
	private int demand_p;
	private String flag;
	
	public DataVO() {}
	public DataVO(String location, String date, int year, int month, int day, int hour, int demand_p, String flag) {
		super();
		this.location = location;
		this.date = date;
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.demand_p = demand_p;
		this.flag = flag;
	}
	public String getDate() {
		return date;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getDemand_p() {
		return demand_p;
	}
	public void setDemand_p(int demand_p) {
		this.demand_p = demand_p;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
	
}
