package com.sparsis.modelagem_conceitual.domain.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum PagamentoStatus {
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private Integer codigo;
	private String descricao;
	
	private PagamentoStatus(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static PagamentoStatus toEnum(Integer codigo) {
		List<PagamentoStatus> values = Arrays.asList(PagamentoStatus.values());
		Optional<PagamentoStatus> optionalValue = values.stream().filter(value -> value.codigo.equals(codigo)).findFirst();
		
		if(optionalValue.isPresent()) {
			optionalValue.get();
		} else {
			throw new IllegalArgumentException("Código de pagamento inválido: " + codigo);
		}
		
		return null;
	}
	
	public static PagamentoStatus toEnum(String descricao) {
		List<PagamentoStatus> values = Arrays.asList(PagamentoStatus.values());
		Optional<PagamentoStatus> optionalValue = values.stream().filter(value -> value.descricao.equals(descricao)).findFirst();
		
		if(optionalValue.isPresent()) {
			optionalValue.get();
		} else {
			throw new IllegalArgumentException("Descrição de pagamento inválido: " + descricao);
		}
		
		return null;
	}
}
