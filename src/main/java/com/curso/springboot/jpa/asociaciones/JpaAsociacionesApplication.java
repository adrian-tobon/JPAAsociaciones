package com.curso.springboot.jpa.asociaciones;

import java.util.Optional;
import java.util.Scanner;

import org.hibernate.internal.build.AllowSysOut;
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
		// manyToOne();
		// findClientById();
		
		createInvoice();
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

	
	@Transactional
	public void findClientById() {
	
		Scanner scanner = new Scanner(System.in);
		System.out.println("----------Busqueda de cliente---------------");
		System.out.println("Ingrese el id del cliente:");
		Long id = scanner.nextLong();
		
		Optional<Client> optClient = clientRepository.findById(id);
		
		/*optClient.ifPresentOrElse(c -> {
				System.out.println(c);
			}, () -> {
				System.out.println("cliente no existe");
		});*/
		
		if(optClient.isPresent()) {
			Client client = optClient.orElseThrow();
			System.out.println(client);
		}else {
			System.out.println("cliente no existe");
		}
		
		scanner.close();
	
	}
	
	@Transactional
	public void createInvoice() {		
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("----------Busqueda de cliente---------------");
		System.out.println("Ingrese el id del cliente:");
		Long id = scanner.nextLong();
		
		Optional<Client> optClient = clientRepository.findById(id);	
		
		if(optClient.isPresent()) {
			Client client = optClient.orElseThrow();
			System.out.println(client);
			System.out.println("----------Creacion de factura---------------");
			System.out.println("Ingrese la descripcion de la factura:");
 			scanner.nextLine();
 			String description = scanner.nextLine();
 			System.out.println("Ingrese el total de la factura:");
 			Long total = scanner.nextLong();
			
			Invoice invoice = new Invoice(description,total);
			invoice.setClient(client);
			
			Invoice invoiceDB = invoiceRepository.save(invoice);
			System.out.println("Factura creada");
			System.out.println(invoiceDB);
			
		}else {
			System.out.println("Cliente no existe");
		}
		
		scanner.close();
		
	}
}
