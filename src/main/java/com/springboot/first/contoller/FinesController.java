package com.springboot.first.contoller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.first.model.Fines;
import com.springboot.first.service.FinesService;

@RestController
@RequestMapping("/api/fines")
public class FinesController {

	private FinesService finesService;

	public FinesController(FinesService finesService) {
		super();
		this.finesService = finesService;
	}
	
	@GetMapping
	public List<Fines> findFines(){
		return finesService.finesInfo();
	}
}
