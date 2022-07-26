package es.pildoras.conexionHibernate;

import java.util.Date;
import java.util.GregorianCalendar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class ObtenerPedidosCliente {

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
			//Cliente elCliente = miSession.get(Cliente.class, 1);
			Query<Cliente> consulta = miSession.createQuery("SELECT CL FROM cliente CL JOIN FETCH CL.pedido where CL.id=: elClienteId", Cliente.class);
			consulta.setParameter("elClienteId", 1);
			Cliente elCliente = consulta.getSingleResult();
			                                   
			System.out.println("Cliente: "  + elCliente);

		
			
			
			
			miSession.getTransaction().commit();
							miSession.close();

		    //System.out.println("Pedidos: " + elCliente.getPedidos());

			
	
			} catch(Exception ex) {
			ex.printStackTrace();
		}
		
			finally { //finally quiero que se realize siempre
			
		
			miFactory.close();
		}
		
		
		}


}
