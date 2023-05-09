package com.springboot.first.dto;

public class BookInfo {

	private int noOfBooks;
	private String rack;
	private double cost;
	
	public BookInfo(int noOfBooks, String rack, double cost) {
		super();
		this.noOfBooks = noOfBooks;
		this.rack = rack;
		this.cost = cost;
	}

	public int getNoOfBooks() {
		return noOfBooks;
	}

	public void setNoOfBooks(int noOfBooks) {
		this.noOfBooks = noOfBooks;
	}

	public String getRack() {
		return rack;
	}

	public void setRack(String rack) {
		this.rack = rack;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
	

}
