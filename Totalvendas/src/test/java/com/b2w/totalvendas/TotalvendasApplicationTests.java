package com.b2w.totalvendas;

import static org.junit.Assert.assertEquals;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TotalvendasApplicationTests {

	@Value("${local.server.port}")
	int port;

	@Autowired
	TestRestTemplate restTemplate;

	HttpHeaders headers = new HttpHeaders();

	@Test
	public void testGetVendas() throws JSONException {
		String vendas = restTemplate.exchange(
				createURLWithPort("/totalvendas/getVendas"), 
				HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<String>() {
				}).getBody();

		String expected = "[[\"18-09-15\",191.01],[\"11-08-15\",202.38],[\"04-11-16\",73.49],[\"13-04-15\",356.43],[\"04-11-16\",173.49],[\"18-09-15\",286.19],[\"30-05-16\",84.2]]";
		JSONAssert.assertEquals(expected, vendas, false);
	}

	@Test
	public void testGetVendasLength() {
		Object[][] vendas = restTemplate.exchange(
				createURLWithPort("/totalvendas/getVendas"), 
				HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<Object[][]>() {
				}).getBody();

		assertEquals(7, vendas.length);
	}

	@Test
	public void testGetTotalVendas() throws JSONException {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(createURLWithPort("/totalvendas/getTotalVendas"))
				.queryParam("data_inicio", "01-07-15")
				.queryParam("data_fim", "30-06-16");

		String total = restTemplate.exchange(
				builder.toUriString(), 
				HttpMethod.GET, HttpEntity.EMPTY, 
				new ParameterizedTypeReference<String>() {
				}).getBody();

		String expected = "763.78";
		JSONAssert.assertEquals(expected, total, false);
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
