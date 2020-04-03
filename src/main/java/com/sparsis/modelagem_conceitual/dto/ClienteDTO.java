package com.sparsis.modelagem_conceitual.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;

import com.sparsis.modelagem_conceitual.domain.Cliente;

public class ClienteDTO {
	
	private Long id;
	@NotEmpty(message = "Nome vazio")
	@Length(min = 5, max = 120, message = "Nome deve ter entre 5 e 120 caracteres")
	private String nome;
	
	@NotEmpty(message = "E-mail vazio")
	@Email(message = "E-mail inválido")
	private String email;
	
	public ClienteDTO() {}
	
	public ClienteDTO( String nome, String email) {
		this.nome = nome;
		this.email = email;
	}
	
	public ClienteDTO(Long id, String nome, String email) {
		this.id = id;
		this.nome = nome;
		this.email = email;
	}
	
	public ClienteDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public static Page<ClienteDTO> toPageList(Page<Cliente> clientes) {
		return clientes.map(c -> new ClienteDTO(c));
	}
}
