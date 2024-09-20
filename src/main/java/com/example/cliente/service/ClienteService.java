package com.example.cliente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cliente.entity.Cliente;
import com.example.cliente.repository.ClienteRespository;

@Service
public class ClienteService {

	@Autowired
	ClienteRespository repository;

	public Cliente consultaClientesPorcedula(Cliente cliente) {

		Cliente consulta = repository.getCliente(cliente.numeroDocumento);
		return consulta;

	}

	public List<Cliente> getClientes() {

		List<Cliente> consulta = repository.getClientes();
		return consulta;

	}

}
