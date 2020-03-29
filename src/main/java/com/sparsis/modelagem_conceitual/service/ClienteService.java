package com.sparsis.modelagem_conceitual.service;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sparsis.modelagem_conceitual.domain.Cliente;
import com.sparsis.modelagem_conceitual.repository.ClienteRepository;
import com.sparsis.modelagem_conceitual.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public Cliente findById(Long id) throws ObjectNotFoundException {
		Supplier<? extends ObjectNotFoundException> exceptionNotFound = () -> {
			String mensagem = "Objeto não encontrado, id: " + id + ", Tipo: " + Cliente.class.getName();
			return new ObjectNotFoundException(mensagem);
		};

		return repository.findById(id).orElseThrow(exceptionNotFound);
	}
}
