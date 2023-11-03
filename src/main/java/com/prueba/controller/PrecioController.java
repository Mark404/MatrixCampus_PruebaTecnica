package com.prueba.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.dto.PrecioRespuesta;
import com.prueba.service.PrecioService;

@RestController
public class PrecioController {

	@Autowired
	PrecioService precioService;

    @GetMapping(value="consultar-precio", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PrecioRespuesta> consultarPrecio(
    		@RequestParam Long marcaId,
    		@RequestParam Long productoId,
            @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date fecha) {
    	//Invoca el servicio para obtener el precio para los parametros de consulta 
    	PrecioRespuesta precioRespuesta = precioService.consultarPrecio(marcaId, productoId, fecha);
    	 
    	 if (precioRespuesta == null) {
    		 //Si NO encuentra un precio responde indicando que no hay contenido (estado 204)
    		 return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    	 }else {
    		 //Si encuentra un precio lo devuelve
    		 return ResponseEntity.ok(precioRespuesta);
    	 }
    }
}
