package com.example.cliente.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.cliente.entity.Cliente;
import com.example.cliente.service.ClienteService;

class ClienteControllerTest {

	@InjectMocks
	private ClienteController clienteController;

	@Mock
	private ClienteService clienteService;

	private Cliente cliente;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		cliente = new Cliente();
		cliente.setId(1);
		cliente.setNumeroDocumento("12345678");
	}

	@Test
	void testConsultarCedula_ClienteEncontrado() {
		when(clienteService.consultaClientesPorcedula(cliente)).thenReturn(cliente);

		ResponseEntity<?> response = clienteController.consultarCedula(cliente);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(cliente, response.getBody());
	}

	@Test
	void testConsultarCedula_ClienteNoEncontrado() {
		when(clienteService.consultaClientesPorcedula(cliente)).thenReturn(null);

		ResponseEntity<?> response = clienteController.consultarCedula(cliente);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("registro no encontrado", response.getBody());
	}

	@Test
	void testConsultarCedulaSinParametro() {
		List<Cliente> clientes = Collections.singletonList(cliente);
		when(clienteService.getClientes()).thenReturn(clientes);

		ResponseEntity<?> response = clienteController.consultarCedula();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(clientes, response.getBody());
	}

	@Test
	void testCrearCliente_DatosValidos() {
		ResponseEntity<?> response = clienteController.crearCliente(cliente);

		verify(clienteService, times(1)).crearCliente(cliente);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Cliente creado", response.getBody());
	}
	
	@Test
	void testEliminarCliente_DatosValidos() {
	    ResponseEntity<?> response = clienteController.eliminarCliente(cliente);

	    verify(clienteService, times(1)).eliminarCliente(cliente);
	    
	    assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals("Exitoso", response.getBody());
	}



	@Test
	void testUpdateCliente_DatosValidos() {
	    ResponseEntity<?> response = clienteController.updateCliente(cliente);

	    // Verificar que el servicio fue llamado
	    verify(clienteService, times(1)).updateCliente(cliente);
	    
	    //Verificar la respuesta
	    assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals("Actualizacion Exitosa", response.getBody());
	}




}
