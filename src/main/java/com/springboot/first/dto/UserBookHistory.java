package com.springboot.first.dto;

public class UserBookHistory {

	private String userName;
	private String issueDate;
	private String returnDate;
	private String bookingId;
	
	public UserBookHistory(String userName, String issueDate, String returnDate, String bookingId) {
		super();
		this.userName = userName;
		this.issueDate = issueDate;
		this.returnDate = returnDate;
		this.bookingId = bookingId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	
	
	
}
