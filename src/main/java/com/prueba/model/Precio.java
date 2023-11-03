package com.prueba.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Table(name = "precios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Precio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long marcaId;
	private Date fechaInicio;
	private Date fechaFin;
	private Long tarifaPrecio;
	private Long productoId;
	private Long prioridad;
	private double precio;
	private String moneda;
}
