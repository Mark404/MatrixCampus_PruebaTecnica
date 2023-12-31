package com.prueba.inicio;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.prueba.controller.PrecioController;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private PrecioController controlador;
	
	@Test
	void contextLoads() throws Exception{
		assertThat(controlador).isNotNull();
	}

}
