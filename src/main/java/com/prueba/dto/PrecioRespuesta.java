package com.prueba.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrecioRespuesta {
	private Long id;
	private Long marcaId;
	private Date fechaInicio;
	private Date fechaFin;
	private Long tarifaPrecio;
	private Long productoId;
	private double precio;
}
