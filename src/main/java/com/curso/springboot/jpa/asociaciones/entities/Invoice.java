package com.curso.springboot.jpa.asociaciones.entities;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="invoices")
public class Invoice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String description;
	private Long total;
	
	@ManyToOne
	@JoinColumn(name="id_client")
	private Client client;
	
	
	public Invoice() {
		
	}
	
	
	public Invoice(String description, Long total) {
		super();
		this.description = description;
		this.total = total;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}

	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}

	
	
	

	@Override
	public int hashCode() {
		return Objects.hash(description, id, total);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Invoice other = (Invoice) obj;
		return Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(total, other.total);
	}


	@Override
	public String toString() {
		return "{id=" + id + ", description=" + description + ", total=" + total + "}";
	}
	
	
	
	

}
