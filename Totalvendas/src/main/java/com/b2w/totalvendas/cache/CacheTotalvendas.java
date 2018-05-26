package com.b2w.totalvendas.cache;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CacheTotalvendas {
	
	@Id
	Long id;
	Double valor;
	
	public CacheTotalvendas() {
		
	}
	
	public CacheTotalvendas(Long id, Double valor) {
		super();
		this.id = id;
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	
	
	

}
