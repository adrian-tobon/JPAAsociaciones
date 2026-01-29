package com.curso.springboot.jpa.asociaciones.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.curso.springboot.jpa.asociaciones.entities.Client;

public interface ClientRepository extends CrudRepository<Client, Long>{
	
	//esta funcion solo funciona cuando se almacena en un Set en vez de un List
	@Query("select c from Client c left join fetch c.addresses left join fetch c.invoices where c.id = ?1")
	Optional<Client> getClientById(Long id);
}
