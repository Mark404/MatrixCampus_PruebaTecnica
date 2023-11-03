package com.prueba.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.prueba.inicio.Application;
import com.prueba.model.Precio;

@SpringBootTest(classes=Application.class)
@AutoConfigureTestDatabase
public class PrecioRepositoryTest {

	@Autowired
	private PrecioRepository precioRepository;

	
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
    
    private void testConsultaGenericaPrecio(Long marcaId, Long productoId, String fechaConsulta,
    		double precioRespuesta) throws Exception {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date fecha = formatoFecha.parse(fechaConsulta);
        
        //Invoca el repositorio para consultar el precio
        Precio precio = precioRepository.findFirstByMarcaIdAndProductoIdAndFechaInicioLessThanEqualAndFechaFinGreaterThanEqualOrderByPrioridadDesc(
                marcaId, productoId, fecha, fecha);

        //Verifica que se obtuvo el precio esperado
        assertEquals(precioRespuesta, precio.getPrecio());
    }

}
