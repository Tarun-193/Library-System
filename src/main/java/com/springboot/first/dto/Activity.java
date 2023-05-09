package com.springboot.first.dto;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.Positive;

public class Activity {
	
	
	@Range(max = 2, min = 1, message = "enter valid activity")
	private int act;
	@Positive(message = "Number of books can only be positive numbers")
	private int noOfBooks;
	private String reasonString;
	
	public Activity(int act, int noOfBooks, String reasonString) {
		super();
		this.act = act;
		this.noOfBooks = noOfBooks;
		this.reasonString = reasonString;
	}

	public int getAct() {
		return act;
	}

	public void setAct(int act) {
		this.act = act;
	}

	public int getNoOfBooks() {
		return noOfBooks;
	}

	public void setNoOfBooks(int noOfBooks) {
		this.noOfBooks = noOfBooks;
	}

	public String getReasonString() {
		return reasonString;
	}

	public void setReasonString(String reasonString) {
		this.reasonString = reasonString;
	}
	
	
	
	
	

}
