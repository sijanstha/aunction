package com.fortheby.aunctionitem.model;

import java.util.Date;

public class BidItemModel {

	private String itemName;
	private double bidAmt;
	private String message;
	private Date date;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getBidAmt() {
		return bidAmt;
	}

	public void setBidAmt(double bidAmt) {
		this.bidAmt = bidAmt;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	

}
