package es.pildoras.conexionHibernate;

import java.util.Date;
import java.util.GregorianCalendar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CrearPedidosCliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//crear sesion factory esepcificar las clasesque utilizaremos abrir la sesion
		SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml")
								       .addAnnotatedClass(Cliente.class)
								       .addAnnotatedClass(DetallesCliente.class)
								       .addAnnotatedClass(Pedido.class)
								       .buildSessionFactory();
		
		Session miSession = miFactory.openSession();		
		try {
			//objeto creado de tipo cliente
			miSession.beginTransaction();
			//Obtener el cliente de la tabla cliente de la base de datos
			Cliente elCliente = miSession.get(Cliente.class, 1);
			//Crear pedidos del cliente
			Pedido pedido1 = new Pedido( new GregorianCalendar(2020,6,4));
			Pedido pedido2 = new Pedido( new GregorianCalendar(2021,4,1));
			Pedido pedido3 = new Pedido( new GregorianCalendar(2018,7,6));
		
			//agregar pedidos creados al lciente creado
			
			elCliente.agregarPedidos(pedido1);
			elCliente.agregarPedidos(pedido2);
			elCliente.agregarPedidos(pedido3);
			
			//guardar los pedidos en la base de datos utilizando sesion.save
			
			miSession.save(pedido1);
			miSession.save(pedido2);
			miSession.save(pedido3);

			
			
			
			
			miSession.getTransaction().commit();
		
			
			
			miSession.close();
			
			} catch(Exception ex) {
			ex.printStackTrace();
		}
		
			finally { //finally quiero que se realize siempre
				miSession.close();
		
			miFactory.close();
		}
		
		
		}


}
