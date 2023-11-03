package com.prueba.service;

import java.util.Date;

import com.prueba.dto.PrecioRespuesta;

public interface PrecioService {

    /**
	 * Consulta el precio de un producto en base a la marca, el codigo
	 * de producto y una determinada fecha de consulta.
	 * En el caso de que coincidan varias tarifas en las mismas fechas,
	 * obtendra el precio de la que tenga mayor prioridad
	 * 
	 * @param marcaId Identificador de la marca
	 * @param productoId Codigo del producto
	 * @param fecha Fecha para la cual se consulta el precio
	 * @return Objeto PrecioRespuesta que contiene la información del precio,
     *  		o null si no se encuentra
	 */
	PrecioRespuesta consultarPrecio(Long marcaId, Long productoId, Date fecha);
}
