package com.curso.springboot.jpa.asociaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.curso.springboot.jpa.asociaciones.entities.Client;
import com.curso.springboot.jpa.asociaciones.entities.Invoice;
import com.curso.springboot.jpa.asociaciones.repositories.ClientRepository;
import com.curso.springboot.jpa.asociaciones.repositories.InvoiceRepository;

import jakarta.transaction.Transactional;

@SpringBootApplication
public class JpaAsociacionesApplication implements CommandLineRunner {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private InvoiceRepository invoiceRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaAsociacionesApplication.class, args);
	}

	@SuppressWarnings("null")
	@Override
	public void run(String... args) throws Exception {
		manyToOne();
	}
	
	@Transactional
	public void manyToOne() {
		
		Client client = new Client("John","Doe");
		clientRepository.save(client);
		
		Invoice invoice = new Invoice("Compras de oficina",220000l);
		invoice.setClient(client);
		
		Invoice invoiceDB = invoiceRepository.save(invoice);
		
		System.out.println(invoiceDB);
		
	}

}
