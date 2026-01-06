package com.curso.springboot.jpa.asociaciones.repositories;

import org.springframework.data.repository.CrudRepository;

import com.curso.springboot.jpa.asociaciones.entities.Client;

public interface ClientRepository extends CrudRepository<Client, Long>{

}
