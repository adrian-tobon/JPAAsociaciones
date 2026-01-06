package com.curso.springboot.jpa.asociaciones.repositories;

import org.springframework.data.repository.CrudRepository;

import com.curso.springboot.jpa.asociaciones.entities.Invoice;

public interface InvoiceRepository extends CrudRepository<Invoice, Long>{

}
