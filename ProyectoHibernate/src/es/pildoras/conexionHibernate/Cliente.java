package es.pildoras.conexionHibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="cliente")
public class Cliente {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="apellido")
	private String apellido;
	
	@Column(name="direccion")
	private String direccion;
	
	
	
	//campo que me permite guardar varios pedidos por eso utilizo el campo de tipo list
	@OneToMany(fetch= FetchType.LAZY, mappedBy = "cliente", cascade= {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	private List<Pedido> pedidos;
	
	
	

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Cliente() {
		super();
	}

	public Cliente(String nombre, String apellidos, String direccion) {
		super();
		this.nombre = nombre;
		this.apellido = apellidos;
		this.direccion = direccion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	//creamos los getters and setters

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id")
	private DetallesCliente detallesCliente;

	public DetallesCliente getDetallesCliente() {
		return detallesCliente;
	}

	public void setDetallesCliente(DetallesCliente detallesCliente) {
		this.detallesCliente = detallesCliente;
	}
	//permita agregar pedidos para el cliente en conccreto que me hizo pedidos
	public void agregarPedidos(Pedido elPedido) {
		if(pedidos == null) {
			pedidos = new ArrayList<>();
			
		}
		pedidos.add(elPedido);
			elPedido.setCliente(this);
		//Tenemos que establecer quien es el cliente que me ha hecho este pedido
		//this hace referencia a la clase donde me encuentro
		
	}
	
	
	
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", direccion=" + direccion
				+ ", pedidos=" + pedidos + ", detallesCliente=" + detallesCliente + "]";
	}
	
	
	
	
	
}
