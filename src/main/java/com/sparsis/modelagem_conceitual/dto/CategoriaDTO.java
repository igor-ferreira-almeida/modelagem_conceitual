package com.sparsis.modelagem_conceitual.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;

import com.sparsis.modelagem_conceitual.domain.Categoria;

public class CategoriaDTO implements DTO, Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@Length(min = 5, message = "O nome deve conter no mínimo 5 caracter")
	@NotNull(message = "Preenchimento obrigatório")
	@NotEmpty(message = "Preenchimento obrigatório")
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
	
	public static Page<CategoriaDTO> toPageList(Page<Categoria> categorias) {
		return categorias.map(c -> new CategoriaDTO(c));
	}
}
