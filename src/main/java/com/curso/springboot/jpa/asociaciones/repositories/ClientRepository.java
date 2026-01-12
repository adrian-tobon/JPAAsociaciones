package com.curso.springboot.jpa.asociaciones.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.curso.springboot.jpa.asociaciones.entities.Client;

public interface ClientRepository extends CrudRepository<Client, Long>{
	
	@Query("select c from Client c  join fetch c.addresses where c.id = ?1")
	Optional<Client> getClientById(Long id);

}
