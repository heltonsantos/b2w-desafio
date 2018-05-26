package com.b2w.totalvendas;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2w.totalvendas.cache.CacheTotalvendas;
import com.b2w.totalvendas.cache.CacheTotalvendasRepository;
import com.b2w.totalvendas.util.DateConverter;

@Service
public class TotalvendasService {
	
	@Autowired
	TotalvendasRepository repository;
	
	@Autowired
	CacheTotalvendasRepository cacheRepository;
	
	public Object[][] getVendas() {
		return repository.carregarVendas();
	}
	
	public Double getTotalVendas(String inicio, String fim) {
		Object[][] vendas = repository.carregarVendas();
		Double total = new Double(0);

		for(Object[] registros:vendas){
			Date dataVenda = DateConverter.read(registros[0].toString());
			
			if(dataVenda.after(DateConverter.read(inicio)) && 
					dataVenda.before(DateConverter.read(fim))){
				total += (Double) registros[1];
			}
		}
		
		saveCache(inicio, fim, total);
		
		return total;

	}
	
	private void saveCache(String inicio, String fim, Double total) {
		
		inicio = inicio.replaceAll("[.-]", "");
		fim = fim.replaceAll("[.-]", "");
		
		CacheTotalvendas cache = new CacheTotalvendas(Long.parseLong(inicio+fim), total);
		
		cacheRepository.save(cache);
		
	}
}
