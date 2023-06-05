package com.latam.jcvq.tienda.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
/*
 * 1.Uso de anotacion para suprimir warning
 * 1.1 Uso de anotacion Entity para marcar la clase como entidad
 * 1.2 Uso de la anotacion Table, para renombrar tabla en DB
 * 1.3 Uso de Named Queries con la anotacion NamedQuery, con parametros de: 
 * 		1.3.1 el nombre de la consulta con la clase a la que pertenece
 * 		1.3.2 la consulta a realizar
 *2.Creacion de entidad Producto con anotacion
 *3.Generacion de id por la DB
 *4.Atributos de la entidad
 *5.Anotacion para atribtudo categoria,muchas categorias para
 *   un producto
 *6.Constructor por default, Constructor con parametros nombre
 *   descripcion,precio,categoria 
 *7.Getters and Setters
 */
@SuppressWarnings("all")
@Entity
@Table(name="productos")
@NamedQuery(name = "Producto.consultaDePrecio", query="SELECT P.precio FROM Producto AS P WHERE P.nombre=:nombre")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;
	private String descripcion;
	private BigDecimal precio;
	private LocalDate fechaRegistro = LocalDate.now();
	
	@ManyToOne(fetch = FetchType.LAZY)
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
