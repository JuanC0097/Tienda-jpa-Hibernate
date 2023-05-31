package com.latam.jcvq.tienda.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.latam.jcvq.tienda.modelo.Cliente;
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
public class ClienteDAO {

	private EntityManager em;
	
	public ClienteDAO(EntityManager em) {
		this.em = em;
	}
	
	public void guardar(Cliente cliente) {
		this.em.persist(cliente);
	}
	
	
	public void actualizar(Cliente cliente) {
		this.em.merge(cliente);
	}
	
	public void remover(Cliente cliente) {
		cliente=this.em.merge(cliente);
		this.em.remove(cliente);
	}
	
	public Cliente consultaPorId(Long id) {
		return em.find(Cliente.class, id);
	}
	
	public List<Cliente> consultarTodos(){
		String jqpl= "SELECT C FROM Cliente AS C";
		return em.createQuery(jqpl,Cliente.class).getResultList();
	}
	
	public List<Cliente> consultaPorNombre(String nombre){
		String jpql =" SELECT C FROM Cliente AS C WHERE C.nombre=:nombre ";
		return em.createQuery(jpql,Cliente.class).setParameter("nombre", nombre).getResultList();
	}
	
	public List<Cliente> consultaPorNombreDeCategoria(String nombre){
		String jpql="SELECT C FROM Cliente AS C WHERE C.categoria.nombre=:nombre";
		return em.createQuery(jpql,Cliente.class).setParameter("nombre", nombre).getResultList();
	}
	 
	public BigDecimal consultarPrecioPorNombreDeProducto(String nombre) {
		String jpql="SELECT C.precio FROM Cliente AS C WHERE C.nombre=:nombre";
		return em.createQuery(jpql,BigDecimal.class).setParameter("nombre", nombre).getSingleResult();
	}
	
}
