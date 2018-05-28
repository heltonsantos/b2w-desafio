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

	/** Esta função é responsável por escultar todas as chamadas realizadas na rota /getTotalVendas.
	 * Ela recupera os parametros passados na consulta e verifica se a consulta está em cache. 
	 * Se sim, ela retorna o total devendas conforme a consulta passada. Se não, a requisição e lançada novamente ao controller.
	 * @author Helton Santos
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return handler - Object
	 */
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

	/** Esta função verifica se a consulta realizada está em cache e retorna.
	 * @author Helton Santos
	 * @param inicio - Data inicial
	 * @param fim - Data final
	 * @return cache - Cache do total de vendas
	 */
	private CacheTotalvendas getCacheTotalvendas(String inicio, String fim) {
		inicio = inicio.replaceAll("[.-]", "");
		fim = fim.replaceAll("[.-]", "");

		Long id = Long.parseLong(inicio+fim);

		CacheTotalvendas cache = cacheRepository.findOne(id);

		return cache;

	}
}
