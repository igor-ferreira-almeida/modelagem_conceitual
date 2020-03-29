package com.sparsis.modelagem_conceitual.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Telefone {
	
	@Column(name = "codigo_pais")
	private String codigoPais;
	
	@Column(name = "ddd")
	private String ddd;
	
	@Column(name = "numero")
	private String numero;
	
	public Telefone() {}
	
	public Telefone(String codigoPais, String ddd, String numero) {
		this.codigoPais = codigoPais;
		this.ddd = ddd;
		this.numero = numero;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
}
