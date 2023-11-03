package com.prueba.service;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.dto.PrecioRespuesta;
import com.prueba.model.Precio;
import com.prueba.repository.PrecioRepository;

@Service
public class PrecioServiceImpl implements PrecioService {

	@Autowired
	PrecioRepository precioRepository;

    /**
     * Consulta el precio basado en la marca, producto y fecha proporcionados.
	 * @param marcaId Identificador de la marca
	 * @param productoId Codigo del producto
     * @param fecha Fecha para la cual se consulta el precio
     * @return Objeto PrecioRespuesta que contiene la información del precio,
     *  		o null si no se encuentra
     */
	@Override
	public PrecioRespuesta consultarPrecio(Long marcaId, Long productoId, Date fecha) {
         Precio precio = precioRepository.findFirstByMarcaIdAndProductoIdAndFechaInicioLessThanEqualAndFechaFinGreaterThanEqualOrderByPrioridadDesc(
            marcaId, productoId, fecha, fecha);
         return convertirToDTO(precio);
    }
	
    /**
     * Convierte un objeto Precio a PrecioRespuesta utilizando ModelMapper.
     * @param precio Objeto Precio para convertir
     * @return Objeto PrecioRespuesta convertido, o null si el precio es null.
     */
	private PrecioRespuesta convertirToDTO(Precio precio) {
		//Verifica si el precio es null
		if(precio == null) {
			return null;
		}
		//Se utiliza ModelMapper para mapear el objeto Precio a PrecioRespuesta
		ModelMapper modelMapper = new ModelMapper();
		PrecioRespuesta precioRespuesta = modelMapper.map(precio, PrecioRespuesta.class);
		return precioRespuesta;
	}
}
