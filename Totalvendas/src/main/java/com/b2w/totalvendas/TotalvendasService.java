package com.b2w.totalvendas;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2w.totalvendas.util.DateConverter;

@Service
public class TotalvendasService {
	
	@Autowired
	TotalvendasRepository repository;
	
	public Object[][] getVendas() {
		return repository.carregarVendas();
	}
	
	public Double getTotalVendas(Date inicio, Date fim) {
		Object[][] vendas = repository.carregarVendas();
		Double total = new Double(0);

		for(Object[] registros:vendas){
			Date dataVenda = DateConverter.read(registros[0].toString());
			
			if(dataVenda.after(inicio) && dataVenda.before(fim)){
				total += (Double) registros[1];
			}
		}
		
		return total;

	}
}
