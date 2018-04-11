package com.jcpv.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringConfigServerApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringConfigServerApplicationTests {

	@Value("${local.server.port}")
	private int port = 0;

	@Test
	public void contextLoads() {
	}

	@Test
	public void configurationAvailableDev() {
		ResponseEntity<Map> entity = new TestRestTemplate().getForEntity(
				"http://localhost:" + port + "/config-server-client/development", Map.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
	}

	@Test
	public void configurationAvailableProd() {
		ResponseEntity<Map> entity = new TestRestTemplate().getForEntity(
				"http://localhost:" + port + "/config-server-client/production", Map.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
	}

	@Test
	public void configurationAvailablePostDev() {
		MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
		ResponseEntity<Map> entity = new TestRestTemplate().postForEntity(
				"http://localhost:" + port + "/config-server-client/development", form, Map.class);
		assertEquals(HttpStatus.METHOD_NOT_ALLOWED, entity.getStatusCode());
	}

	@Test
	public void configurationAvailablePostProd() {
		MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
		ResponseEntity<Map> entity = new TestRestTemplate().postForEntity(
				"http://localhost:" + port + "/config-server-client/production", form, Map.class);
		assertEquals(HttpStatus.METHOD_NOT_ALLOWED, entity.getStatusCode());
	}

}
