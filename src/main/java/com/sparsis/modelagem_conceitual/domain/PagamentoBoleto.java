package com.sparsis.modelagem_conceitual.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sparsis.modelagem_conceitual.domain.status.PagamentoStatus;

@Entity
@Table(name = "pagamento_boleto")
public class PagamentoBoleto extends Pagamento {
	private static final long serialVersionUID = 1L;

	@JsonProperty(value = "data_vencimento")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataVencimento;

	@JsonProperty(value = "data_pagamento")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataPagamento;

	public PagamentoBoleto() {
	}

	public PagamentoBoleto(Long id, PagamentoStatus status, Pedido pedido, LocalDateTime dataVencimento,
			LocalDateTime dataPagamento) {
		this.setId(id);
		this.setStatus(status);
		this.setPedido(pedido);
		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataVencimento;
	}

	public LocalDateTime getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDateTime dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public LocalDateTime getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDateTime dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

}
