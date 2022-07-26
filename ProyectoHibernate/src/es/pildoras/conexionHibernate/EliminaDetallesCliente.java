package es.pildoras.conexionHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EliminaDetallesCliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//crear sesion factory esepcificar las clasesque utilizaremos abrir la sesion
		SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml")
								       .addAnnotatedClass(Cliente.class)
								       .addAnnotatedClass(DetallesCliente.class)
								       .buildSessionFactory();
		
		Session miSession = miFactory.openSession();		 
		try {
		
			miSession.beginTransaction();
			//transaccion se encargara de guardar este objeto de tipo cliente en la base de datos
			//clase del objeto que quiero obtener eliminar el cliente con el id4
			//creo una instancio o objeto obtenemos el id que queremos eliminar
			
			DetallesCliente detalleDelCliente = miSession.get(DetallesCliente.class, 1);
			
			detalleDelCliente.getElCliente().setDetallesCliente(null);
			
			
			if(detalleDelCliente != null) {
				
				
				miSession.delete(detalleDelCliente);
			}
		
			//ahora hay 2 aternativas o bien commit o rollback
			miSession.getTransaction().commit();
		
		
			
			miSession.close();
			
		} finally { //finally quiero que se realize siempre
		
			miFactory.close();
		}
		
		
		}


}
