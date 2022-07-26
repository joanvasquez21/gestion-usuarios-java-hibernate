package es.pildoras.conexionHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ObtenerCliente {

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
					//Obtener objetos de tipo  detalles Cliente
					DetallesCliente detallesCliente1 = miSession.get(DetallesCliente.class, 1);
				
					System.out.println(detallesCliente1);
					System.out.println(detallesCliente1.getElCliente());
					//ahora hay 2 aternativas o bien commit o rollback
					
					//Elimine detalle cliente y en cascada elimina el cliente
					
					System.out.println("Ahora vamos a eliminar en cascada");
					miSession.delete(detallesCliente1);
					
					miSession.getTransaction().commit();
				
					
					
				}catch(Exception ex1) {
					
					ex1.printStackTrace();
					
					
				} finally { //finally quiero que se realize siempre
					miSession.close();
					miFactory.close();
				}
				
				
				

	}

}
