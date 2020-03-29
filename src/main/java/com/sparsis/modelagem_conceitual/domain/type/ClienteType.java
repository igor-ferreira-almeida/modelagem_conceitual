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
	private String nome;
	private Formatador formatador;
	
	private ClienteType(Integer codigo, String nome, Formatador formatador) {
		this.codigo = codigo;
		this.nome = nome;
		this.formatador = formatador;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}
	
	public static ClienteType toEnum(Integer codigo) {
		List<ClienteType> values = Arrays.asList(ClienteType.values());
		Optional<ClienteType> optionalValue = values.stream().filter(value -> value.codigo.equals(codigo)).findFirst();
		
		if(optionalValue.isPresent()) {
			optionalValue.get();
		} else {
			throw new IllegalArgumentException("Código de cliente inválido: " + codigo);
		}
		
		return null;
	}

	public String formata(String numero) {
		return formatador.formata(numero);
	}
	
}
