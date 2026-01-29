package com.curso.springboot.jpa.asociaciones.repositories;

import org.springframework.data.repository.CrudRepository;

import com.curso.springboot.jpa.asociaciones.entities.Address;



public interface AddressRepository  extends CrudRepository<Address, Long> {

}
