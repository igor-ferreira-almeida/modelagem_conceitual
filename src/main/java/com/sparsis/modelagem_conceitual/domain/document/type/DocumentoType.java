package com.sparsis.modelagem_conceitual.domain.document.type;

import com.sparsis.modelagem_conceitual.domain.formatter.Formatador;
import com.sparsis.modelagem_conceitual.domain.formatter.FormatadorCNPJ;
import com.sparsis.modelagem_conceitual.domain.formatter.FormatadorCPF;

public enum DocumentoType {
	CPF("CPF - Cadastro de Pessoas Físicas", new FormatadorCPF()),
    CNPJ("CNPJ - Cadastro Nacional da Pessoa Jurídica", new FormatadorCNPJ());
	
	private String descricao;
	private Formatador formatador;

    /**
     * Construtor privado para montar a enum
     */
    private DocumentoType(String descricao, Formatador formatador) {
        this.descricao = descricao;
        this.formatador = formatador;
    }

    public String getDescricao() {
        return descricao;
    }
    
    /**
     * Formata número do documento
     */
    public String formata(String numero) {
        if (this.formatador == null) {
            return numero;
        }
        return this.formatador.formata(numero);
    }
}
