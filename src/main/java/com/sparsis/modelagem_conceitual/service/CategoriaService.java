package com.sparsis.modelagem_conceitual.service;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.sparsis.modelagem_conceitual.domain.Categoria;
import com.sparsis.modelagem_conceitual.repository.CategoriaRepository;
import com.sparsis.modelagem_conceitual.service.exception.DataIntegrityException;
import com.sparsis.modelagem_conceitual.service.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria findById(Integer id) throws ObjectNotFoundException {

		Supplier<? extends ObjectNotFoundException> exceptionNotFound = () -> {
			String mensagem = "Objeto não encontrado, id: " + id + ", Tipo: " + Categoria.class.getName();
			return new ObjectNotFoundException(mensagem);
		};
		
		return categoriaRepository.findById(id).orElseThrow(exceptionNotFound);
	}

	public Categoria create(Categoria categoria) {
		categoria.setId(null);
		return categoriaRepository.save(categoria);
	}

	public Categoria update(Integer id, Categoria categoria) {
		findById(id);
		categoria.setId(id);
		return categoriaRepository.save(categoria);
	}
	
	public void delete(Integer id) {
		findById(id);
		try {
			categoriaRepository.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produto");
		}
	}

}
