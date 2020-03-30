package com.sparsis.modelagem_conceitual.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ItemPK id = new ItemPK();
	
	@Column(name = "desconto")
	private BigDecimal desconto;
	
	@Column(name = "preco")
	private BigDecimal preco;
	
	@Column(name = "quantidade")
	private Integer quantidade;

	public Item() {}
	
	public Item(Pedido pedido, Produto produto, BigDecimal desconto, BigDecimal preco, Integer quantidade) {
		this.id = new ItemPK(pedido, produto);
		this.desconto = desconto;
		this.preco = preco;
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return id.getProduto();
	}

	public Pedido getPedido() {
		return id.getPedido();
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
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
		Item other = (Item) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
