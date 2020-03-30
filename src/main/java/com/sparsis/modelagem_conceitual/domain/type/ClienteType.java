package com.sparsis.modelagem_conceitual.domain.type;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.sparsis.modelagem_conceitual.domain.formatter.Formatador;
import com.sparsis.modelagem_conceitual.domain.formatter.FormatadorCNPJ;
import com.sparsis.modelagem_conceitual.domain.formatter.FormatadorCPF;

public enum ClienteType {
	PESSOA_FISICA(1, "Pessoa Física", new FormatadorCPF()), 
	PESSOA_JURIDICA(2, "Pessoa Jurídica", new FormatadorCNPJ());

	private Integer codigo;
	private String descricao;
	private Formatador formatador;
	
	private ClienteType(Integer codigo, String descricao, Formatador formatador) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.formatador = formatador;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static ClienteType toEnum(String descricao) {
		List<ClienteType> values = Arrays.asList(ClienteType.values());
		Optional<ClienteType> optionalValue = values.stream().filter(value -> value.descricao.equals(descricao)).findFirst();
		
		if(optionalValue.isPresent()) {
			optionalValue.get();
		} else {
			throw new IllegalArgumentException("Descrição de cliente inválido: " + descricao);
		}
		
		return null;
	}

	public String formata(String numero) {
		return formatador.formata(numero);
	}
	
}
