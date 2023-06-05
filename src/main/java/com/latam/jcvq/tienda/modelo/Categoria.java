package com.latam.jcvq.tienda.modelo;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
	
	@EmbeddedId
	private CategoriaId categoriaId;
	
	public Categoria() {}

	public Categoria(String nombre) {
		this.categoriaId = new CategoriaId(nombre,"456");
	}
	
	public String getNombre() {
		return categoriaId.getNombre();
	}
	public void setNombre(String nombre) {
		this.categoriaId.setNombre(nombre);
	}
}
