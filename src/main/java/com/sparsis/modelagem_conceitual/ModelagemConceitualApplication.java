package com.sparsis.modelagem_conceitual;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
import com.sparsis.modelagem_conceitual.domain.Item;
import com.sparsis.modelagem_conceitual.domain.Pagamento;
import com.sparsis.modelagem_conceitual.domain.PagamentoBoleto;
import com.sparsis.modelagem_conceitual.domain.PagamentoCartao;
import com.sparsis.modelagem_conceitual.domain.Pedido;
import com.sparsis.modelagem_conceitual.domain.Produto;
import com.sparsis.modelagem_conceitual.domain.Telefone;
import com.sparsis.modelagem_conceitual.domain.status.PagamentoStatus;
import com.sparsis.modelagem_conceitual.domain.type.ClienteType;
import com.sparsis.modelagem_conceitual.repository.CategoriaRepository;
import com.sparsis.modelagem_conceitual.repository.CidadeRepository;
import com.sparsis.modelagem_conceitual.repository.ClienteRepository;
import com.sparsis.modelagem_conceitual.repository.EnderecoRepository;
import com.sparsis.modelagem_conceitual.repository.EstadoRepository;
import com.sparsis.modelagem_conceitual.repository.ItemRepository;
import com.sparsis.modelagem_conceitual.repository.PagamentoRepository;
import com.sparsis.modelagem_conceitual.repository.PedidoRepository;
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
	
	@Autowired
	private PedidoRepository pedidoReposity;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
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
		
		Pedido pedido1 = new Pedido(null, LocalDateTime.of(2017, 9, 30, 10, 32), cliente1, endereco1);
		Pedido pedido2 = new Pedido(null, LocalDateTime.of(2017, 10, 10, 19, 35), cliente1, endereco2);
		
		
		Pagamento pagamento1 = new PagamentoCartao(null, PagamentoStatus.QUITADO, pedido1, 6);
		pedido1.setPagamento(pagamento1);
		
		Pagamento pagamento2 = new PagamentoBoleto(null, PagamentoStatus.PENDENTE, pedido2, LocalDateTime.of(2017, 10, 20, 0, 0), null);
		pedido2.setPagamento(pagamento2);
		
		
		cliente1.addAllPedidos(pedido1, pedido2);
		
		
		Item item1 = new Item(pedido1, produto1, new BigDecimal("0.00"), new BigDecimal("2000.00"), 1);
		Item item2 = new Item(pedido1, produto3, new BigDecimal("0.00"), new BigDecimal("80.00"), 2);
		Item item3 = new Item(pedido2, produto2, new BigDecimal("100.00"), new BigDecimal("800.00"), 1);
		
		pedido1.addItens(item1, item2);
		pedido2.addItem(item3);
		
		produto1.addItens(item1);
		produto2.addItens(item3);
		produto3.addItens(item2);
		
		categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2));
		produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3));
		
		estadoRepository.saveAll(Arrays.asList(estado1, estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));
		
		clienteReposity.save(cliente1);
		enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2));
		
		pedidoReposity.saveAll(Arrays.asList(pedido1, pedido2));
		
		pagamentoRepository.saveAll(Arrays.asList(pagamento1, pagamento2));
		itemRepository.saveAll(Arrays.asList(item1, item2, item3));
	}

}
