package com.latam.jcvq.tienda.prueba;

import java.io.FileNotFoundException;

import javax.persistence.EntityManager;

import com.latam.jcvq.tienda.Utils.JPAUtils;
import com.latam.jcvq.tienda.dao.PedidoDAO;
import com.latam.jcvq.tienda.modelo.Pedido;

public class PruebaDeDesempeno {

	public static void main(String[] args) throws FileNotFoundException {
	LoadRecords.cargarRegistros();
	
	EntityManager em = JPAUtils.getEntityManager();
	
	PedidoDAO pedidoDao = new PedidoDAO(em);
	Pedido pedidoConCliente = pedidoDao.consultarPedidoConCliente(2l);
	
	em.close();
	
//	System.out.println(pedido.getFecha());
//	System.out.println(pedido.getItems().size());
	System.out.println(pedidoConCliente.getCliente().getNombre());
	
	}
}
