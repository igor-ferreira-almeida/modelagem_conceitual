package com.sparsis.modelagem_conceitual.domain.document;

import com.sparsis.modelagem_conceitual.domain.document.type.DocumentoType;

public class Documento {
	private String numero;
    private DocumentoType tipo;
    
    public Documento(String numero, DocumentoType tipo) {
		this.numero = numero;
		this.tipo = tipo;
	}

	public String formata() {
        return this.tipo.formata(this.numero);
    }
}
