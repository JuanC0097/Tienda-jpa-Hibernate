package com.latam.jcvq.tienda.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/*
 * 1. Pedido como entidad
 * 2. Nombre de tabla pedidos
 * 3. Generacion de id automatico por la db
 * 4. Atributos y columnas en la tabla
 * 5. Generacion automatica del atributo fecha
 * 6. Atributo tipo entidad con cardinalidad muchos a uno
 * 7. Atributo tipo lista con cardinalidad muchos a muchos anicializado
 * 7. Constructor por default solicitado por la jpa
 * 8. Constructor con parametro de entidad cliente
 * 9. Metodo para agragar a la lista un itemPedido
 * 10. Getters and Setters
 */
@Entity
@Table(name = "pedidos")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	private LocalDate fecha = LocalDate.now();
	private BigDecimal valor_Total = new BigDecimal(0);
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Cliente cliente;
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemsPedido> items= new ArrayList<>();
	
	public Pedido() {}

	public Pedido(Cliente cliente) {
		this.cliente = cliente;
	}

	public void AgregarItems(ItemsPedido item) {
		item.setPedido(this);
		this.items.add(item);
		this.valor_Total = this.valor_Total.add(item.getValor());
	}
	
	public Long getId() {
		return id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getValor_Total() {
		return valor_Total;
	}

	public void setValor_Total(BigDecimal valor_Total) {
		this.valor_Total = valor_Total;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemsPedido> getItems() {
		return items;
	}

	public void setItems(List<ItemsPedido> items) {
		this.items = items;
	}
	
	
}
