package com.b2w.totalvendas.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.b2w.totalvendas.TotalvendasInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
 
	@Autowired
	TotalvendasInterceptor requestInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {  
      registry.addInterceptor(requestInterceptor)//
            .addPathPatterns("/getTotalVendas");
 
   }
}