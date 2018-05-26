package com.b2w.totalvendas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.b2w.totalvendas.cache.CacheTotalvendas;
import com.b2w.totalvendas.cache.CacheTotalvendasRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class TotalvendasInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	CacheTotalvendasRepository cacheRepository;
	 
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
  
        System.out.println("\n-------- Interceptor --- ");
        System.out.println("Request URL: " + request.getRequestURL());
        
        String dataInicio = request.getParameter("data_inicio");
        String dataFim = request.getParameter("data_fim");
        
        CacheTotalvendas cache = getCacheTotalvendas(dataInicio, dataFim);
        
        if(cache != null){
	        ObjectMapper mapper = new ObjectMapper();
	        
	        Double total = cache.getValor();
	        
	        response.setContentType("application/json");
	        response.setStatus(HttpServletResponse.SC_OK);
	        response.getWriter().write(mapper.writeValueAsString(total));
	        
	        System.out.println("\n-------- Cache --- ");
	 
	        return false;
        } else{
        	 System.out.println("\n-------- No Cache --- ");
        	return true;
        }
    }
    
    private CacheTotalvendas getCacheTotalvendas(String inicio, String fim) {
    	inicio = inicio.replaceAll("[.-]", "");
		fim = fim.replaceAll("[.-]", "");
    	
		Long id = Long.parseLong(inicio+fim);
		
		CacheTotalvendas cache = cacheRepository.findOne(id);
    	
    	return cache;
    	
    }
}
