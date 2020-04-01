package com.sparsis.modelagem_conceitual.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.sparsis.modelagem_conceitual.domain.Categoria;

public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	public CategoriaDTO() {}
	
	public CategoriaDTO(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public CategoriaDTO(Categoria categoria) {
		this.id = categoria.getId();
		this.nome = categoria.getNome();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public static List<CategoriaDTO> toList(List<Categoria> categorias) {
		return categorias.stream().map(c -> new CategoriaDTO(c)).collect(Collectors.toList());
	}
}
