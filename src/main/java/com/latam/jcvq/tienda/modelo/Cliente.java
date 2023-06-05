package com.latam.jcvq.tienda.modelo;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/*
 * 1. Entidad cliente
 * 2. Tabla en h2 como clientes
 * 3. Generacion de id por la db
 * 4. Atributos y columnas en la db
 * 5. Constructor por default "Solicitado por jpa"
 * 6. Constructor con parametros nombre y dni
 * 7. Getters and Setters sin set para id
 */
@Entity
@Table(name = "clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Embedded
	private DatosPersonales datosPersonales;
	
	
	public Cliente() {}
	
	public Cliente(String nombre, String dni) {
		this.datosPersonales = new DatosPersonales(nombre,dni);
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return datosPersonales.getNombre();
	}

	public void setNombre(String nombre) {
		this.datosPersonales.setNombre(nombre);
	}

	public String getDni() {
		return datosPersonales.getDni();
	}

	public void setDni(String dni) {
		this.datosPersonales.setDni(dni);
	}


	
}
