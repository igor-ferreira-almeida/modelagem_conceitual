package com.sparsis.modelagem_conceitual.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.sparsis.modelagem_conceitual.service.Service;

public class Controller<T, ID, DTO, SERVICE extends Service> {
	
	@Autowired
	private SERVICE service;
	

}
