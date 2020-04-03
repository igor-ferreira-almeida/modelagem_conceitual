package com.sparsis.modelagem_conceitual.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "estado")
public class Estado extends ORM<Long> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "nome")
	private String nome;
	
	@JsonIgnore
	@OneToMany(mappedBy = "estado")
	private List<Cidade> cidades = new ArrayList<>();
	
	public Estado() {}

	public Estado(Long id, String nome) {
		this.setId(id);
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((super.getId() == null) ? 0 : super.getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estado other = (Estado) obj;
		if (super.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!super.getId().equals(other.getId()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		List<String> nomesCidades = cidades.stream().map(cidade -> cidade.getNome()).collect(Collectors.toList());
		StringBuilder sb = new StringBuilder();
		sb.append("Estado [id=" + super.getId() + ", nome=" + nome + ", cidades=");
		
		nomesCidades.forEach(cidade -> sb.append(cidade + " "));
		sb.append("]");
		
		return sb.toString();
	}
}
