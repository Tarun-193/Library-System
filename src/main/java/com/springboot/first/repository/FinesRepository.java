package com.springboot.first.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.first.model.Fines;

public interface FinesRepository extends JpaRepository<Fines, Integer> {
	
	//query to select fines 
	@Query("select fine from Fines where :days BETWEEN fromDay AND toDay")
	double calcFine(long days);
}
