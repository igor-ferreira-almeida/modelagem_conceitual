package com.sparsis.modelagem_conceitual.service;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sparsis.modelagem_conceitual.domain.ORM;
import com.sparsis.modelagem_conceitual.service.exception.DataIntegrityException;
import com.sparsis.modelagem_conceitual.service.exception.ObjectNotFoundException;

public class Service<T extends ORM<Long>, REPOSITORY extends JpaRepository<T, Long>> {
	
	@Autowired
	private REPOSITORY repository;
	
	public T findById(Long id) throws ObjectNotFoundException {
		Supplier<? extends ObjectNotFoundException> exceptionNotFound = () -> {
			String mensagem = "Objeto não encontrado, id: ";
			return new ObjectNotFoundException(mensagem);
		};

		return repository.findById(id).orElseThrow(exceptionNotFound);
	}
	
	public List<T> findAll() throws ObjectNotFoundException {
		return repository.findAll();
	}
	
	public T create(T t) {
		t.setId(null);
		return repository.save(t);
	}
	
	public T update(Long id, T t) {
		findById(id);
		t.setId(id);
		return repository.saveAndFlush(t);
	}
	
	public void delete(Long id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produto");
		}
	}
	
	public Page<T> findPage(Integer page, Integer linePerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linePerPage, Direction.valueOf(direction), orderBy);
		
		return repository.findAll(pageRequest);
	}
}
