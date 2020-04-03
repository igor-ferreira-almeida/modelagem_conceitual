package com.sparsis.modelagem_conceitual.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sparsis.modelagem_conceitual.domain.status.PagamentoStatus;

@Entity
@Table(name = "pagamento_cartao")
public class PagamentoCartao extends Pagamento {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "numero_parcelas")
	private Integer numeroParcelas;

	public PagamentoCartao(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

	public PagamentoCartao() {}
	
	public PagamentoCartao(Long id, PagamentoStatus status, Pedido pedido, Integer numeroParcelas) {
		this.setId(id);
		this.setStatus(status);
		this.setPedido(pedido);
		this.numeroParcelas = numeroParcelas;
	}
	
	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}
}
