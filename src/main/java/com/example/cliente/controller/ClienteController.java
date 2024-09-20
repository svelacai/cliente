package com.example.cliente.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cliente.entity.Cliente;
import com.example.cliente.service.ClienteService;

@RestController
@RequestMapping("/Cliente")
//@Api(value = "API de Ejemplo", description = "Operaciones relacionadas con la API de ejemplo")
//@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE })

public class ClienteController {

	@Autowired
	ClienteService service;

	@GetMapping("/getClienteCedula")
	public ResponseEntity<?> consultarCedula(@RequestBody Cliente cliente) {
		Cliente consulta = service.consultaClientesPorcedula(cliente);
		if (consulta == null) {
			String messager = "registro no encontrado";
			return new ResponseEntity<>(messager, HttpStatus.BAD_REQUEST);

		}
		return new ResponseEntity<>(consulta, HttpStatus.OK);
	}

	@GetMapping("/consul")
	public ResponseEntity<?> consultarCedula() {
		List<Cliente> consulta = service.getClientes();

		return new ResponseEntity<>(consulta, HttpStatus.OK);
	}

	@PostMapping("/crearCliente")
	public ResponseEntity<?> crearCliente(@RequestBody Cliente cliente) {
		if (cliente.toString().isEmpty()) {
			String messager = "Datos vacios o en null";
			return new ResponseEntity<>(messager, HttpStatus.BAD_REQUEST);
		}
		service.crearCliente(cliente);

		return new ResponseEntity<>("Cliente creado", HttpStatus.OK);
	}

	@DeleteMapping("/eliminarCliente")
	public ResponseEntity<?> eliminarCliente(@RequestBody Cliente cliente) {
		if (cliente.toString().isEmpty()) {
			String messager = "Datos vacios o en null";
			return new ResponseEntity<>(messager, HttpStatus.BAD_REQUEST);
		}
		service.eliminarCliente(cliente);

		return new ResponseEntity<>("Exitoso", HttpStatus.OK);
	}

	@DeleteMapping("/updateCliente")
	public ResponseEntity<?> updateCliente(@RequestBody Cliente cliente) {
		if (cliente.toString().isEmpty()) {
			String messager = "Datos vacios o en null";
			return new ResponseEntity<>(messager, HttpStatus.BAD_REQUEST);
		}
		service.updateCliente(cliente);

		return new ResponseEntity<>("Actualizacion Exitosa", HttpStatus.OK);
	}

}
