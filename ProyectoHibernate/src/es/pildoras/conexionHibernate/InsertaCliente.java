package es.pildoras.conexionHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class InsertaCliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//crear sesion factory esepcificar las clasesque utilizaremos abrir la sesion
		SessionFactory miFactory = new Configuration().configure("hibernate.cfg.xml")
								       .addAnnotatedClass(Cliente.class)
								       .addAnnotatedClass(DetallesCliente.class)
								       .buildSessionFactory();
		
		Session miSession = miFactory.openSession();		
		try {
			//objeto creado de tipo cliente
			Cliente cliente1 = new Cliente("Paco", "Alvez" , "Granvia");
			DetallesCliente detallesCliente1 = new DetallesCliente("www.pinfo.com", "153412", "este es un comentario de paco");
			//Asociar los objetos con el setter que hemos creado en cliente
			cliente1.setDetallesCliente(detallesCliente1);
			//Esto guarda la informacion en las dos tablas relacionadas
			//comenzar transaccion
			miSession.beginTransaction();
			//transaccion se encargara de guardar este objeto de tipo cliente en la base de datos
			
			miSession.save(cliente1);

			//ahora hay 2 aternativas o bien commit o rollback
			miSession.getTransaction().commit();
		
			
			
			miSession.close();
			
		} finally { //finally quiero que se realize siempre
		
			miFactory.close();
		}
		
		
		}


}
