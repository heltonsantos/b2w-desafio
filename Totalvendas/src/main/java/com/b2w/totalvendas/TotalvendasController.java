package com.b2w.totalvendas;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TotalvendasController {
	
	@Autowired
	TotalvendasService service;
	
	@RequestMapping("/")
    @ResponseBody
    String home() {
        return "This aplication is running...";
    }
	
	@RequestMapping(method = RequestMethod.GET, path="/getVendas")	
	public @ResponseBody Object[][] getVendas() {
		return service.getVendas();
	}
	
	/*http://localhost:9000/totalvendas/getTotalVendas?data_inicio=01-07-15&data_fim=30-06-16*/
	
	@RequestMapping(method = RequestMethod.GET, path="/getTotalVendas")	
	public @ResponseBody Double getTotalVendas(@Valid @NotNull String data_inicio, 
			@Valid @NotNull String data_fim) {
		
		return service.getTotalVendas(data_inicio, data_fim);
	}

}
