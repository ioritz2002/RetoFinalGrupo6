package vista;

import java.awt.EventQueue;

import javax.swing.JDialog;

import clases.Cliente;
import clases.Usuario;
import modelo.InterfazAmbosUsuarios;
import modelo.InterfazCliente;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Es la ventana desde la que el cliente podr� ir a las ventanas en las que podr� realizar sus acciones
 * @author grupo6
 * @version 1
 */
public class VMenuCliente extends JDialog implements ActionListener{
	/**
	 * Al pulsarlo se cerrar� la ventana actual y se abrir� la anterior
	 */
	private JButton btnAtras;
	/**
	 * Al pulsarlo se abrir� la ventana VCesta en la que se mostrar� los productos que lleva comprados en la cesta actual
	 */
	private JButton btnCarrito;
	/**
	 * Al pulsarlo se abrir� la ventana VHistorialCompras 
	 */
	private JButton btnHistorialCompra;
	/**
	 * Al pulsarlo se abrir� la venta VDatosCliente del cliente que ha pulsado el bot�n
	 */
	private JButton btnInfoPersonal;
	/**
	 * Al pulsarlo se abrir� la ventana VProductos 
	 */
	private JButton btnComprar;
	/**
	 * Se igualar� a la interfaz datosCliente que viene por par�metro para poder utilizarla fuera del constructor
	 */
	private InterfazCliente datosCliente;
	/**
	 * Se igualar� a la interfaz datosAmbos que viene por par�metro para poder utilizarla fuera del constructor
	 */
	private InterfazAmbosUsuarios datosAmbos;
	/**
	 * Se igualar� al usuario que viene por par�metro para poder utilizarlo fuera del constructor
	 */
	private Cliente usuario;
	/**
	 * Al pularlo se abrir� la ventana VValorar
	 */
	private JButton btnValorarProducto;

	/**
	 * 
	 * @param vPrincipal Es la ventana de la que viene
	 * @param b Indica al modal si tiene que estar activo o no
	 * @param datosCliente Es la interfaz que se utilizar� en caso de necesitar la base de datos
	 * @param usuario Son los datos del cliente que ha iniciado sesi�n
	 * @param datosAmbos Es la interfaz que se utilizar� en caso de necesitar la base de datos
	 */
	public VMenuCliente(VPrincipal vPrincipal, boolean b, InterfazCliente datosCliente, Cliente usuario, InterfazAmbosUsuarios datosAmbos) {
		super(vPrincipal);
		this.setModal(b);
		this.datosCliente = datosCliente;
		this.usuario = usuario;
		this.datosAmbos= datosAmbos;
		setBounds(100, 100, 521, 457);
		getContentPane().setLayout(null);
		
		btnAtras = new JButton("ATR\u00C1S");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAtras.setBounds(44, 325, 103, 53);
		getContentPane().add(btnAtras);
		btnAtras.addActionListener(this);
		
		btnInfoPersonal = new JButton("INFORMACION PERSONAL");
		btnInfoPersonal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnInfoPersonal.setBounds(24, 37, 212, 44);
		getContentPane().add(btnInfoPersonal);
		btnInfoPersonal.addActionListener(this);
		
		btnComprar = new JButton("COMPRAR");
		btnComprar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnComprar.setBounds(278, 37, 181, 44);
		btnComprar.addActionListener(this);
		getContentPane().add(btnComprar);
		
		btnValorarProducto = new JButton("VALORAR PRODUCTO");
		btnValorarProducto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnValorarProducto.setBounds(266, 139, 212, 44);
		btnValorarProducto.addActionListener(this);
		getContentPane().add(btnValorarProducto);
		
		btnCarrito = new JButton("CARRITO");
		btnCarrito.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCarrito.setBounds(36, 139, 181, 44);
		btnCarrito.addActionListener(this);
		getContentPane().add(btnCarrito);
		
		btnHistorialCompra = new JButton("HISTORIAL COMPRA");
		btnHistorialCompra.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnHistorialCompra.addActionListener(this);
		btnHistorialCompra.setBounds(278, 255, 181, 44);
		getContentPane().add(btnHistorialCompra);

	}

	/**
	 * Permite que al pulsar alg�n elemento de la ventana haga una acci�n concreta indicada en el m�todo
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAtras)) {
			this.dispose();
		}
		if (e.getSource().equals(btnInfoPersonal)) {
			VDatosCliente vDatosCliente = new VDatosCliente(this, true, (Cliente) usuario, datosCliente, datosAmbos);
			vDatosCliente.setVisible(true);
			this.dispose();
		}
		if (e.getSource().equals(btnComprar)) {
			VProductos vprod = new VProductos(this, true, usuario, datosCliente);
			vprod.setVisible(true);
		}
		if (e.getSource().equals(btnCarrito)) {
			VCesta cest = new VCesta(this, true, datosCliente, usuario);
			cest.setVisible(true);
		}
		if (e.getSource().equals(btnHistorialCompra)) {
			VHistorialCompras vHistorialCompras= new VHistorialCompras(this, true, usuario, datosCliente, datosAmbos);
			vHistorialCompras.setVisible(true);
		}
		if (e.getSource().equals(btnValorarProducto)) {
			VValorar vValorar= new VValorar(this, true, usuario, datosCliente);
			vValorar.setVisible(true);
		}
	}

}