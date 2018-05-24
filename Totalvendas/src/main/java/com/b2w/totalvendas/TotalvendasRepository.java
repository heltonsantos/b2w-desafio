package com.b2w.totalvendas;

import java.io.IOException;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.b2w.totalvendas.util.FileLoader;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class TotalvendasRepository {
	
	@Cacheable("vendas")
	public Object[][] carregarVendas() {
		String response;
		Object[][] parsed = null;

		try {
			response = FileLoader.read("classpath:vendas.json");
			parsed = new ObjectMapper().readValue(response, Object[][].class);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return parsed;
	}


}
