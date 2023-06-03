package com.latam.jcvq.tienda.prueba;

import java.math.BigDecimal;
import java.util.List;

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
import com.latam.jcvq.tienda.vo.ReporteVenta;

/*
 * 1.Metodo MAIN
 * 2.Llamado del metodo registrar producto()
 * 3.Instanciacion del EntityManager
 * 4.Instanciacion de productoDAO traer el entityManager
 * 5.Consulta por metodo de clase dao, guardado en una variable producto
 * 6.Instanciacion dao de Cliente y Pedido, como parameto en EntityManager
 * 7.Instanciacion de nuevo Cliente y pedido, uso del metodo Agregar item,con parametro la entidad ItemsPedido,el
 *   producto y el pedido
 * 8. Inicio de transaccion con db,obtenermos la transaccion he iniciamos, llamado de metodos dao para guardar en 
 *    clienteDao y pedidoDao, obtenemos de nuevo la transaccion y commit para enviar los cambios
 * 9. Uso de metodo Dao de pedido para consultar el valor total vendido,guardado en una variable valor total
 * 10.Impresion en consola de este valor
 *    
 * 11.METODO registrarProducto()
 * 	11.1 Instanciacion de clases Categoria,Producto,EntityManager,ProductoDAO,CategoriaDAO
 *  11.2 Uso de los metodos de EntityManager para obtener las transacciones,iniciar proceso
 *  6.3 Uso de metodo dao para guardar el producto en categoriaDAO y productoDAO.
 *  6.4 Uso de metodos de EntityManager para obtener transaccion,enviar los cambios y cerrar
 *      conexion 
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
		
		BigDecimal valorTotal = pedidoDao.valorTotalVenta();
		System.out.println("Valor total: " +valorTotal);
		
		List<ReporteVenta> reporteVentas = pedidoDao.reporteVentasVO();
		
		reporteVentas.forEach(System.out::println);
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
