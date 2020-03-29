package com.sparsis.modelagem_conceitual;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sparsis.modelagem_conceitual.domain.Categoria;
import com.sparsis.modelagem_conceitual.domain.Cidade;
import com.sparsis.modelagem_conceitual.domain.Cliente;
import com.sparsis.modelagem_conceitual.domain.Endereco;
import com.sparsis.modelagem_conceitual.domain.Estado;
import com.sparsis.modelagem_conceitual.domain.Produto;
import com.sparsis.modelagem_conceitual.domain.Telefone;
import com.sparsis.modelagem_conceitual.domain.type.ClienteType;
import com.sparsis.modelagem_conceitual.repository.CategoriaRepository;
import com.sparsis.modelagem_conceitual.repository.CidadeRepository;
import com.sparsis.modelagem_conceitual.repository.ClienteRepository;
import com.sparsis.modelagem_conceitual.repository.EnderecoRepository;
import com.sparsis.modelagem_conceitual.repository.EstadoRepository;
import com.sparsis.modelagem_conceitual.repository.ProdutoRepository;

@SpringBootApplication
public class ModelagemConceitualApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private ClienteRepository clienteReposity;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
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
		
		Estado estado1 = new Estado(null, "São Paulo");
		Estado estado2 = new Estado(null, "Minas Gerais");
		
		Cidade cidade1 = new Cidade(null, "São Paulo", estado1);
		Cidade cidade2 = new Cidade(null, "Campinas", estado1);
		
		Cidade cidade3 = new Cidade(null, "Uberlândia", estado2);
		
		estado1.setCidades(Arrays.asList(cidade1, cidade2));
		estado2.setCidades(Arrays.asList(cidade3));
		
		Cliente cliente1 = new Cliente(null, "Maria Silva", "maria.silva@sparsis.com", "99999999999", ClienteType.PESSOA_FISICA);
		Telefone telefone1 = new Telefone("+55", "11", "99999999");
		Telefone telefone2 = new Telefone("+55", "11", "77777777");
		
		cliente1.addAllTelefone(telefone1, telefone2);
		
		Endereco endereco1 = new Endereco(null, "Rua das Flores", 300, "Apartamento 303", "Jardim", "38220834", cliente1, cidade3);
		Endereco endereco2 = new Endereco(null, "Avenida Matos", 105, "Sala 800", "Centro", "38777012", cliente1, cidade1);
		
		cliente1.addAllEnderecos(endereco1, endereco2);
		
		
		categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2));
		produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3));
		
		estadoRepository.saveAll(Arrays.asList(estado1, estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));
		
		clienteReposity.save(cliente1);
		enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2));
	}

}
