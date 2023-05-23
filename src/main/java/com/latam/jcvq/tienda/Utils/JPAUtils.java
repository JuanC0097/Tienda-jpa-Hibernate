package com.latam.jcvq.tienda.Utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/*
 * Clase encargada en instanciar el EntityManager
 */
public class JPAUtils {

	private static EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("tienda");
	
	public static EntityManager getEntityManager() {
		return FACTORY.createEntityManager();
	}
}
