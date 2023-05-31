package com.latam.jcvq.tienda.prueba;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import com.latam.jcvq.tienda.Utils.JPAUtils;
import com.latam.jcvq.tienda.dao.CategoriaDAO;
import com.latam.jcvq.tienda.dao.ClienteDAO;
import com.latam.jcvq.tienda.dao.PedidoDAO;
import com.latam.jcvq.tienda.dao.ProductoDAO;
import com.latam.jcvq.tienda.modelo.Categoria;
import com.latam.jcvq.tienda.modelo.Cliente;
import com.latam.jcvq.tienda.modelo.ItemsPedido;
import com.latam.jcvq.tienda.modelo.Pedido;
import com.latam.jcvq.tienda.modelo.Producto;

/*
 * 1.Metodo MAIN para probar proyecto
 * 2.Llamado del metodo registrar producto()
 * 3.Instanciacion del EntityManager
 * 4.Instanciacion de productoDAO traer el entityManager
 * 5.Consulta por metodo de clase dao, guardado en una variable producto
 * 6.Instanciacion con constructor con parammetros de Cliente y Pedido
 * 7.Llamado del metodo AgragarItemms, instanciacion de nuevo itemsPedido con sus parametros cantidad de producto,
 *   producto y pedido
 * 8.Consulta del presion con metodo de productoDao,Impresion del precio
 * 6.METODO registrarProducto
 * 	6.1 Instanciacion de clases Categoria,Producto,EntityManager,ProductoDAO,CategoriaDAO
 *  6.2 Uso de los metodos de EntityManager para obtener las transacciones,iniciar proceso
 *  6.3 Uso de metodo guardar de las clases categoriaDAO y productoDAO.
 *  6.4 Uso de metodos de EntityManager para obtener transaccion,enviar los cambios y cerrar
 *      conexion 
 *  
 * 
 */
public class RegistroPedido {

	public static void main(String[] args) {
		registrarProducto();
		
		EntityManager em = JPAUtils.getEntityManager();
		
		ProductoDAO productDao = new ProductoDAO(em);
		Producto producto = productDao.consultaPorId(1l);
		
		ClienteDAO clienteDao = new ClienteDAO(em);
		PedidoDAO pedidoDao = new PedidoDAO(em);
		
		Cliente cliente = new Cliente("Juan","augydwiu");
		Pedido pedido = new Pedido(cliente);
		pedido.AgregarItems(new ItemsPedido(5,producto,pedido));
		
		em.getTransaction().begin();
		
		clienteDao.guardar(cliente);
		pedidoDao.guardar(pedido);
		
		em.getTransaction().commit();
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
