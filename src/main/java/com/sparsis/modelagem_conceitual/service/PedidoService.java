package com.sparsis.modelagem_conceitual.service;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sparsis.modelagem_conceitual.domain.Pedido;
import com.sparsis.modelagem_conceitual.repository.PedidoRepository;
import com.sparsis.modelagem_conceitual.service.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	public Pedido findById(Integer id) throws ObjectNotFoundException {
		Supplier<? extends ObjectNotFoundException> exceptionNotFound = () -> {
			String mensagem = "Objeto não encontrado, id: " + id + ", Tipo: " + Pedido.class.getName();
			return new ObjectNotFoundException(mensagem);
		};

		return repository.findById(id).orElseThrow(exceptionNotFound);
	}
}
