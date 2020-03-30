package com.sparsis.modelagem_conceitual.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	@Column(name = "data")
	private LocalDateTime data;
	
	@JsonManagedReference
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
	private Pagamento pagamento;
	
	@JsonManagedReference
	@JoinColumn(name = "cliente_id")
	@ManyToOne
	private Cliente cliente;
	
	@JsonProperty(value = "endereco_entrega")
	@JoinColumn(name = "endereco_entrega_id")
	@ManyToOne
	private Endereco enderecoEntrega;
	
	@OneToMany(mappedBy = "id.pedido")
	private Set<Item> itens = new HashSet<>();

	public Pedido() {}
	
	public Pedido(Integer id, LocalDateTime data, Cliente cliente, Endereco enderecoEntrega) {
		this.id = id;
		this.data = data;
		this.cliente = cliente;
		this.enderecoEntrega = enderecoEntrega;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(Endereco enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}
	
	

	public Set<Item> getItens() {
		return Collections.unmodifiableSet(itens);
	}

	public void addItem(Item item) {
		this.itens.add(item);
	}
	
	public void addItens(Item...itens) {
		this.itens.addAll(Arrays.asList(itens));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
