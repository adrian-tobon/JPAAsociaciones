package com.curso.springboot.jpa.asociaciones.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="clients")
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String lastname;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	//@JoinColumn(name = "client_id")
	@JoinTable(name = "addresses_by_clients", 
			   joinColumns = @JoinColumn(name = "id_client"),
			   inverseJoinColumns = @JoinColumn(name = "id_address"),
			   uniqueConstraints = @UniqueConstraint(columnNames = {"id_address"}))
	private List<Address> addresses;	
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "client")
	private List<Invoice> invoices;
	
	
	public Client() {
		addresses = new ArrayList<>();
		invoices = new ArrayList<>();
	
	}
	
	public Client(String name, String lastname) {
		this();
		this.name = name;
		this.lastname = lastname;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	
	
	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

		
	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}

	@Override
	public String toString() {
		return "{id=" + id + ", name=" + name + ", lastname=" + lastname + ", addresses= " + addresses + ", invoices= "  + invoices +  "}";
	}
	

}
