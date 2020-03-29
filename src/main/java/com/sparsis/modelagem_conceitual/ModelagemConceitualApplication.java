package com.sparsis.modelagem_conceitual;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sparsis.modelagem_conceitual.domain.Categoria;
import com.sparsis.modelagem_conceitual.domain.Produto;
import com.sparsis.modelagem_conceitual.repository.CategoriaRepository;
import com.sparsis.modelagem_conceitual.repository.ProdutoRepository;

@SpringBootApplication
public class ModelagemConceitualApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ModelagemConceitualApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria categoria1 = new Categoria(null, "Informática");
		Categoria categoria2 = new Categoria(null, "Escritório");
		
		
		
		Produto produto1 = new Produto(null, "Computador", new BigDecimal("2000"));
		Produto produto2 = new Produto(null, "Impressora", new BigDecimal("800"));
		Produto produto3 = new Produto(null, "Mouse", new BigDecimal("80"));
		
		categoria1.setProdutos(Arrays.asList(produto1, produto2, produto3));
		categoria2.setProdutos(Arrays.asList(produto2));
		
		produto1.setCategorias(Arrays.asList(categoria1));
		produto2.setCategorias(Arrays.asList(categoria1, categoria2));
		produto3.setCategorias(Arrays.asList(categoria1));
		
		categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2));
		produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3));
	}

}
