package com.b2w.totalvendas;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Validated
public class TotalvendasController {
	
	@Autowired
	TotalvendasService service;
	
	@RequestMapping("/")
    @ResponseBody
    String home() {
        return "This aplication is running...";
    }
	
	/** Esta rota permite retornar uma lista com todas as vendas.
	 * @author Helton Santos
	 */
	@RequestMapping(method = RequestMethod.GET, path="/getVendas")	
	public @ResponseBody Object[][] getVendas() {
		return service.getVendas();
	}
	
	/** Esta rota permite retornar o total de vendas em um determinado período.
	 * @author Helton Santos
	 * @param data_inicio - Data inicial
	 * @param data_fim - Data final
	 */
	@RequestMapping(method = RequestMethod.GET, path="/getTotalVendas")	
	public @ResponseBody Double getTotalVendas(@Valid @NotEmpty String data_inicio, 
			@Valid @NotEmpty String data_fim) {
		
		return service.getTotalVendas(data_inicio, data_fim);
	}

}
