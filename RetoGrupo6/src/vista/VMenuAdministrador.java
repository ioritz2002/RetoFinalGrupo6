package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Usuario;
import modelo.InterfazAdministrador;
import modelo.InterfazAmbosUsuarios;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * Sirve para poder acceder a las acciones que únicamente puede realizar el administrador 
 * @author grupo6
 * @version 1
 */
public class VMenuAdministrador extends JDialog implements ActionListener{

	/**
	 * Al pulsarlo se irá a la ventana donde se mostrará el historial de compras del cliente seleccionado
	 */
	private JButton btnHistorialCliente;
	/**
	 *Al pulsarlo cerrará la ventana actual y se irá a la anterior 
	 */
	private JButton btnAtras;
	/**
	 * Se iguala a la interfaz datosAdmin que viene por parámetro para poder utilizarlos fuera del constructor
	 */
	private InterfazAdministrador datosAdmin;
	/**
	 * Al pulsarlo se abrirá una ventana donde poder hacer el alta de un repartidor
	 */
	private JMenuItem itmAltaRepartidor;
	/**
	 * Al pulsarlo se abrirá una ventana donde poder hacer el alta de un producto
	 */
	private JMenuItem itmAltaProducto;
	/**
	 *  Al pulsarlo se abrirá una ventana donde poder hacer la baja de un repartidor
	 */
	private JMenuItem itmBajaRepartidor;
	/**
	 *  Al pulsarlo se abrirá una ventana donde poder hacer la baja de un producto
	 */
	private JMenuItem itmBajaProducto;
	/**
	 *  Al pulsarlo se abrirá una ventana donde poder hacer la modificación de un producto
	 */
	private JMenuItem itmModificarProducto;
	/**
	 * Se igualará al usuario que viene por parámetro para poder utilizarlo fuera del constructor
	 */
	private Usuario usuario;
	/**
	 * Se igualará a la interfaz que viene por parámetro para utilizarlo fuera del constructor.
	 */
	private InterfazAmbosUsuarios datosAmbos;

	/**
	 * 
	 * @param vPrincipal Es la ventana de la que viene
	 * @param b Indica si el modal estará activo o no
	 * @param datosAdmin Es la interfaz que se va a utilizar en caso de necesitar utilizar la base de datos
	 * @param usuario Son los datos del administrador
	 * @param datosAmbos Es la interfaz que se va a utilizar en caso de necesitar la base de datos
	 */
	public VMenuAdministrador(VPrincipal vPrincipal, boolean b, InterfazAdministrador datosAdmin, Usuario usuario, InterfazAmbosUsuarios datosAmbos) {
		super(vPrincipal);
		this.setModal(b);
		this.datosAdmin = datosAdmin;
		this.usuario = usuario;
		this.datosAmbos= datosAmbos;
		
		JPanel contentPanel = new JPanel();
		setBounds(100, 100, 639, 386);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
			btnAtras = new JButton("ATR\u00C1S");
			btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnAtras.setBounds(68, 260, 105, 50);
			btnAtras.addActionListener(this);
			contentPanel.add(btnAtras);
			btnAtras.addActionListener(this);
		
			btnHistorialCliente = new JButton("HISTORIAL CLIENTE");
			btnHistorialCliente.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btnHistorialCliente.addActionListener(this);
			btnHistorialCliente.setBounds(287, 260, 273, 50);
			contentPanel.add(btnHistorialCliente);
		
			JMenuBar menuBar = new JMenuBar();
			menuBar.setBounds(0, 0, 623, 50);
			contentPanel.add(menuBar);
			
			JMenu menAlta = new JMenu("                    ALTA                ");
			menAlta.setFont(new Font("Segoe UI", Font.PLAIN, 16));
			menuBar.add(menAlta);
			
			itmAltaRepartidor = new JMenuItem("REPARTIDOR");
			menAlta.add(itmAltaRepartidor);
			itmAltaRepartidor.addActionListener(this);
			
			itmAltaProducto = new JMenuItem("PRODUCTO");
			menAlta.add(itmAltaProducto);
			itmAltaProducto.addActionListener(this);
			
			JMenu menBaja = new JMenu("                    BAJA                   ");
			menBaja.setFont(new Font("Segoe UI", Font.PLAIN, 16));
			menuBar.add(menBaja);
			
			itmBajaRepartidor = new JMenuItem("REPARTIDOR");
			menBaja.add(itmBajaRepartidor);
			itmBajaRepartidor.addActionListener(this);
			
			itmBajaProducto = new JMenuItem("PRODUCTO");
			menBaja.add(itmBajaProducto);
			itmBajaProducto.addActionListener(this);
			
			JMenu menModificacion = new JMenu("               MODIFICACION               ");
			menModificacion.setFont(new Font("Segoe UI", Font.PLAIN, 16));
			menuBar.add(menModificacion);
			
			itmModificarProducto = new JMenuItem("PRODUCTO");
			menModificacion.add(itmModificarProducto);
			itmModificarProducto.addActionListener(this);
		
	}

	/**
	 * Permite que al pulsar algún elemento de la ventana haga una acción concreta indicada en el método
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAtras)) {
			this.dispose();
		}
		if (e.getSource().equals(itmModificarProducto)) {
			VProductos vProductos = new VProductos(this, true, usuario, datosAdmin);
			vProductos.setVisible(true);
		}
		if(e.getSource().equals(itmAltaRepartidor)) {
			VAltaRepartidor a = new VAltaRepartidor(this, true, datosAdmin, usuario);
			a.setVisible(true);
		}
		if (e.getSource().equals(itmAltaProducto)) {
			VDatosProducto prod= new VDatosProducto(this, true, datosAdmin, usuario);
			prod.setVisible(true);
		}
		if (e.getSource().equals(btnHistorialCliente)) {
			VSelecCliente vSelecCliente= new VSelecCliente(this, true, datosAdmin, datosAmbos);
			vSelecCliente.setVisible(true);
		}
		if (e.getSource().equals(itmBajaRepartidor)) {
			VBajaRepartidor vBajaRepartidor = new VBajaRepartidor(this, true, datosAdmin);
			vBajaRepartidor.setVisible(true);
		}
		if (e.getSource().equals(itmBajaProducto)) {
			VProductos vProductos = new VProductos(this, true, usuario, datosAdmin);
			vProductos.setVisible(true);
			
			
		}
	}
}
