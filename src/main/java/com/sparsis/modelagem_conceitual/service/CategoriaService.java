package com.sparsis.modelagem_conceitual.service;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sparsis.modelagem_conceitual.domain.Categoria;
import com.sparsis.modelagem_conceitual.repository.CategoriaRepository;
import com.sparsis.modelagem_conceitual.service.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	public Categoria findById(Integer id) throws ObjectNotFoundException {
		Supplier<? extends ObjectNotFoundException> exceptionNotFound = () -> {
			String mensagem = "Objeto não encontrado, id: " + id + ", Tipo: " + Categoria.class.getName();
			return new ObjectNotFoundException(mensagem);
		};
		
		return repository.findById(id).orElseThrow(exceptionNotFound);
	}
}
