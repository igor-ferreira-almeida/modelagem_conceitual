package com.sparsis.modelagem_conceitual.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparsis.modelagem_conceitual.domain.type.ClienteType;
import com.sparsis.modelagem_conceitual.dto.ClienteDTO;

@Entity
@Table(name = "cliente")
public class Cliente extends ORM<Long> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "documento")
	private String documento;
	
	@Column(name = "tipo")
	private String tipo;
	
	@OneToMany(mappedBy = "cliente")
	private List<Endereco> enderecos = new ArrayList<>();
	
	@CollectionTable(name = "telefone", joinColumns = @JoinColumn(name = "cliente_id"))
	@ElementCollection
	private Set<Telefone> telefones = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos = new ArrayList<>();
	
	public Cliente() {}
	
	public Cliente(Long id, String nome, String email, String documento, ClienteType tipo) {
		super.setId(id);
		this.nome = nome;
		this.email = email;
		this.documento = tipo.formata(documento);
		this.tipo = tipo.getDescricao();
	}
	
	public Cliente(ClienteDTO clienteDTO) {
		this.nome = clienteDTO.getNome();
		this.email = clienteDTO.getEmail();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public ClienteType getTipo() {
		return ClienteType.toEnum(tipo);
	}

	public void setTipo(ClienteType tipo) {
		this.tipo = tipo.getDescricao();
	}

	public Set<Telefone> getTelefones() {
		return Collections.unmodifiableSet(telefones);
	}
	
	public List<Endereco> getEnderecos() {
		return Collections.unmodifiableList(enderecos);
	}
	
	
	public List<Pedido> getPedidos() {
		return Collections.unmodifiableList(pedidos);
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
		Cliente other = (Cliente) obj;
		if (super.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!super.getId().equals(other.getId()))
			return false;
		return true;
	}

	public void addTelefone(Telefone telefone) {
		this.telefones.add(telefone);
	}
	
	public void addAllTelefone(Telefone... telefones) {
		this.telefones.addAll(Arrays.asList(telefones));
	}
	
	public void addAllTelefone(List<Telefone> telefones) {
		this.telefones.addAll(telefones);
	}
	
	public void addEndereco(Endereco endereco) {
		this.enderecos.add(endereco);
	}

	public void addAllEnderecos(Endereco... enderecos) {
		this.enderecos.addAll(Arrays.asList(enderecos));
	}

	public void addPedidos(Pedido pedido) {
		this.pedidos.add(pedido);
	}
	
	public void addAllPedidos(Pedido...pedidos) {
		this.pedidos.addAll(Arrays.asList(pedidos));
	}

	@Override
	public Cliente prepareUpdate(ORM<Long> orm) {
		Cliente cliente = (Cliente) orm;
		
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
		
		return this;
	}
}
