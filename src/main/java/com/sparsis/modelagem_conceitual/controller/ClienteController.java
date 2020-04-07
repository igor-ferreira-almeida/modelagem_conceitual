package com.sparsis.modelagem_conceitual.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sparsis.modelagem_conceitual.domain.Cliente;
import com.sparsis.modelagem_conceitual.dto.ClienteDTO;
import com.sparsis.modelagem_conceitual.service.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Long id) {
		Cliente categoria = clienteService.findById(id);
		return ResponseEntity.ok().body(categoria);
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linePerPage", defaultValue = "24") Integer linePerPage, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy) {
		
		Page<Cliente> clientes = clienteService.findPage(page, linePerPage, direction, orderBy);
		
		return ResponseEntity.ok().body(ClienteDTO.toPageList(clientes));
	}
	
	@PostMapping
	public ResponseEntity<Void> save(@Valid @RequestBody ClienteDTO clienteDTO) {
		Cliente clienteCriado = clienteService.create(new Cliente(clienteDTO));
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(clienteCriado.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody ClienteDTO clienteDTO) {
		Cliente clienteCriado = clienteService.update(id, new Cliente(clienteDTO));
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(clienteCriado.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
