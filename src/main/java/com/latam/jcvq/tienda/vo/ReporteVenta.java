package com.latam.jcvq.tienda.vo;

import java.time.LocalDate;
/*
 * 1.Atributos de la clase con nombre de producto, Cantidad de producto y Fecha de Ultima venta
 * 2. Constructor con todos los atributos
 * 3. Getters and Setters
 * 4. Metodo to string para imprimir los valores
 */
public class ReporteVenta {

	private String nombreProducto;
	private Long CantidadProducto;
	private LocalDate FechaUltimaVenta;
	
	public ReporteVenta(String nombreProducto, Long cantidadProducto, LocalDate fechaUltimaVenta) {
		this.nombreProducto = nombreProducto;
		CantidadProducto = cantidadProducto;
		FechaUltimaVenta = fechaUltimaVenta;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public Long getCantidadProducto() {
		return CantidadProducto;
	}

	public void setCantidadProducto(Long cantidadProducto) {
		CantidadProducto = cantidadProducto;
	}

	public LocalDate getFechaUltimaVenta() {
		return FechaUltimaVenta;
	}

	public void setFechaUltimaVenta(LocalDate fechaUltimaVenta) {
		FechaUltimaVenta = fechaUltimaVenta;
	}

	@Override
	public String toString() {
		return "ReporteVenta [nombreProducto=" + nombreProducto + ", CantidadProducto=" + CantidadProducto
				+ ", FechaUltimaVenta=" + FechaUltimaVenta + "]";
	}
}
