package com.latam.jcvq.tienda.dao;

import javax.persistence.EntityManager;

import com.latam.jcvq.tienda.modelo.Categoria;
/*
 * 1.Creacion de atributo EntityManager
 * 2.Creacion de Constructor con parametro del EntityManager
 * 3. METODOS
 * 	3.1 Metodo para guardar la categoria en BD con el metodo persist
 * 	3.2 Metodo para actualizar la categoria en BD con el metodo merge para cambios en la bd
 *  3.3 Metodo para remover una categoria de la BD, con el metodo remove y merge
 */
public class CategoriaDAO {

	private EntityManager em;
	
	public CategoriaDAO(EntityManager em) {
		this.em = em;
	}
	
	public void guardar(Categoria categoria) {
		this.em.persist(categoria);
	}
	
	public void actualizar(Categoria categoria) {
		this.em.merge(categoria);
	}
	
	public void remover(Categoria categoria) {
		categoria=this.em.merge(categoria);
		this.em.remove(categoria);
	}
}
