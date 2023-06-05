package com.latam.jcvq.tienda.prueba;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import com.latam.jcvq.tienda.Utils.JPAUtils;
import com.latam.jcvq.tienda.dao.CategoriaDAO;
import com.latam.jcvq.tienda.dao.ProductoDAO;
import com.latam.jcvq.tienda.modelo.Categoria;
import com.latam.jcvq.tienda.modelo.CategoriaId;
import com.latam.jcvq.tienda.modelo.Producto;

/*
 * 1.Metodo MAIN para probar proyecto
 * 2.Llamado del metodo registrar producto()
 * 3.Instanciaion del EntityManager
 * 4.Instanciacion de productoDAO para cosultas y producto impresion de nombre del producto
 * 5.Consulta del presion con metodo de productoDao,Impresion del precio
 * 6.METODO registrarProducto
 * 	6.1 Instanciacion de clases Categoria,Producto,EntityManager,ProductoDAO,CategoriaDAO
 *  6.2 Uso de los metodos de EntityManager para obtener las transacciones,iniciar proceso
 *  6.3 Uso de metodo guardar de las clases categoriaDAO y productoDAO.
 *  6.4 Uso de metodos de EntityManager para obtener transaccion,enviar los cambios y cerrar
 *      conexion 
 * 
 * 
 */
public class RegistroProducto {

	public static void main(String[] args) {
		registrarProducto();
		EntityManager em = JPAUtils.getEntityManager();
	    
		ProductoDAO productoDao = new ProductoDAO(em);
	    Producto producto = productoDao.consultaPorId(1l);
	    
	    System.out.println(producto.getNombre());
	    BigDecimal precio = productoDao.consultarPrecioPorNombreDeProducto("Xiaomi Redmi");
	    System.out.println(precio);
	    
	    Categoria find = em.find(Categoria.class, new CategoriaId("CELULARES", "456"));
	    System.out.println(find);
	}
	
	
	private static void registrarProducto() {
		Categoria celulares = new Categoria("CELULARES");

		Producto celular = new Producto("Xiaomi Redmi", "Muy bueno", new BigDecimal("800"), celulares);

	    EntityManager em = JPAUtils.getEntityManager();
	    ProductoDAO productoDao = new ProductoDAO(em);
        CategoriaDAO categoriaDao = new CategoriaDAO(em);
        
	    em.getTransaction().begin();
	    
	    categoriaDao.guardar(celulares);
	    productoDao.guardar(celular);	
	    
	    em.getTransaction().commit();
	    em.close();
	}
}
