package com.sparsis.modelagem_conceitual.domain.formatter;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

public class FormatadorCPF implements Formatador {

	@Override
	public String formata(String numero) {
		try {
	        MaskFormatter mask = new MaskFormatter("###.###.###-##");
	        mask.setValueContainsLiteralCharacters(false);
	        return  mask.valueToString(numero);
	    } catch (ParseException e) {
	        throw new IllegalArgumentException();
	    }
	}
}
