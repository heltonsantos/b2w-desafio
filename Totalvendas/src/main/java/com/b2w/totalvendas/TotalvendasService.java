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
	
	/** Esta função retorna uma lista com todas as vendas.
	 * @author Helton Santos
	 * @return vendas - Lista de vendas
	 */
	public Object[][] getVendas() {
		return repository.carregarVendas();
	}
	
	/** Esta função calcula e retorna o total de vendas de um determinado período.
	 * @author Helton Santos
	 * @param inicio - Data inicial
	 * @param fim - Data final
	 * @return total - Total de vendas
	 */
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
	
	/** Esta função salva a consulta do total de vendas em cache.
	 * @author Helton Santos
	 * @param inicio - Data inicial
	 * @param fim - Data final
	 * @param total - Total de vendas
	 */
	private void saveCache(String inicio, String fim, Double total) {
		
		inicio = inicio.replaceAll("[.-]", "");
		fim = fim.replaceAll("[.-]", "");
		
		CacheTotalvendas cache = new CacheTotalvendas(Long.parseLong(inicio+fim), total);
		
		cacheRepository.save(cache);
		
	}
}
