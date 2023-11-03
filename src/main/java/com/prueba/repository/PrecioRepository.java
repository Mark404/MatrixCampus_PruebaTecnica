package com.prueba.repository;

import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import com.prueba.model.Precio;

public interface PrecioRepository extends JpaRepository<Precio, Long> {

    /**
	 * Consulta el precio de un producto en base a la marca, el codigo
	 * de producto y un intervalo de consulta.
	 * En el caso de que coincidan varias tarifas en las mismas fechas,
	 * obtendra el precio de la que tenga mayor prioridad
	 * 
	 * @param marcaId Identificador de la marca
	 * @param productoId Codigo del producto
	 * @param fechaInicio Fecha inicial de consulta
	 * @param fechaFin Fecha final de la consulta
	 * @return Objeto Precio que contiene la información del precio,
     *  		o null si no se encuentra
	 */
    Precio findFirstByMarcaIdAndProductoIdAndFechaInicioLessThanEqualAndFechaFinGreaterThanEqualOrderByPrioridadDesc(
            Long marcaId, Long productoId, Date fechaInicio, Date fechaFin);
}
