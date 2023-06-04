package com.latam.jcvq.tienda.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.latam.jcvq.tienda.modelo.Pedido;
import com.latam.jcvq.tienda.vo.ReporteVenta;
/*
 * 1.Creacion de atributo EntityManager
 * 2.Creacion de Constructor con parametro del EntityManager
 * 3. METODOS
 * 	3.1 Metodo para guardar el producto en BD con el metodo persist
 * 	3.2 Metodo para actualizar el producto en BD con el metodo merge para cambios en la bd
 *  3.3 Metodo para remover un producto de la BD, con el metodo remove
 *  3.4 Metodo de consulta a BD por medio de  id y el metodo find
 *  3.5 Metodo tipo lista para colsultar todos los productos,uso de consultas jpql en la BD
 *      y los metodos createQuery y getResultList
 *  3.6 Metodo para consultar precio por nombre de cliente,uso de consulta jpql,creacion y retorno de la consulta por
 *      nombre de cliente
 *      
 *  3.7 Metodo para consultar el valor total de la venta, uso de la consulta jpql,creacion de la query y obtener el 
 *      resultado
 *  3.8 Metodo para consultar un reporte de ventas de extrayendo nombre de producto,cantidad vendidad y fecha de 
 *      ultima venta. Creacion de consulta avanzada jpql uso de SUM,MAX para sumar la cantidad de item y la ultima 
 *      fecha de comprar, obtenemos las tablas pedido,item,producto. agrupamos los nombres de producto y ordenamos 
 *      de forma descendente, retorno llamado de entity manager, uso de un arreglo de objeto y objetenemos el resultado
 *  4.Segunda forma de realizar la consulta utilizando una clase vo, metodo tipo lista de la clase vo Reporte venta,
 *    Creacion de la consulta jpql con las palabra new y la ruta de la clase (permite utilizar lenguaje java en la 
 *    consulta SQL),resto de la consulta igual al metodo anterior
 */
public class PedidoDAO {

	private EntityManager em;
	
	public PedidoDAO(EntityManager em) {
		this.em = em;
	}
	
	public void guardar(Pedido pedido) {
		this.em.persist(pedido);
	}

	public void actualizar(Pedido pedido) {
		this.em.merge(pedido);
	}
	
	public void remover(Pedido pedido) {
		pedido=this.em.merge(pedido); 
		this.em.remove(pedido);
	}
	
	public Pedido consultaPorId(Long id) {
		return em.find(Pedido.class, id);
	}
	
	public List<Pedido> consultarTodos(){
		String jqpl= "SELECT P FROM Pedido AS P";
		return em.createQuery(jqpl,Pedido.class).getResultList();
	}
	
	public BigDecimal valorTotalVenta() {
		String jpql = "SELECT SUM(p.valor_Total) FROM Pedido p";
		return em.createQuery(jpql,BigDecimal.class).getSingleResult();
	}
	
	public Double valorPromedioVendido() {
		String jpql = "Select AGV(p.valortotal) FROM Pedido p";
		return em.createQuery(jpql,Double.class).getSingleResult();
	}
	
	public List<Object[]> reporteVentas(){
		String jpql = "SELECT producto.nombre,"
				+"SUM(item.cantidad)," 
				+"MAX(pedido.fecha) " 
				+"FROM Pedido pedido "
				+"JOIN pedido.items item "
				+"JOIN item.producto producto "
				+"GROUP BY producto.nombre "
				+"ORDER BY item.cantidad DESC ";
		return em.createQuery(jpql,Object[].class).getResultList();
	}
	
	public List<ReporteVenta> reporteVentasVO(){
		String jpql = "SELECT new com.latam.jcvq.tienda.vo.ReporteVenta(producto.nombre,"
				+"SUM(item.cantidad)," 
				+"MAX(pedido.fecha)) " 
				+"FROM Pedido pedido "
				+"JOIN pedido.items item "
				+"JOIN item.producto producto "
				+"GROUP BY producto.nombre "
				+"ORDER BY item.cantidad DESC ";
		return em.createQuery(jpql,ReporteVenta.class).getResultList();
	}
	
	public Pedido consultarPedidoConCliente(Long id) {
		String jpql = "SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id=:id";
		return em.createQuery(jpql,Pedido.class).setParameter("id",id).getSingleResult();
	}
	
}
