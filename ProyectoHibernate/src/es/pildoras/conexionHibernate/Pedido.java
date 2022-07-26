package es.pildoras.conexionHibernate;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.*;


@Entity
@Table(name="pedido")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name = "fecha")
	private GregorianCalendar fecha;
	
	@Column(name="forma_pago")
	private String formaPago;
	

	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="cliente_id")
	private Cliente cliente;

	//cuando creemos un pedido tendremos que pasarlo obligatoriamente una fecha


	public Pedido(GregorianCalendar date) {
		// TODO Auto-generated constructor stub
		super();
		this.fecha = fecha;
	}
	

	public Pedido() {
		super();
	}


	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", fecha=" + fecha + ", formaPago=" + formaPago + "]";
	}
	
	
	
	
}
