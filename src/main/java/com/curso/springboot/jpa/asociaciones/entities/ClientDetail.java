package com.curso.springboot.jpa.asociaciones.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="client_details")
public class ClientDetail {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private boolean isPremium;
	
	private Integer points;

	@OneToOne/*(mappedBy="details")*/
	@JoinColumn(name="id_client")
	private Client client;
	
	public ClientDetail() {
		
	}

	public ClientDetail(boolean isPremium, Integer points) {
		super();
		this.isPremium = isPremium;
		this.points = points;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isPremium() {
		return isPremium;
	}

	public void setPremium(boolean isPremium) {
		this.isPremium = isPremium;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}
	
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	
	@Override
	public String toString() {
		return "{id=" + id + ", isPremium=" + isPremium + ", points=" + points + "}";
	}
	
	
	

}
