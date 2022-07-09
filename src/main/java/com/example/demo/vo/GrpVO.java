package com.example.demo.vo;

import org.springframework.stereotype.Component;

@Component
public class GrpVO {
	
	private int idx;
	private	String datetime;
	private	int hour;
	private	int supply;
	private	int demand;
	private	int supplyrp;
	private	int operationrp;
	
	public GrpVO() {}

	public GrpVO(int idx, String datetime, int hour, int supply, int demand, int supplyrp, int operationrp) {
		super();
		this.idx = idx;
		this.datetime = datetime;
		this.hour = hour;
		this.supply = supply;
		this.demand = demand;
		this.supplyrp = supplyrp;
		this.operationrp = operationrp;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getSupply() {
		return supply;
	}

	public void setSupply(int supply) {
		this.supply = supply;
	}

	public int getDemand() {
		return demand;
	}

	public void setDemand(int demand) {
		this.demand = demand;
	}

	public int getSupplyrp() {
		return supplyrp;
	}

	public void setSupplyrp(int supplyrp) {
		this.supplyrp = supplyrp;
	}

	public int getOperationrp() {
		return operationrp;
	}

	public void setOperationrp(int operationrp) {
		this.operationrp = operationrp;
	}
	
	
	
}
