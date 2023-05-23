package com.latam.jcvq.tienda.modelo;

import java.math.BigDecimal;
//import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/*
 *1.Creacion de entidad Producto con anotacion
 *2.Generacion de id por la DB
 *3.Atributos de la entidad
 *4.Anotacion para atribtudo categoria,muchas categorias para
 *   un producto
 *5.Constructor por default, Constructor con parametros nombre
 *   descripcion,precio,categoria 
 *6.Getters and Setters
 */
@Entity
@Table(name="productos")
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;
	private String descripcion;
	private BigDecimal precio;
	//private LocalDate fechaRegistro = LocalDate.now();
	
	@ManyToOne
	private Categoria categoria;
	
	public Producto() {}

	public Producto(String nombre, String descripcion, BigDecimal precio, Categoria categoria) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
}
