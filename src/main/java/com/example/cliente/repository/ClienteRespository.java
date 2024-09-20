package com.example.cliente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.cliente.entity.Cliente;

@Repository
public interface ClienteRespository   extends JpaRepository<Cliente, Integer>{
	
	@Query(value = "select * from clientes c where c.numero_documento  =:numeroDocumen", nativeQuery = true)
	Cliente getCliente(String numeroDocumen);

	@Query(value = "select * from clientes", nativeQuery = true)
	List<Cliente> getClientes();


}
