package com.springboot.first.model;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"BookingId"})})
public class BookHistory {
	@Id
	@Column(name="BookingId")
	private String bookingId;
	
	@NotNull
	@Positive
	@Column(name="UserId")
	private long userId;
	
	
	@NotNull
	@NotEmpty(message = "BookId cannot be empty")
	@Column(name="BookId")
	private String bookId;
	
	
	@NotNull
	@NotEmpty(message = "issue date cannot be empty")
	@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "issue date must be in 'yyyy-mm-dd' format.")
	@Column(name="IssueDate")
	private String issueDate;
	
	@NotNull
	@NotEmpty(message = "expiry date cannot be empty")
	@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "return date must be in 'yyyy-mm-dd' format.")
	@Column(name="ExpiryDate")
	private String expiryDate;
	
	@Column(name="ReturnDate")
	private String returnDate;
	
	@Column(name="FineAmount")
	private double fineAmount;
	
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public double getFineAmount() {
		return fineAmount;
	}
	public void setFineAmount(double fineAmount) {
		this.fineAmount = fineAmount;
	}
}
