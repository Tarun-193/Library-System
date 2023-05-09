package com.springboot.first.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Fines {
	@Id
	@Column(name = "FromDay")
	private int fromDay;
	@Column(name="ToDay")
	private int toDay;
	@Column(name = "Fine")
	private double fine;
	
	public int getFromDay() {
		return fromDay;
	}
	public void setFromDay(int fromDay) {
		this.fromDay = fromDay;
	}
	public int getToDay() {
		return toDay;
	}
	public void setToDay(int toDay) {
		this.toDay = toDay;
	}
	public double getFine() {
		return fine;
	}
	public void setFine(double fine) {
		this.fine = fine;
	}

}
