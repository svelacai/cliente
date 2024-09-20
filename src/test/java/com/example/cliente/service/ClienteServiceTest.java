package com.example.cliente.service;

import com.example.cliente.entity.Cliente;
import com.example.cliente.repository.ClienteRespository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClienteServiceTest {

	@InjectMocks
	private ClienteService clienteService;

	@Mock
	private ClienteRespository clienteRespository;

	private Cliente cliente;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		cliente = new Cliente();
		cliente.setId(1);
		cliente.setNumeroDocumento("12345678");
	}

	@Test
	void testConsultaClientesPorcedula() {
		when(clienteRespository.getCliente(cliente.getNumeroDocumento())).thenReturn(cliente);

		Cliente result = clienteService.consultaClientesPorcedula(cliente);

		assertEquals(cliente, result);
		verify(clienteRespository, times(1)).getCliente(cliente.getNumeroDocumento());
	}

	@Test
	void testGetClientes() {
		List<Cliente> clientes = Arrays.asList(cliente);
		when(clienteRespository.getClientes()).thenReturn(clientes);

		List<Cliente> result = clienteService.getClientes();

		assertEquals(clientes, result);
		verify(clienteRespository, times(1)).getClientes();
	}

	@Test
	void testCrearCliente() {
		clienteService.crearCliente(cliente);

		verify(clienteRespository, times(1)).save(cliente);
	}

	@Test
	void testEliminarCliente() {
		when(clienteRespository.findById(cliente.getId())).thenReturn(Optional.of(cliente));

		clienteService.eliminarCliente(cliente);

		verify(clienteRespository, times(1)).deleteById(cliente.getId());
	}

	@Test
	void testUpdateCliente() {
		clienteService.updateCliente(cliente);

		verify(clienteRespository, times(1)).save(cliente);
	}
}
