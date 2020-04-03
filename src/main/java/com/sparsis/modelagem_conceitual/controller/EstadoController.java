package com.sparsis.modelagem_conceitual.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparsis.modelagem_conceitual.domain.Estado;
import com.sparsis.modelagem_conceitual.dto.EstadoDTO;
import com.sparsis.modelagem_conceitual.service.EstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoController extends Controller<Estado, Long, EstadoDTO, EstadoService> {
	
}
