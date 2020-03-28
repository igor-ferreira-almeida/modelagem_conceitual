package com.sparsis.modelagem_conceitual.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparsis.modelagem_conceitual.domain.Categoria;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {
	
	@GetMapping
	public List<Categoria> listar() {
		Categoria categoria1 = new Categoria(1, "Informática");
		Categoria categoria2 = new Categoria(2, "Escritório");
		
		List<Categoria> categorias = Arrays.asList(categoria1, categoria2);
		
		return categorias;
	}

}
