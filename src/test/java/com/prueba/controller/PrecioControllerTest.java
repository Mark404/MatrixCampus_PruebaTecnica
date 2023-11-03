package com.prueba.controller;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.prueba.dto.PrecioRespuesta;
import com.prueba.inicio.Application;
import com.prueba.service.PrecioService;

@SpringBootTest(classes=Application.class)
@AutoConfigureMockMvc
public class PrecioControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PrecioService precioService;
	
	
	@Test
	public void testConsultarPrecio_Dia14Hora10_respuestaTarifa1() throws Exception {
		testConsultaGenericaPrecio(1L,35455L, "2020-06-14 10:00:00", 35.50);
	}
	
	@Test
	public void testConsultarPrecio_Dia14Hora16_respuestaTarifa2() throws Exception {
		testConsultaGenericaPrecio(1L,35455L, "2020-06-14 16:00:00", 25.45);
	}
	
	@Test
	public void testConsultarPrecio_Dia14Hora21_respuestaTarifa1() throws Exception {
		testConsultaGenericaPrecio(1L,35455L, "2020-06-14 21:00:00", 35.50);
	}
	
	@Test
	public void testConsultarPrecio_Dia15Hora10_respuestaTarifa3() throws Exception {
		testConsultaGenericaPrecio(1L,35455L, "2020-06-15 10:00:00", 30.50);
	}
	
	@Test
	public void testConsultarPrecio_Dia16Hora21_respuestaTarifa4() throws Exception {
		testConsultaGenericaPrecio(1L,35455L, "2020-06-16 21:00:00", 38.95);
	}
	
	@Test
	void testConsultarPrecio_productoInexistente_respuestaSinContenido() throws Exception {
		//Configuramos los datos de prueba para el supuesto de no encontrar ningun precio
	    Long marcaId = 1L;
	    Long productoId = 999L; //NO existe el producto
	    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date fecha = formatoFecha.parse("2022-06-14 18:30:00");
	    
	    //PrecioService mock devuelve null, indicando que no se encontraron datos
	    when(precioService.consultarPrecio(productoId, productoId, fecha)).thenReturn(null);

	    //Se realiza la solicitud GET al endpoint y verifica que se recibe un código 204 (Sin contenido)
	    mockMvc.perform(get("/consultar-precio")
	    		.param("marcaId", String.valueOf(marcaId))
	            .param("productoId", String.valueOf(productoId))
	            .param("fecha", formatoFecha.format(fecha)))
	            .andExpect(status().isNoContent())
	            .andReturn();
	}	

	private void testConsultaGenericaPrecio(Long marcaId, Long productoId, String fechaConsulta,
			double precioRespuesta) throws Exception {
	    //Establece el formato adecuado para la fecha
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date fecha = formatoFecha.parse(fechaConsulta);

	    //Crea el objeto de respuesta esperada
	    PrecioRespuesta precioEsperado = new PrecioRespuesta();
	    precioEsperado.setMarcaId(marcaId);
	    precioEsperado.setProductoId(productoId);
	    precioEsperado.setPrecio(precioRespuesta);

	    //PrecioService mock devuelve el objeto de respuesta esperado
	    when(precioService.consultarPrecio(marcaId, productoId, fecha)).thenReturn(precioEsperado);

	    //Realizar la solicitud GET al endpoint y verifica la respuesta
	    mockMvc.perform(get("/consultar-precio")
	    		.param("marcaId", String.valueOf(marcaId))
	            .param("productoId", String.valueOf(productoId))
	            .param("fecha", formatoFecha.format(fecha)))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.marcaId").value(marcaId))
	            .andExpect(jsonPath("$.productoId").value(productoId))
	            .andExpect(jsonPath("$.precio").value(precioRespuesta))
	            .andReturn();
	}

}


