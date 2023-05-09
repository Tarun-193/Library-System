package com.springboot.first.model;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"BookId"}),@UniqueConstraint(columnNames = {"BookName"}),@UniqueConstraint(columnNames = {"BookImage"})} )
public class Books {
	 @Id
	 @Column(name = "BookId")
	 private String bookId;
	 
	 @NotNull
	 @NotEmpty(message = "BookName cannot be empty")
	 @Pattern(regexp = "^[a-zA-Z ]*$", message = "Name must contain only alphabets")
	 @Column(name = "BookName")
	 private String bookName;
	 
	 @NotNull
	 @NotEmpty(message = "Author cannot be empty")
	 @Pattern(regexp = "^[a-zA-Z ]*$", message = "Name must contain only alphabets")
	 @Column(name = "Author")
	 private String author;
	 
	 @NotNull
	 @Positive(message = "Number of books should be positive")
	 @Column(name = "NoOfBooks")
	 private int noOfBooks;
	 
	 @NotNull
	 @DecimalMin(value = "0.0")
	 @Column(name = "Cost" )
	 private double cost;
	 
	 @NotNull
	 @NotEmpty
	 @Pattern(regexp = "^[a-zA-Z][0-9]", message = "Invalid Rack!")
	 @Column(name = "Rack")
	 private String rack;
	 
	 @NotNull
	 @NotEmpty
	 @Column(name = "BookImage")
	 private String bookImage;
	 
	 
	 @Column(name = "ReasonForUpdate")
	 private String reasonForUpdate;
	  
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getNoOfBooks() {
		return noOfBooks;
	}
	public void setNoOfBooks(int noOfBooks) {
		this.noOfBooks = noOfBooks;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getRack() {
		return rack;
	}
	public void setRack(String rack) {
		this.rack = rack;
	}
	public String getBookImage() {
		return bookImage;
	}
	public void setBookImage(String bookImage) {
		this.bookImage = bookImage;
	}
	public String getReasonForUpdate() {
		return reasonForUpdate;
	}
	public void setReasonForUpdate(String reasonForUpdate) {
		this.reasonForUpdate = reasonForUpdate;
	}
}
