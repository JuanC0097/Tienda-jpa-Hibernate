package com.latam.jcvq.tienda.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
 *  3.8 Metodo para consultar precio por nombre de producto,uso de NamedQuery instanciada en entidad Producto,consulta 
 *  	retorno uso del metodo CreateNadQuery con parametros de entidad a la cual se realiza la consulta y el metodo 
 *      consultaDeprecio de esta misma, segundo parametro tipo de retorno de la consulta
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
		return em.createNamedQuery("Producto.consultaDePrecio", BigDecimal.class).setParameter("nombre", nombre).getSingleResult();
	}
	
	public List<Producto> consultarPorParametro(String nombre,BigDecimal precio,LocalDate fecha){
		StringBuilder jpql= new StringBuilder("SELECT p FROM Producto p WHERE 1=1 ");
				if (nombre!=null && !nombre.trim().isEmpty()) {
					jpql.append("AND p.nombre=:nombre ");
				}
				if (precio!=null && !precio.equals(new BigDecimal(0))) {
					jpql.append("AND p.precio=:precio ");
				}
				if (fecha!=null) {
					jpql.append("AND p.fechaRegistro=:fecha");
				}
				TypedQuery<Producto> query = em.createQuery(jpql.toString(),Producto.class);
				if (nombre!=null && !nombre.trim().isEmpty()) {
					query.setParameter("nombre", nombre);
				}
				if (precio!=null && !precio.equals(new BigDecimal(0))) {
					query.setParameter("precio", precio);
				}
				if (fecha!=null) {
					query.setParameter("fechaRegistro", fecha);
				}
				
				return query.getResultList();
	}
	
	public List<Producto> consultarPorParametroConApiCriteria(String nombre,BigDecimal precio,LocalDate fecha){
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Producto> query = builder.createQuery(Producto.class);
		Root<Producto> from = query.from(Producto.class);
		
		
		Predicate filtro = builder.and();

		if (nombre!=null && !nombre.trim().isEmpty()) {
			filtro= builder.and(filtro,builder.equal(from.get("nombre"), nombre));
		}
		if (precio!=null && !precio.equals(new BigDecimal(0))) {
			filtro= builder.and(filtro,builder.equal(from.get("precio"), precio));
		}
		if (fecha!=null) {
			filtro= builder.and(filtro,builder.equal(from.get("fechaRegistro"), fecha ));
		}
		query = query.where(filtro);
		return em.createQuery(query).getResultList();
	}
}
