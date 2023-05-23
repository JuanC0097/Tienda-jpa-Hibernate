package com.latam.jcvq.tienda.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.latam.jcvq.tienda.modelo.Producto;
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
 *  3.6 Metodo para colsutar por nombre de producto,uso de consulta jpql y el metodo
 *      setParameter.
 *  3.7 Metodo para consultar por nombre de categoria,udo de consulta jpql 
 *  3.8 Metodo para consultar precio por nombre de producto,uso de consulta jpql 
 */
public class ProductoDAO {

	private EntityManager em;
	
	public ProductoDAO(EntityManager em) {
		this.em = em;
	}
	
	public void guardar(Producto producto) {
		this.em.persist(producto);
	}
	
	
	public void actualizar(Producto producto) {
		this.em.merge(producto);
	}
	
	public void remover(Producto producto) {
		producto=this.em.merge(producto);
		this.em.remove(producto);
	}
	
	public Producto consultaPorId(Long id) {
		return em.find(Producto.class, id);
	}
	
	public List<Producto> consultarTodos(){
		String jqpl= "SELECT P FROM Producto AS P";
		return em.createQuery(jqpl,Producto.class).getResultList();
	}
	
	public List<Producto> consultaPorNombre(String nombre){
		String jpql =" SELECT P FROM Producto AS P WHERE P.nombre=:nombre ";
		return em.createQuery(jpql,Producto.class).setParameter("nombre", nombre).getResultList();
	}
	
	public List<Producto> consultaPorNombreDeCategoria(String nombre){
		String jpql="SELECT p FROM Producto AS p WHERE p.categoria.nombre=:nombre";
		return em.createQuery(jpql,Producto.class).setParameter("nombre", nombre).getResultList();
	}
	 
	public BigDecimal consultarPrecioPorNombreDeProducto(String nombre) {
		String jpql="SELECT P.precio FROM Producto AS P WHERE P.nombre=:nombre";
		return em.createQuery(jpql,BigDecimal.class).setParameter("nombre", nombre).getSingleResult();
	}
	
}
