package com.curso.springboot.jpa.asociaciones.repositories;

import org.springframework.data.repository.CrudRepository;

import com.curso.springboot.jpa.asociaciones.entities.ClientDetail;

public interface ClientDetailsRepository extends CrudRepository<ClientDetail, Long> {

}
