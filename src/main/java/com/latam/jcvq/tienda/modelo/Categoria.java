package com.latam.jcvq.tienda.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * 1.Creacion de clase como entidad USO DE ANOTACION
 * 2.Identificacion de tabla como categorias USO DE ANOTACION
 * 3.Generacion de id por la DB
 * 4.Atributos de la entidad
 * 5.Constructor x default,Constructor con parametro nombre
 * 6.Getters y Setters
 */
@Entity
@Table(name="categorias")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	
	
	public Categoria() {}

	public Categoria(String nombre) {
		this.nombre = nombre;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
