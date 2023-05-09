package com.springboot.first.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.first.model.Fines;
import com.springboot.first.repository.FinesRepository;
import com.springboot.first.service.FinesService;

@Service
public class FinesServiceImplementation implements FinesService {

	private FinesRepository finesRepository;
	
	public FinesServiceImplementation(FinesRepository finesRepository) {
		super();
		this.finesRepository = finesRepository;
	}

	@Override
	public List<Fines> finesInfo() {
		return finesRepository.findAll();
	}

	

}
